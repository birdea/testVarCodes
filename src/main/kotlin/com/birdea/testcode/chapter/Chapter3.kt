package com.birdea.testcode.chapter

import com.birdea.testcode.IStudy
import com.birdea.testcode.start
import com.birdea.testcode.end

const val UNIX_LINE_SEPARATOR = "\n"

class Chapter3 : IStudy {

    override fun study() {
        start(this)

        val set = hashSetOf(1,7,53)
        val list = arrayListOf(1,7,53)
        val map = hashMapOf(1 to "one", 2 to "two", 53 to "fifty-three")

        println(set.javaClass)
        println(list.javaClass)
        println(map.javaClass)

        /**
         * easy & simple method call
         * default parameter value
         */

        val lst = listOf(1,2,3)

        println(joinToString(lst, ", ","[","]"))
        println(joinToString(lst, ";"))
        println(joinToString(lst))

        // usage of global value
        println("hi~ +enter_value:"+ UNIX_LINE_SEPARATOR)

        // usage of extensive method
        println("last char(Kotlin):"+"Kotlin".last())
        println("hey".last())
        println("hey".lastChar)

        val sb = StringBuilder("hello?")
        println(sb)
        println(sb.toString())
        println(sb.lastChar)

        // @see 'vararg' (same as '...' in java)
        val list2 = listOf(2,3,5,7,11)
        val aar = arrayListOf<String>("a","b","c")

        //val list3 = listOf("args:", *aar)

        // create map
        var map1 = mapOf(1 to "one", 7 to "seven", 53 to "fifty-three")
        map1.plus(4 to "df")

        // infix call
        1.to("one")
        1 to "one"

        // split
        println("12.345-6.A".split("."))
        println("12.345-6.A".split(".", "-"))

        // parse string with a substring method
        parsePath("/Users/yole/kotlin-book/chapter.adoc")

        // parse string with a regular expression
        parsePathReg("/Users/yole/kotlin-book/chapter.adoc")

        val kotlinLogo = """|  //
                           .| //
                           .|/ \"""
        println(kotlinLogo.trimMargin("."))

        // ${'$'} - what for ???
        val price = """${'$'}99.9"""
        val price2 = """$99.9"""
        println(price)
        println(price2)

        // example of 'make class better'
        saveUser(User(1, "a", "b"))

        end(this)
    }


    class User(val id: Int, val name: String, val address: String)
    fun saveUser(user: User) {
        fun validate(value: String, fieldName: String) {
            if(value.isEmpty()) {
                throw IllegalArgumentException(
                        "Can't save user ${user.id}: empty $fieldName"
                )
            }
        }
        validate(user.name, "Name")
        validate(user.address, "Address")
    }



    fun parsePathReg(path: String) {
        val regex = """(.+)/(.+)\.(.+)""".toRegex()
        val matchResult = regex.matchEntire(path)
        if(matchResult!=null) {
            val (directory, filename, extension) = matchResult.destructured
            println("Dir: $directory, name: $filename, ext: $extension")
        }

    }

    fun parsePath(path: String) {
        val dircetory = path.substringBeforeLast("/")
        val fullName = path.substringAfterLast("/")
        val fileName = fullName.substringBeforeLast(".")
        val extension = fullName.substringAfterLast(".")

        println("Dir: $dircetory, name: $fileName, ext: $extension")
    }

    infix fun Any.to(other: Any) = Pair(this, other)

    var StringBuilder.lastChar: Char
        get() = get(length-1)
        set(value: Char) {
            this.setCharAt(length-1, value)
        }

    val String.lastChar : Char
        get()=get(length-1)


    @JvmOverloads
    fun <T> joinToString(
            collection : Collection<T>,
            separator: String = ", ",
            prefix: String = "",
            postfix: String = ""
    ): String {
        val result = StringBuilder(prefix)

        for ((index, element) in collection.withIndex()) {
            if (index > 0) result.append(separator)
            result.append(element)
        }
        result.append(postfix)
        return result.toString()
    }
}