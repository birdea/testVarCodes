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
    private val measureCriteria: Queue<MeasureCriteria> = LinkedList()
    private val measureData: Queue<MeasureData> = LinkedList()

    private val LOG_PATH = "/logs/"

    fun analyze() {
        log("# analyze() - start")
        log("--- check environment - start")
        // set dir-path
        val userPath = System.getProperty("user.dir")
        val logPath = userPath + LOG_PATH
        log("- user.dir:$userPath")
        log("- logPath:$logPath")

        // get log-dir
        val dir = File(logPath)
        if (!dir.exists()) {
            throw RuntimeException("[err] dir is not exist")
        }

        // get log-file list
        val logFiles = dir.listFiles()
        if (logFiles == null || logFiles.size < 1) {
            throw RuntimeException("[err] log files not exist")
        }
        log("- file.size():" + logFiles.size)
        log("--- check environment - end")

        // sort log-file list
        sortFileByName(logFiles)
        log(String.format("> loop start, task(%s)", logFiles.size))
        for (logFile in logFiles) {
            if (logFile.isFile) {
                initCriteria()
                analyzeLogFile(logFile.path)
            }
        }
        log(String.format("> loop end, task(%s)", logFiles.size))
        log("# analyze() - end")
    }

    private fun initCriteria() {
        measureCriteria.clear()
        for (item in MeasureCriteria.values()) {
            measureCriteria.add(item)
        }
    }

    private fun analyzeLogFile(filePath: String) {
        var fr: FileReader? = null
        var br: BufferedReader? = null
        try {
            log("> analyze() - start, filePath:$filePath")
            val file = File(filePath) // 로그 파일
            fr = FileReader(file) // 입력 스트림
            br = BufferedReader(fr) // 입력 버퍼

            // loop task
            var line: String? = null
            do {
                line = br.readLine()
                if (measureCriteria.isEmpty()) {
                    break
                }
                readLine(line)
            } while (line != null)
            resultOut()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            if (br != null) {
                try {
                    br.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                br = null
            }
            close(br)
            close(fr)
        }
        //log("> analyze() - end");
    }

    private fun close(io: Closeable?) {
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

        // validate
        val criteria = measureCriteria.element() ?: return
        if (item.tag?.startsWith(criteria.tag)==false) {
            return
        }
        if (item.message?.startsWith(criteria.message[0])==false) {
            return
        }
        val extraCheckCount = criteria.message.size
        if (extraCheckCount > 1) {
            for (i in 1 until extraCheckCount) {
                log("check: $i, ${criteria.message[i]}")
                if (item.message?.contains(criteria.message[i])==false) {
                    log("check: $i, ${criteria.message[i]} -> false")
                    return
                } else {
                    log("check: $i, ${criteria.message[i]} -> true")
                }
            }
        }
        item.item = measureCriteria.poll()
        //log("checked: ${item}")
        measureData.add(item)

        //log("readLine() - measureData sth on end :" +item.item);
    }

    private fun resultOut() {
        //log("resultOut() - start");
        val size = measureData.size
        var sum: Long = 0
        var pre: Long = 0
        var cur: Long = 0
        var gap: Long = 0
        for (i in 0 until size) {
            val item = measureData.poll()
            cur = transform(item.time)
            if (pre > 0) {
                gap = cur - pre
            }
            sum += gap
            pre = cur
            log(String.format("[%s], %s(%s), %s", i, gap, sum, item))
        }
    }

    private fun transform(from: String?): Long {
        if (from == null || from.isEmpty()) {
            return -1
        }
        val transFormat = SimpleDateFormat("HH:mm:ss.SSS")
        try {
            val to: Date = transFormat.parse(from)
            return to.getTime()
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return -1
    }

    private fun sortFileByName(files: Array<File>) {
        log("sortFileByName()")
        Arrays.sort(
            files
        ) { f1, f2 -> f1.name.compareTo(f2.name, ignoreCase = true) }
    }

    private fun log(msg: String) {
        println(msg)
    }

    // 로그 데이터 발췌 기준
    enum class MeasureCriteria(var tag: String, var message: Array<String>) {
        ASR_SPEECH_START("D/NUGUauto::NuguClientManager", arrayOf("asrListener.onStateChanged(state:SPEECH_START)")),
        ASR_SPEECH_END("D/NUGUauto::NuguClientManager", arrayOf("asrListener.onStateChanged(state:SPEECH_END)")),
        ASR_SPEECH_WAITING("D/NUGUauto::NuguClientManager", arrayOf("asrListener.onStateChanged(state:WAITING)")),
        ASR_DIRECTIVE_COMPLETE("D/NUGUauto::NuguClientManager", arrayOf("directiveHandlingListener.onRequested(","namespace=ASR","\"state\":\"COMPLETE\"")),
        TTS_PLAY_REQUEST("D/DefaultTTSAgent:", arrayOf("[startPlaying] sourceId: ")),
        TTS_PLAY_BUFFER("D/NuguOpusPlayer2:", arrayOf("[onStatusChanged] status: READY")),
        TTS_PLAY_START("D/NuguOpusPlayer2:", arrayOf("[onStatusChanged] status: STARTED")),
        TTS_PLAY_PLAYING("D/DefaultTTSAgent:", arrayOf("[setCurrentState] state: PLAYING"));
    }

    // 발췌된 로그 데이터 클래스
    private class MeasureData {
        var date: String? = null
        var time: String? = null
        var app: String? = null
        var tag: String? = null
        var message: String? = null
        var item: MeasureCriteria? = null
        override fun toString(): String {
            val sb = StringBuilder()
                .append(date).append(" ")
                .append(time).append(" ")
                .append(tag).append(" ")
                .append(message).append(" ")
                .append(item).append(" ")
            return sb.toString()
        }
    }
}