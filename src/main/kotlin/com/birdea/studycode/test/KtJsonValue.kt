package com.birdea.testcode.test

import com.birdea.testcode.util.L
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import org.junit.Test

/**
 * @author seungtae.hwang (birdea@sk.com)
 *
 * @since 2019. 1. 24.
 */

class KtJsonValue {

    internal var json1 = "{\"Command\":1,\"Content-Size\":14,\"Content\":\"json1\"}"
    internal var json2 = "{Command:2,Content-Size:24,Content:json2}"
    internal var json3 = "" //{"Command":80,"Content-Size":4,"Content":"0000","heartbeat_in":"20190117182803954","heartbeat_out":"20190117182803996"}
    internal var json5 = "{\"Command\":6,\"Content-Size\":-8,\"Content\":\"hello\"}"


    var gson = Gson()

    @Test
    fun tests() {
        val gson = Gson()
        val a1 = gson.fromJson(json1, Aaa::class.java)
        L.msg("result a1: " + a1.toString())
        val a2 = gson.fromJson(json2, Aaa::class.java)
        L.msg("result a2: " + a2.toString())
        val a3 = Aaa()
        a3.Command = 7
        a3.Content_Size = 3
        a3.Content = "hey"
        val jsonA3 = gson.toJson(a3)
        L.msg("result a3: $jsonA3")
        val a4 = gson.fromJson(jsonA3, Aaa::class.java)
        L.msg("result a4: " + a4.toString())
        val a5 = gson.fromJson(json5, Aaa::class.java)
        L.msg("result a5: " + a5.toString())
    }


    internal inner class Aaa {
        var Command = -1
        @SerializedName("Content-Size")
        var Content_Size = -2
        var Content: String? = null

        override fun toString(): String {
            return StringBuilder()
                .append("Command=").append(Command).append(", ")
                .append("Content_Size=").append(Content_Size).append(", ")
                .append("Content=").append(Content).append(", ")
                .toString()
        }
    }
}