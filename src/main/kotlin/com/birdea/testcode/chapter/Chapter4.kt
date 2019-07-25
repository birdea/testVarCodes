package com.birdea.testcode.chapter

import com.birdea.testcode.IStudy
import com.birdea.testcode.start
import com.birdea.testcode.end

class Chapter4 : IStudy {
    override fun study() {
        start(this)

        Button().click()

        val button = Button()
        button.showOff()
        button.setFocus(true)
        button.click()

        end(this)
    }
}

///////
//class TwitterUser(nickname:String):com.birdea.testcode.chapter.User(nickname){}

///////
class User constructor(_nickname:String){
    val nickname = _nickname
    /*val nickname:String
    init {
        nickname = _nickname
    }*/
}

//////
sealed class Exprr{
    class Num(val value:Int): Exprr()
    class Sum(val left: Exprr, val right: Exprr):
        Exprr()
}

fun eval(e: Exprr):Int =
    when(e){
        is Exprr.Num -> e.value
        is Exprr.Sum -> eval(e.right) + eval(
            e.left
        )
    }

////////////
interface Expr
class Num(val value: Int): Expr
class Sum(val left: Expr, val right: Expr):
    Expr
fun eval2(e: Expr):Int =
        when(e){
            is Num -> e.value
            is Sum -> eval2(e.right) + eval2(
                e.left
            )
            else ->
                    throw IllegalArgumentException("Unknown expression")
        }

open class Outer {
    inner class Inner {
        fun getOuterReference() : Outer = this@Outer
    }
}

class Button2 : View {
    override fun getCurrentState(): State {
        return ButtonState()
    }

    override fun restoreState(state: State) {
    }

    class ButtonState : State {}
}

interface State

interface View {
    fun getCurrentState(): State
    fun restoreState(state: State)
}

open class RichButton : Outer(), Clickable {
    fun disable() {} // means final method, can not override on a child class

    open fun animate() {} // means open method, can override on a child class

    override fun click() { // means overrided method, can override on a child class
    }

    final override fun showOff() {
        super.showOff()
    }
}

internal open class TalkativeButton : Focusable {
    private fun yell() = println("Hey!")
    protected fun whisper() = println("Let's talk!")
}

/*fun com.birdea.testcode.chapter.TalkativeButton.giveSpeech() {
    yell()
    whisper()
}*/


interface Clickable {
    fun click()
    fun showOff() {
        println("I'm clickable!")
    }
}

interface Focusable {
    fun setFocus(b: Boolean) =
            println("I ${if (b) "got" else "lost"} focus.")
    fun showOff() = println("I'm focusable!")
}

class Button : Clickable, Focusable {
    override fun showOff() {
        super<Clickable>.showOff()
        super<Focusable>.showOff()
    }

    override fun click() = println("I was clicked")

}