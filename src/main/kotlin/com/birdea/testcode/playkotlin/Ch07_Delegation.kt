package com.birdea.testcode.playkotlin

import kotlin.reflect.KProperty

/**
 * @author seungtae.hwang (birdea@sk.com)
 *
 * @since 2019. 1. 28.
 */

fun main() {
    val tomAraya = TomAraya("Trash Metal")
    tomAraya.makeSound()
    val elvisPresley = ElvisPresley("Dancin' to the Jailhouse Rock.")
    elvisPresley.makeSound()
    //
    mainB()
    //
    mainC()
    //
    mainD()
}


//----------------------------------------------- Delegation Pattern
interface SoundBehavior {
    fun makeSound()
}

class ScreamBehavior(val n:String): SoundBehavior {
    override fun makeSound() = println("${n.toUpperCase()} !!!")
}

class RockAndRollBehavior(private val n:String): SoundBehavior {
    override fun makeSound() {
        println("I'm The King of Rock 'N' Roll: $n")
    }
}

class TomAraya(n:String): SoundBehavior by ScreamBehavior(n)

class ElvisPresley(n:String): SoundBehavior by RockAndRollBehavior(n)


//----------------------------------------------- Delegated Properties
class Example {
    var p: String by Delegate()

    override fun toString() = "Example Class"
}

class Delegate {
    operator fun getValue(thisRef: Any?, prop: KProperty<*>): String {
        return "$thisRef, thank you for delegation '${prop.name}' to me!"
    }

    operator fun setValue(thisRef: Any?, prop: KProperty<*>, value: String) {
        println("$value has been assigned to ${prop.name} in $thisRef")
    }
}

fun mainB() {
    var e = Example()
    println("pre -> ${e.p}")
    e.p = "NEW"
    println("post -> ${e.p}")

}


//------------------------------- Standard Delegates
class LazySample {
    init {
        println("created!")
    }

    val lazyStr: String by lazy {
        println("computed!")
        "my lazy"
    }
}

fun mainC() {
    val sample = LazySample()
    println("lazyStr = ${sample.lazyStr}")
    println(" = ${sample.lazyStr}")
}


//----------------------------------- Storing Properties in a Map
class User(val map: Map<String, Any?>) {
    val name: String by map
    val age: Int by map
}

fun mainD() {
    val user = User(mapOf(
        "name" to "John Doe",
        "age" to 25
    ))
    println("name = ${user.name}, age = ${user.age}")
}

