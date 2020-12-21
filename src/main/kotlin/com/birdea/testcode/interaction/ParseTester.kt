package com.birdea.test

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

class ParseTester {

    var json1 = "{\"interactionControl\":{\"mode\":\"MULTI_TURN\"}}"
    var json2 = "{\"key\":\"unique_key\", \"interactionControl\":{\"mode\":\"MULTI_TURN\"}}"

    var payloadStart = "{\"key\":\"unique_key\", \"interactionControl\":{\"mode\":\"MULTI_TURN\"}}"
    var payloadFinish = "{\"key\":\"unique_key\"}"
    //

    val gson = Gson()

    fun test() {
        Log.msg("-- start")
        try {
            val vo = gson.fromJson(json1, InteractionControlDto::class.java)
            vo.key = "unique_key"
            val inter = vo.interactionControl
            Log.msg("InteractionManageVo > key:${vo.key}, interactionControl:${vo.interactionControl}")
            Log.msg("InteractionControlVo > mode:${inter?.mode}")
            val output = gson.toJson(vo)
            Log.msg("InteractionControlVo > output:${output}")
        } catch (e: Throwable) {
            //null
            e.printStackTrace()
        }
        Log.msg("-- end")
    }

    fun testInsertDelete() {
        Log.msg("-- testInsertDelete start")
        printMap()
        try {
            var startScope = 1..10
            Log.msg("-- testInsertDelete start $startScope")
            for (i in startScope) {
                val vo = gson.fromJson(payloadStart, InteractionControlDto::class.java)
                vo.key = i.toString()
                val json = gson.toJson(vo)
                startInteraction(json)
            }
            printMap()
            val finishScope = 1..11
            Log.msg("-- testInsertDelete finish $finishScope")
            for (i in finishScope) {
                var input = gson.toJson(InteractionControlDto(i.toString()))
                finishInteraction(input)
            }
            printMap()
        } catch (e: Throwable) {
            e.printStackTrace()
        }

        Log.msg("-- testInsertDelete end")
    }

    fun printMap() {
        var sets = interactionControlMap.keys
        println("item size:${sets.size}")
        for(item in sets) {
            println("item: ${interactionControlMap.get(item).toString()}")
        }
    }

    private var interactionControlMap = HashMap<String, IInteractionControl>()

    fun startInteraction(payload: String?) {
        payload?.let {
            val data = MessageFactory.create(payload, InteractionControlDto::class.java)
            data?.let {
                loadInteractionControl(data.interactionControl)?.let {
                    //interactionControlManager?.start(it)
                    interactionControlMap.put(data.key!!, it)
                }
            }
        }
    }

    fun finishInteraction(payload: String?) {
        payload?.let {
            val data = MessageFactory.create(payload, InteractionControlDto::class.java)
            val key = data?.key
            interactionControlMap[key]?.let {
                //interactionControlManager?.finish(it)
                interactionControlMap.remove(key)
            }
        }
    }

    private fun loadInteractionControl(control: InteractionControl?): IInteractionControl? {
        return if (control != null) {
            object : IInteractionControl {
                override fun getMode(): InteractionControlMode = when (control.mode) {
                    InteractionControl.Mode.MULTI_TURN -> InteractionControlMode.MULTI_TURN
                    InteractionControl.Mode.NONE -> InteractionControlMode.NONE
                }
            }
        } else {
            null
        }
    }

    private data class InteractionControlDto (
        @SerializedName("key")
        var key: String?= null,
        @SerializedName("interactionControl")
        var interactionControl: InteractionControl? = null
    )

    data class InteractionControl(
        @SerializedName("mode")
        val mode: Mode
    ) {
        enum class Mode {
            @SerializedName("NONE")
            NONE,
            @SerializedName("MULTI_TURN")
            MULTI_TURN
        }
    }

    enum class InteractionControlMode {
        NONE,
        MULTI_TURN
    }
}