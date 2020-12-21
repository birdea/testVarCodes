package com.birdea.testcode.playkotlin

import java.time.LocalDate
import java.time.chrono.ChronoLocalDate

/**
 * @author seungtae.hwang (birdea@sk.com)
 *
 * @since 2019. 1. 28.
 */


fun main() {

    doNamedArguments()

    doStringTemplates()

    doDestructuringDeclarations()

    doSmartCasts()
}


//-------------------------------------- Smart Casts
fun doSmartCasts() {
    val date: ChronoLocalDate? = LocalDate.now()

    if (date != null) {
        println(date.isLeapYear)
    }

    if (date != null && date.isLeapYear) {
        println("It's a leap year!")
    }

    if (date == null || !date.isLeapYear) {
        println("There's no Feb 29 this year...")
    }

    if (date is LocalDate) {
        val month = date.monthValue
        println(month)
    }
}


//-------------------------------------------- Destructuring Declarations
fun findMinMax(list: List<Int>): Pair<Int, Int> {
    val sortList = list.sorted()
    val min = sortList.first()
    val max = sortList.last()
    return Pair(min, max)
}
fun doDestructuringDeclarations() {
    val (x, y, z) = arrayOf(5, 10, 15)
    println("x:$x, y:$y, z:$z")

    val map = mapOf("Alice" to 21, "Bob" to 25)
    for ((name, age) in map) {
        println("$name is $age years old")
    }

    val (min, max) = findMinMax(listOf(100 ,90, 50, 98, 76, 83))
    println("min:$min, max:$max")

    //------------------------------------------------

    data class User(val username: String, val email: String)

    fun getUser() = User("Mary", "mary@somewhere.com")

    val user = getUser()
    val (username, email) = user
    println(username == user.component1())
    val user2 = getUser()
    val (_, emailAddress) = user2
    println("emailAddress:$emailAddress, user2:${user2.username}, user2:${user2.component2()}")

    //------------------------------------------------
    class Pair<K, V, J>(val first: K, val second: V, val third: J) {
        operator fun component1(): K {
            return first
        }
        operator fun component2(): V {
            return second
        }
        operator fun component3(): J {
            return third
        }
    }

    val (num, name) = Pair(1, "one")
    val (a, b, c) = Pair(1, 2, 3)
    println("num = $num, name = $name")
    println("a = $a, b = $b, c = $c")
}


//-------------------------------------- String Templates
fun doStringTemplates() {
    val greeting = "Kotliner"
    println("Hello $greeting")
    println("Hello ${greeting.toUpperCase()}")
}



//-------------------------------------- Named Arguments
fun format(userName: String, domain: String) = "$userName@$domain"
fun doNamedArguments() {
    println(format("mario", "example.com"))
    println(format("domain.com", "username"))
    println(format(userName = "foo", domain = "bar.com"))
    println(format(domain = "frog.com", userName = "pepe"))
}
