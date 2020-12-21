package com.birdea.testcode.test


/**
 * @author seungtae.hwang (birdea@sk.com)
 *
 * @since 2019. 1. 24.
 */


fun cases(obj: Any) {                                // 1
    when (obj) {
        1 -> println("One")                          // 2
        "Hello" -> println("Greeting")               // 3
        is Long -> println("Long")                   // 4
        !is String -> println("Not a string")        // 5
        else -> println("Unknown")                   // 6
    }
}

class MyClass

fun whenAssign(obj: Any): Any {
    val result = when (obj) {                   // 1
        1 -> "one"                              // 2
        "Hello" -> 1                            // 3
        is Long -> false                        // 4
        else -> 42                              // 5
    }
    return result
}

open class Lion(val name: String, val origin: String) {
    fun sayHello() {
        println("$name, the lion from $origin says: graoh!")
    }
}

class Asiatic(name: String) : Lion(name = name, origin = "India") // 1

fun calculate(x: Int, y:Int, oper: (Int, Int) -> Int): Int {
    return oper(x, y)
}

fun sum(x: Int, y: Int) = x + y

fun operation(): (Int)->Int{
    return ::square
}

fun square(x: Int) = x * x

fun main() {

    val upperCase1: (String) -> String = { str:String -> str.toUpperCase() }
    val upperCase2: (String) -> String = { str -> str.toUpperCase() }
    val upperCase3 = { str: String -> str.toUpperCase() }
    //val upperCase4 = { str -> str.toUpperCase() }
    val upperCase5: (String) -> String = { it.toUpperCase() }
    val upperCase6: (String) -> String = String::toUpperCase

    println(upperCase1("hello"))
    println(upperCase2("hello"))
    println(upperCase3("hello"))
    println(upperCase5("hello"))
    println(upperCase6("hello"))


    val func = operation()
    val value = func(2)
    println("func:$value")

    val sumResult = calculate(4, 5, ::sum)
    val mulResult = calculate(4, 5) {a,b->a*b}
    println("sumResult $sumResult, mulResult $mulResult")

    val x = 11
    if (x in 1..10) {            // 1
        print(x)
    }
    print(" ")

    if (x !in 1..4) {            // 2
        print(x)
    }

//    cases("Hello")
//    cases(1)
//    cases(0L)
//    cases(MyClass())
//    cases("hello")


    println("## end")

    /*operator fun Int.times(str: String) = str.repeat(this)       // 1
    println(2 * "Bye ")                                          // 2

    operator fun String.get(range: IntRange) = substring(range)  // 3
    val str = "Always forgive your enemies; nothing annoys them so much."
    println(str[0..14])   */                                       // 4
    /*
    infix fun Int.times(str: String) = str.repeat(this)        // 1
    println(3 times "Bye ")                                 // 2

    val pair = "Ferrari" to "Katrina"       // 3
    println(pair)

    infix fun String.onto(other: String) = Pair(this, other)    // 4
    val myPair = "McLaren" onto "Lucas"
    println(myPair)

    val sophia = Person("Sophia")
    val claudia = Person("Claudia")
    sophia likes claudia // 5
    */
}

class Person(val name: String) {
    val likedPeople = mutableListOf<Person>()
    infix fun likes(other: Person) { likedPeople.add(other) }  // 6
}