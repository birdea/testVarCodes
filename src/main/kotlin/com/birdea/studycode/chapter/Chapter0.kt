package com.birdea.testcode.chapter

import com.birdea.testcode.IStudy
import com.birdea.testcode.start
import com.birdea.testcode.end

class Chapter0 : IStudy {
    override fun study() {
        start(this)
        val t1 = TestOne()
        t1.methodOne()

        //
        TestTwo().methodTwo()

        //
        val user = UserC("Alice")
        user.address = "Elsenheimer 47, 80687 Muenchen"
        user.address = "Alsenheimer 57, 1687 Muenchen"

        end(this)
    }

    class User {
        constructor(a : String) : super() {
        }
        constructor(b : String, c : Int) {
        }
    }

    class UserA (nickname : String) {

    }

    interface IUser {
        val nickname : String
        val email : String
            get() = nickname.substring(3)
    }

    class PrivateUser( val nname: String) : IUser {
        override val nickname: String
            get() = nname.toString()
        override val email: String
            get() = super.email
    }

    class UserC(val name: String) {
        var address: String = "unspecified"
            set(value: String) {
                println(""" Address was changed for $name: "$field" -> "$value".""".trimIndent())
                field = value
            }
    }

    class TestOne {
        fun methodOne() {
            println("called methodOne!")
        }
    }

    class TestTwo {
        fun methodTwo() {
            println("called methodTwo!")
        }
    }

}