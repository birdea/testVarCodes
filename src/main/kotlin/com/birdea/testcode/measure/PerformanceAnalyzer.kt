package measure

import java.io.File
import java.text.SimpleDateFormat
import java.io.IOException
import java.io.Closeable
import java.io.BufferedReader
import java.io.FileReader
import java.text.ParseException
import java.util.*


class PerformanceAnalyzer {

    // 로그 파일 적재 위치
    private val LOG_PATH = "/logcat/"

    // 로그 데이터 발췌 기준
    enum class MeasureCriteria(var tag: String, var message: Array<String>) {
        //MEASURE_TRIGGER_WORD("D/NUGUauto::NuguClientManager:",arrayOf("asrResultListener.onCompleteResult(result:","문자 보내")),
        //
        ASR_SPEECH_START("D/NUGUauto::NuguClientManager", arrayOf("asrListener.onStateChanged(state:SPEECH_START)")),
        ASR_SPEECH_END("D/NUGUauto::NuguClientManager", arrayOf("asrListener.onStateChanged(state:SPEECH_END)")),
        ASR_SPEECH_RESULT("D/NUGUauto::NuguClientManager", arrayOf("asrResultListener.onCompleteResult(result:")),
        //
        TTS_PLAY_MESSAGE("D/NUGUauto::NuguClientManager", arrayOf("ttsListener.onReceiveTTSText(")),
        TTS_PLAY_PLAYING("D/DefaultTTSAgent:", arrayOf("[setCurrentState] state: PLAYING")),
        TTS_PLAY_FINISH("D/DefaultTTSAgent:", arrayOf("[setCurrentState] state: FINISHED")),
        //
        ASR_SPEECH_EXPECTING("D/NUGUauto::NuguClientManager", arrayOf("asrListener.onStateChanged(state:EXPECTING_SPEECH)")),
    }

    private val measureCriteria: Queue<MeasureCriteria> = LinkedList()
    private val measureData: Queue<MeasureData> = LinkedList()
    private var mapDirectiveComplete = HashMap<String, MeasureDirective>()

    fun analyze() {
        log("###########################")
        log("### Performace Analyzer ###")
        log("###########################")

        log("- how to use?\n" +
                " 1. get logcat files\n" +
                " 2. set logcat files on the folder (see val LOG_PATH=?)\n" +
                " 3. exec")

        log("- environment > start")
        // set dir-path
        val userPath = System.getProperty("user.dir")
        val logFilePath = userPath + LOG_PATH
        log(" - user.dir:$userPath")
        log(" - logFilePath:$logFilePath")

        // get log-dir
        val dir = File(logFilePath)
        if (!dir.exists()) {
            throw RuntimeException("[err] dir is not exist")
        }

        // get log-file list
        val logFiles = dir.listFiles()
        if (logFiles == null || logFiles.size < 1) {
            throw RuntimeException("[err] log files not exist")
        }
        log(" - logFiles.size():" + logFiles.size)
        log(" - sortFileByName()")
        sortFileByName(logFiles)

        log("- environment < end")

        doAnalyze(logFiles)
    }

    private fun doAnalyze(logFiles: Array<File>) {
        log("*** doAnalyze > start")
        log("** loop start, task(${logFiles.size})")
        for (logFile in logFiles) {
            if (logFile.isFile) {
                initCriteria()
                analyzeLogFile(logFile.path)
            }
        }
        log("** loop end, task(${logFiles.size})")
        log("*** doAnalyze < end")
    }

    private fun initCriteria() {
        measureCriteria.clear()
        buildMeasureCriteria()
        mapDirectiveComplete.clear()
    }

    private fun buildMeasureCriteria() {
        for (item in MeasureCriteria.values()) {
            measureCriteria.add(item)
        }
    }


    private fun analyzeLogFile(filePath: String) {
        var fr: FileReader? = null
        var br: BufferedReader? = null
        log("\n*** start - analyze() , filePath:$filePath")
        try {
            val file = File(filePath) // 로그 파일
            fr = FileReader(file) // 입력 스트림
            br = BufferedReader(fr) // 입력 버퍼

            // loop task
            var line: String?
            do {
                line = br.readLine()
                if (measureCriteria.isEmpty()) {
                    buildMeasureCriteria()
                }
                readLine(line)
            } while (line != null)
            resultOut()

        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            close(br)
            close(fr)
        }
    }


