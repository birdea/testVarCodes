package com.birdea.testcode.playkotlin

/**
 * @author seungtae.hwang (birdea@sk.com)
 *
 * @since 2019. 1. 28.
 */

val numbers = listOf(1, -2, 3, -4, 5, -6)      // 1

fun main() {
/*
    // with
    with(configuration) {
        println("$host:$port")
    }
    // instead of:
    println("${configuration.host}:${configuration.port}")
*/
    doLet()
    doFilter()
    doMap()
    doAny()
    doAll()
    doNone()
    doFindAndFindLast()
    doFirstLast()
    doCount()
    doPartition()
    doAssociateByGroupBy()
    doZip()
    doFlatMap()
    doMinMax()
    doSorted()
    doMapElementAccess()
    doGetOrElse()
}

fun doGetOrElse() {
    val list = listOf(0, 10, 20)
    println(list.getOrElse(0) {42})
    println(list.getOrElse(1) {42})
    println(list.getOrElse(2) {42})
    println(list.getOrElse(3) {42})
    println(list.getOrElse(10) {42})

    val map = mutableMapOf<String, Int?>()
    println(map.getOrElse("x") {1})

    map["x"]=3
    println(map.getOrElse("x") {1})

    map["x"]=null
    println(map.getOrElse("x") {-1})
}

fun doMapElementAccess() {
    val map = mapOf("key" to 4442)
    val value1 = map["key"]
    val value2 = map["key2"]
    val value3:Int = map.getValue("key")

    val mapWithDefault = map.withDefault { k -> k.length }
    val value4 = mapWithDefault.getValue("key22")
    val value5 = mapWithDefault.getValue("ke")

    try {
        map.getValue("anotherKey")
    } catch (e: NoSuchElementException) {
        println("Message: $e")
    }

    println("map: $map")
    println("value1: $value1")
    println("value2: $value2")
    println("value3: $value3")
    println("value4: $value4")

    println("mapWithDefault: $mapWithDefault")
    println("value4: $value4")
    println("value5: $value5")
}

fun doSorted() {
    val shuffled = listOf(5,4,2,3,1)
    val natural = shuffled.sorted()
    val inverted = shuffled.sortedBy { -it }

    println("shuffled: $shuffled -> natural:$natural")
    println("shuffled: $shuffled -> inverted:$inverted")
}

fun doMinMax() {
    val numbers = listOf(1, 2, 3)
    val empty = emptyList<Int>()

    println("Numbers: $numbers, min = ${numbers.min()} max = ${numbers.max()}") // 1
    println("Empty: $empty, min = ${empty.min()}, max = ${empty.max()}")        // 2
}

fun doFlatMap() {
    val numbers = listOf(1,2,3)
    val tripled = numbers.flatMap { listOf(it, it, it) }

    println("numbers: $numbers")
    println("tripled: $tripled")
}

fun doZip() {
    val A = listOf("a", "b", "c")
    val B = listOf(1,2,3)
    val resultPairs = A zip B
    val resultReduce = A.zip(B) { a, b -> "$a$b" }

    println("A: $A")
    println("B: $B")
    println("resultPairs: $resultPairs")
    println("resultReduce: $resultReduce")
}


fun doAssociateByGroupBy() {
    data class Person(val name: String, val city: String, val phone: String)

    val people = listOf(
        Person("John", "Boston", "+1-888-123456"),
        Person("Sarah", "Munich", "+49-777-789123"),
        Person("Svyatoslav", "Saint-Petersburg", "+7-999-456789"),
        Person("Vasilisa", "Saint-Petersburg", "+7-999-123456"),
        Person("Tom", "Rome", "+7-999-123456")

    )

    val phoneBook = people.associateBy { it.phone }
    val cityBook = people.associateBy (Person::phone, Person::city)
    val peopleCities = people.groupBy (Person::city, Person::name)

    println("phoneBook: $phoneBook")
    println("cityBook: $cityBook")
    println("peopleCities: $peopleCities")
}

fun doLet() {
    fun checkStringContent(ns: String?) {
        println("for \"$ns\":")

        ns.let {                                                  // 1
            println("\tcontent: " + it)                           // 2
        }
    }

    checkStringContent(null)
    checkStringContent("")
    checkStringContent("some string with Kotlin")

    fun checkNullableString(ns: String?) {
        println("for \"$ns\":")

        ns?.let {                                                   // 3
            println("\tis empty? " + it.isEmpty())
            println("\tcontains Kotlin? " + it.contains("Kotlin"))
        }
    }
    checkNullableString(null)
    checkNullableString("")
    checkNullableString("some string with Kotlin")
}

fun doFilter() {
    val positives = numbers.filter { y -> y > 0 }  // 2
    val negatives = numbers.filter { it < 0 }      // 3
    println("positives: $positives")
    println("negatives: $negatives")
}

fun doMap() {
    val doubled = numbers.map { x -> x * 2 }      // 2
    val tripled = numbers.map { it * 3 }          // 3
    println("doubled: $doubled")
    println("tripled: $tripled")
}

fun doAny() {
    val anyNegative = numbers.any { it < 0 }
    val anyGT6 = numbers.any { it > 6 }
    println("anyNegative: $anyNegative")
    println("anyGT6: $anyGT6")
}

fun doAll() {
    val allEven = numbers.all { it % 2 == 0 }
    val allLess6 = numbers.all { it < 6 }
    println("allEven: $allEven")
    println("allLess6: $allLess6")
}

fun doNone() {
    val isAllEven = numbers.none { it % 2 == 1 }
    val isAllLess6 = numbers.none { it > 6 }
    println("isAllEven: $isAllEven")
    println("isAllLess6: $isAllLess6")
}

fun doFindAndFindLast() {
    val words = listOf("Lets", "find", "something", "in", "collection", "somehow")
    val first = words.find { it.startsWith("some") }
    val last = words.findLast { it.startsWith("some") }
    val nothing = words.find { it.contains("nothing") }
    println("first: $first")
    println("last: $last")
    println("nothing: $nothing")
}

fun doFirstLast() {

    val firstValue = numbers.first()
    val lastValue = numbers.last()
    val firstEven = numbers.first { it % 2 == 0 }
    val lastOdd = numbers.last { it % 2 != 0 }

    println("firstValue: $firstValue")
    println("lastValue: $lastValue")
    println("firstEven: $firstEven")
    println("lastOdd: $lastOdd")

    val words = listOf("foo", "bar", "baz", "faz")         // 1
    val empty = emptyList<String>()                        // 2

    val first = empty.firstOrNull()                        // 3
    val last = empty.lastOrNull()                          // 4

    val firstF = words.firstOrNull { it.startsWith('f') }  // 5
    val firstZ = words.firstOrNull { it.startsWith('z') }  // 6
    val lastF = words.lastOrNull { it.endsWith('f') }      // 7
    val lastZ = words.lastOrNull { it.endsWith('z') }      // 8

    println("first $first")
    println("last $last")
    println("firstF $firstF")
    println("firstZ $firstZ")
    println("lastF $lastF")
    println("lastZ $lastZ")
    println("lastZ ${words.first() {it.endsWith('z')}}")
}

fun doCount() {
    val totalCount = numbers.count()
    val evenCount = numbers.count { it % 2 == 0 }
    println("totalCount: $totalCount")
    println("evenCount: $evenCount")
}

fun doPartition() {
    val evenOdd = numbers.partition { it % 2 == 0 }
    val (positives, negatives) = numbers.partition { it > 0 }

    println("evenOdd: $evenOdd")
    println("positivies: $positives")
    println("negatives: $negatives")
}
