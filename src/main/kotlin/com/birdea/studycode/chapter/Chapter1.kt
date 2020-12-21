package com.birdea.testcode.chapter

import com.birdea.testcode.IStudy
import com.birdea.testcode.start
import com.birdea.testcode.end

class Chapter1 : IStudy {

    override fun study() {
        start(this)
        //
        //val s0:String = null // error in compile
        val s1:String ?= null
        val s2:String = ""
        val s3 = "abcde"
        //
        printToUpperCase(s1)
        printToUpperCase(s2)
        printToUpperCase(s3)
        //
        end(this)
    }

    fun printToUpperCase(arg : String?) {
        if (arg is String) {
            println(arg.toUpperCase())
        } else {
            println(arg + " is not String")
        }
    }
}