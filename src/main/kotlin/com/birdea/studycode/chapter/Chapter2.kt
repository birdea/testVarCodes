package com.birdea.testcode.chapter

import com.birdea.testcode.IStudy
import com.birdea.testcode.start
import com.birdea.testcode.end
import com.birdea.testcode.enter
import java.io.BufferedReader
import java.util.*

class Chapter2 : IStudy {
    override fun study() {
        start(this)

        /* statement vs expression */
        println("3,2 max:"+max(3,2))
        println("3,2 maxExp:"+maxExp(3,2))
        println("3,2 maxExpWithoutReturn:"+maxExpWithoutReturn(3,2))

        /* val vs var */
        val question = "삶, 우주, 그리고 모든 것에 대한 궁극적인 질문"
        val answer1 = 42
        val answer2:Int = 42
        val yearsToCompute = 7.5e6
        val f1:Float = 3.4f

        var msg = "불금을 위하여"
        var f2 = 2.5
        var answer3 = 42

        //question = "what does it mean?"
        msg = "oh~ yes"
        //answer3 = "switchable?"

        textTemplete1(listOf("Bob", "Tom"))
        textTemplete2(listOf("Bob", "Tom"))

        val rect = Rectangle(10, 9)
        println("isSquare? "+rect.isSquare +",w:"+rect.width+"/h:"+rect.height)
        rect.height = 10
        rect.width = 10
        println("isSquare? "+rect.isSquare +",w:"+rect.width+"/h:"+rect.height)

        val enum = ""

        println("blue.rgb():"+ RgbColor.BLUE.rgb())
        println("indigo.rgb():"+ RgbColor.INDIGO.rgb())
        println("violet.rgb():"+ RgbColor.VIOLET.rgb())

        // when
        println("getMnemonic:"+getMnemonic(Color.GREEN))
        println("getMnemonic:"+getMnemonic(Color.VIOLET))

        // evaluate
        println("eval1 - sum(1,2,4):"+eval1(
            Sum(
                Sum(
                    Num(1),
                    Num(2)
                ), Num(4)
            )
        ))
        println("com.birdea.testcode.chapter.eval2 - sum(2,4,7):"+eval2(
            Sum(
                Sum(
                    Num(2),
                    Num(4)
                ), Num(7)
            )
        ))

        enter()
        // iteration
        val oneToTen = 1..10
        for(i in 1..100) {
            print(fizzBuzz(i))
        }
        enter()
        for(i in 100 downTo 1 step 2){
            print(fizzBuzz(i))
        }
        enter(2)

        // iteration for map
        val binaryReps = TreeMap<Char, String>()
        for (c in 'A'..'F') {
            val binary = Integer.toBinaryString(c.toInt())
            binaryReps[c] = binary
        }
        for ((letter, binary) in binaryReps) {
            println("$letter = $binary")
        }

        enter()
        // iteration with collection
        val list = arrayListOf("10","11","1001")
        for ((index, element) in list.withIndex()) {
            println("$index: $element")
        }

        //validate value
        println(isLetter('c'))
        println(isDigit('9'))
        println(isNotDigit('7'))

        println(recognize('5'))
        println(recognize('c'))

        println("Kotlin" in "Java".."Scala")
        println("Kotlin" in setOf("Java","Scala","kotlin"))

        end(this)
    }

    // try-catch-finally
    fun readNumber(reader: BufferedReader):Int? {
        try {
            val line = reader.readLine()
            return Integer.parseInt(line)
        }
        catch(e: NumberFormatException) {
            return null
        }
        finally {
            reader.close()
        }
    }

    // scope, range
    fun recognize(c:Char) = when(c) {
        in '0'..'9' -> "It's a digit"
        in 'a'..'z' -> "it's a letter"
        else -> "don't know.."
    }

    fun isLetter(c:Char) = c in 'a'..'z' || c in 'A'..'Z'
    fun isDigit(c:Char) = c in '0'..'9'
    fun isNotDigit(c:Char) = c !in '0'..'9'

    fun fizzBuzz(i:Int) = when {
        i%15==0 -> "FizzBuzz"
        i%3==0 -> "Fizz"
        i%5==0 -> "Buzz"
        else -> "$i"
    }

    // evaluate v2
    fun eval2(e: Expr):Int =
        when(e){
            is Num ->
                    e.value
            is Sum ->
                    eval2(e.right) +eval2(e.left)
            else ->
                    throw IllegalStateException("Unknown expression")
        }

    // evaluate v1
    fun eval1(e: Expr):Int {
        if(e is Num) {
            val n = e//as com.birdea.testcode.chapter.Num
            return n.value
        }
        if(e is Sum) {
            return eval1(e.right) + eval1(e.left)
        }
        throw IllegalArgumentException("Unknown expression")
    }

    // define
    interface Expr
    class Num(val value:Int): Expr
    class Sum(val left: Expr, val right: Expr):
        Expr

    fun mix(c1: Color, c2: Color) {
        when(setOf(c1, c2)) {
            setOf(
                Color.RED,
                Color.YELLOW
            ) -> Color.ORANGE
            setOf(
                Color.YELLOW,
                Color.BLUE
            ) -> Color.GREEN
            else -> throw Exception("not handled..")
        }
    }

    // handle when/else expression
    fun getMnemonic(color: Color) =
        when (color) {
            Color.RED -> "Richard"
            Color.ORANGE -> "Of"
            Color.YELLOW -> "York"
            Color.GREEN -> "Gave"
            else -> {
                "else -> Dirty color"
                //throw Exception("else -> Dirty color")
            }
        }

    enum class RgbColor(val r:Int, val g:Int, val b:Int) {
        RED(255, 0,0),
        ORANGE(255,165,0),
        YELLOW(255,255,0),
        GREEN(0,255,0),
        BLUE(0,0,255),
        INDIGO(75,0,130),
        VIOLET(238,130,238)
        ;

        fun rgb() = (r*256+g)*256+b
    }


    enum class Color{
        RED, ORANGE, YELLOW, GREEN, BLUE, INDIGO, VIOLET
    }

    // property getter
    class Rectangle(var height: Int, var width: Int) {
        val isSquare: Boolean
            get() {
                return height == width
            }
    }

    // class
    class Person(val name:String, val isMarried:Boolean)
    //class com.birdea.testcode.chapter.Person(val name:String)

    // templete
    fun textTemplete1(args:List<String>) {
        val name = if (args.size>0) args[0] else "Kotlin"
        println("Hello~1, $name")
    }
    fun textTemplete2(args:List<String>) {
        println("Hello~2, ${if(args.size>0) args[0] else "someone"}")
    }

    // statement
    fun max(a: Int, b: Int):Int {
        return if (a>b) a else b
    }

    // expression
    fun maxExp(a: Int, b: Int):Int = if (a>b) a else b

    // expression
    fun maxExpWithoutReturn(a: Int, b: Int) = if (a>b) a else b
}