    fun close(io: Closeable?) {
        if (io == null) {
            return
        }
        try {
            io.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun readLine(line: String?) {
        //log("readLine() - start");
        val split = line?.split(" ".toRegex(), 5)?.toTypedArray()
        if (split != null && split?.size < 5) {
            return
        }
        val item = MeasureData()
        item.date = split?.get(0)
        item.time = split?.get(1)
        item.app = split?.get(2)
        item.tag = split?.get(3)
        item.message = split?.get(4)

        // directive
        collectAndJudgeDirectiveComplete(item)

        // validate
        val criteria = measureCriteria.element() ?: return
        if (item.tag?.startsWith(criteria.tag) == false) {
            return
        }
        if (item.message?.startsWith(criteria.message[0]) == false) {
            return
        }
        val optionalCheck = criteria.message.size
        if (optionalCheck > 1) {
            for (i in 1 until optionalCheck) {
                if (item.message?.contains(criteria.message[i]) == false) {
                    return
                }
            }
        }

        item.item = measureCriteria.poll()
        measureData.add(item)
    }

    private fun collectAndJudgeDirectiveComplete(data: MeasureData) {
        if (data.tag?.startsWith("D/NUGUauto::NuguClientManager") == false) {
            return
        }
        val line = data.message
        if (line?.startsWith("directiveHandlingListener.onRequested(") == true) {
            val request = line.substring(
                line.indexOf("dialogRequestId=") + "dialogRequestId=".length,
                line.indexOf(", messageId=")
            )

            val directive = MeasureDirective(request)
            directive.startTime = data.time!!
            directive.message = data.message

            mapDirectiveComplete[request] = directive
        }
        if (line?.startsWith("directiveHandlingListener.onCompleted(") == true) {
            var complete = line.substring(
                line.indexOf("dialogRequestId\": \"") + "dialogRequestId\": \"".length,
                line.indexOf(",") - 1
            )

            if (mapDirectiveComplete.contains(complete)) {
                var directive = mapDirectiveComplete[complete]!!
                directive.endTime = data.time!!
                directive.isComplete = true
                mapDirectiveComplete[complete] = directive
            }
        }
    }

    private fun resultOutOfDirectiveComplete() {
        log("\n> directive complete, size:${mapDirectiveComplete.size}")

        for (item in mapDirectiveComplete.keys) {
            val data = mapDirectiveComplete[item]
            log("$data")
        }
    }

    private fun resultOut() {
        val size = measureData.size
        var sum: Long = 0
        var pre: Long = 0
        var cur: Long = 0
        var gap: Long = 0

        log(String.format("[%2s] %5s(%5s) %s", "No", "+Gap", "=Sum", "Key > Log Message"))

        for (i in 0 until size) {
            val item = measureData.poll()
            cur = DateHelper.transform(item.time)
            if (pre > 0) {
                gap = cur - pre
            }
            sum += gap
            pre = cur

            log(String.format("[%2s] %5s(%5s) %s", i, gap, sum, item))
        }

        resultOutOfDirectiveComplete()
    }

    private fun sortFileByName(files: Array<File>) {
        Arrays.sort(files) { f1, f2 -> f1.name.compareTo(f2.name, ignoreCase = true) }
    }

    private fun log(msg: String) {
        println(msg)
    }


    private data class MeasureDirective(
        var dialogRequesteId: String,
        var startTime: String = "0",
        var endTime: String = "0",
        var isComplete: Boolean = false,
        var message: String? = null,
    ) {
        fun gap(): Long {
            return DateHelper.transform(endTime) - DateHelper.transform(startTime)
        }

        override fun toString(): String {
            return StringBuilder()
                .append("Directive request&complete($isComplete), ")
                .append("dialogRequesteId:$dialogRequesteId, ")
                .append("timeGap:${gap()} [$startTime~$endTime], ")
                .append("message:$message").toString()
        }
    }

    // 발췌된 로그 데이터 클래스
    private data class MeasureData(
        var date: String? = null,
        var time: String? = null,
        var app: String? = null,
        var tag: String? = null,
        var message: String? = null,
        var item: MeasureCriteria? = null
    ) {
        override fun toString(): String {
            return StringBuilder()
                .append(item).append(" -> ")
                .append(date).append(" ")
                .append(time).append(" ")
                .append(tag).append(" ")
                .append(message).append(" ").toString()
        }
    }
}

class DateHelper {

    companion object {

        fun transform(from: String?): Long {
            if (from == null || from.isEmpty()) {
                return -1
            }
            val transFormat = SimpleDateFormat("HH:mm:ss.SSS")
            try {
                val to: Date = transFormat.parse(from)
                return to.time
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            return -1
        }

    }
}