package com.birdea.test

import com.google.gson.Gson

object MessageFactory {

    private val gson = Gson()

    fun <T> create(json: String, classOfT: Class<T>): T? {
        return try {
            gson.fromJson(json, classOfT)
        } catch (e: Throwable) {
            e.printStackTrace()
            null
        }
    }
}