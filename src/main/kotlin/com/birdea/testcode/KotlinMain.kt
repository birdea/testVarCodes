package com.birdea.testcode

import java.util.*

fun main(args: Array<String>){
    println("***** kotlinsample start *****")

//    com.birdea.testcode.chapter.Chapter1().study()
//    com.birdea.testcode.chapter.Chapter2().study()
//    com.birdea.testcode.chapter.Chapter3().study()
//    com.birdea.testcode.chapter.Chapter4().study()
//    com.birdea.testcode.chapter.Chapter5().study()
    a()

    val leftShift = 1 shl 2
    val rightShift = 1 shr 2
    val unsignedRightShift = 1 ushr 2

    println("leftShift $leftShift")
    println("rightShift $rightShift")

    //val string = "string with \n new line"
    //val rawString = """ string with \n new line val $leftShift """

    //println("string $string")

    val list = arrayOf(1,2,3,4,"5","^")
    val oneToFifty = 1..50
    val stepList = oneToFifty.step(2)
    println ("- oneToFifty: ${oneToFifty.step} , ${stepList.step}")
    println ("- list.size: ${list.size}")

    for (index in list.indices) {
        println("Element $index is ${list[index]}")
    }

    for (k in list) {
        println ("+ value: $k")
    }

    println("***** kotlinsample end *****")
}

fun start(arg : Any) {
    println("* start :"+arg.toString())
}

fun end(arg : Any) {
    println("* end :" +arg.toString())
}

fun enter(arg:Int = 1) {
    for (i in 1..arg) {
        println()
    }
}

interface IStudy {
    fun study()
}

internal fun a() {
    val date = Date()
    val today = if (date.year == 2016) true else false

    println(today)
}