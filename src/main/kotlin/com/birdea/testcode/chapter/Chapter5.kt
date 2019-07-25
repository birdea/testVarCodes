package com.birdea.testcode.chapter

import com.birdea.testcode.IStudy
import com.birdea.testcode.start
import com.birdea.testcode.end

class Chapter5 : IStudy {
    override fun study() {
        start(this)
        val people = listOf(
            Person("Alice", 29),
            Person("Tom", 31)
        )
        println(people.maxBy { it.age })
        var list = listOf<Any>(1,2,3)
        //mutableListOf<>()
        //arrayListOf<>()
        end(this)
    }
}
///////
data class Person(val name: String, val age: Int)