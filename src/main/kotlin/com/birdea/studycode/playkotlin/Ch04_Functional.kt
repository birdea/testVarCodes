package com.birdea.testcode.playkotlin

/**
 * @author seungtae.hwang (birdea@sk.com)
 *
 * @since 2019. 1. 28.
 */

data class Item(val name: String, val price: Float)                                   // 1

data class Order(val items: Collection<Item>)

fun Order.maxPricedItemValue(): Float = this.items.maxBy { it.price }?.price ?: 0F    // 2
fun Order.maxPricedItemName() = this.items.maxBy { it.name }?.name ?: "NO_PRODUCTS"

val Order.commaDelimitedItemNames: String                                             // 3
    get() = items.map { it.name }.joinToString()

fun <T> T?.nullSafeToString() = this?.toString() ?: "NULL"

fun main() {

    val order = Order(listOf(Item("Bread", 25.0F), Item("Wine", 29.0F), Item("Water", 12.0F)))

    println("Max priced item name: ${order.maxPricedItemName()}")                     // 4
    println("Max priced item value: ${order.maxPricedItemValue()}")
    println("Items: ${order.commaDelimitedItemNames}")                                // 5


    // let
    order.maxPricedItemName()?.let {
        println("let $it")
    }

/*
    // with
    with(configuration) {
        println("$host:$port")
    }
    // instead of:
    println("${configuration.host}:${configuration.port}")
*/


    val numbers = listOf(1, -2, 3, -4, 5, -6)      // 1
    println("- numbers: $numbers")

    // filter
    val positives = numbers.filter { y -> y > 0 }  // 2
    val negatives = numbers.filter { it < 0 }      // 3
    println("positives: $positives")
    println("negatives: $negatives")

    // map
    val doubled = numbers.map { x -> x * 2 }      // 2
    val tripled = numbers.map { it * 3 }          // 3
    println("doubled: $doubled")
    println("tripled: $tripled")

    // any
    val anyNegative = numbers.any { it < 0 }
    val anyGT6 = numbers.any { it > 6 }
    println("anyNegative: $anyNegative")
    println("anyGT6: $anyGT6")

    // all
    val allEven = numbers.all { it % 2 == 0 }
    val allLess6 = numbers.all { it < 6 }
    println("allEven: $allEven")
    println("allLess6: $allLess6")

    // none
    val isAllEven = numbers.none { it % 2 == 1 }
    val isAllLess6 = numbers.none { it > 6 }
    println("isAllEven: $isAllEven")
    println("isAllLess6: $isAllEven")

}