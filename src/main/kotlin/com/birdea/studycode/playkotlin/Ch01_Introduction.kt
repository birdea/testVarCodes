package com.birdea.testcode.playkotlin

/**
 * @author seungtae.hwang (birdea@sk.com)
 *
 * @since 2019. 1. 28.
 */

fun main() {
    // hello world
    println("Hello, World!")

    // functions
    doDefaultParameterValuesandNamedArguments()

    doInfixFunctions()

    doOperatorFunctions()

    doFunctionsWithVarargParameters()

    // variables
    doVariables()

    // null safety
    doNullSafety()

    // classes
    doClasses()

    // generics
    doGenerics()

    // Inheritance
    doInheritance()
}

fun doInheritance() {
    open class Dog {                // 1
        open fun sayHello() {       // 2
            println("wow wow!")
        }
    }

    class Yorkshire : Dog() {       // 3
        override fun sayHello() {   // 4
            println("wif wif!")
        }
    }

    val dog: Dog = Yorkshire()
    dog.sayHello()

    open class Tiger(val origin: String) {
        fun sayHello() {
            println("A tiger from $origin says: grrhhh!")
        }
    }

    class SiberianTiger : Tiger("Siberia")                  // 1

    val tiger: Tiger = SiberianTiger()
    tiger.sayHello()

    open class Lion(val name: String, val origin: String) {
        fun sayHello() {
            println("$name, the lion from $origin says: graoh!")
        }
    }

    class Asiatic(name: String) : Lion(name = name, origin = "India") // 1

    val lion: Lion = Asiatic("Rufo")                              // 2
    lion.sayHello()
}


//------------------------ Generics
fun doGenerics() {
    class MutableStack<E>(vararg items: E) {              // 1

        private val elements = items.toMutableList()

        fun push(element: E) = elements.add(element)        // 2

        fun peek(): E = elements.last()                     // 3

        fun pop(): E = elements.removeAt(elements.size - 1)

        fun isEmpty() = elements.isEmpty()

        fun size() = elements.size

        override fun toString() = "MutableStack(${elements.joinToString()})"
    }

    fun <E> mutableStackOf(vararg elements: E) = MutableStack(*elements)

    val stack = mutableStackOf(0.62, 3.14, 2.7)
    println(stack)
}



//------------------------ Classes
fun doClasses() {
    class Customer                                  // 1

    class Contact(val id: Int, var email: String)   // 2


    val customer = Customer()                   // 3

    val contact = Contact(1, "mary@gmail.com")  // 4

    println(contact.id)                         // 5
    contact.email = "jane@gmail.com"            // 6
}



//------------------------ Null Safety
fun doNullSafety() {
    /*
    // Null Safety
    var neverNull: String = "This can't be null"            // 1
    neverNull = null                                        // 2

    var nullable: String? = "You can keep a null here"      // 3
    nullable = null                                         // 4

    var inferredNonNull = "The compiler assumes non-null"   // 5
    inferredNonNull = null                                  // 6

    fun strLength(notNull: String): Int {                   // 7
        return notNull.length
    }

    strLength(neverNull)                                    // 8
    strLength(nullable)                                     // 9

    // Working with Nulls
    fun describeString(maybeString: String?): String {              // 1
        if (maybeString != null && maybeString.length > 0) {        // 2
            return "String of length ${maybeString.length}"
        } else {
            return "Empty or null string"                           // 3
        }
    }
    */
}

/* earlier than Kotlin v1.3
fun main(args: Array<String>) {}
*/



//------------------------ Default Parameter Values and Named Arguments
fun doDefaultParameterValuesandNamedArguments() {
    fun printMessage(message: String): Unit {                               // 1
        println(message)
    }

    fun printMessageWithPrefix(message: String, prefix: String = "Info") { // 2
        println("[$prefix] $message")
    }

    fun sum(x: Int, y: Int): Int {                                          // 3
        return x + y
    }

    fun multiply(x: Int, y: Int) = x * y                                    // 4

    printMessage("Hello")                                               // 5
    printMessageWithPrefix("Hello", "Log")              // 6
    printMessageWithPrefix("Hello")                            // 7
    printMessageWithPrefix(prefix = "Log", message = "Hello")           // 8
    println(sum(1, 2))                                            // 9
}



//--------------------------------- Infix Functions
fun doInfixFunctions() {
    class Person(val name: String) {
        val likedPeople = mutableListOf<Person>()
        infix fun likes(other: Person) { likedPeople.add(other) }  // 6
    }

    infix fun Int.times(str: String) = str.repeat(this)        // 1
    println(2 times "Bye ")                                    // 2

    val pair = "Ferrari" to "Katrina"                          // 3
    println(pair)

    infix fun String.onto(other: String) = Pair(this, other)   // 4
    val myPair = "McLaren" onto "Lucas"
    println(myPair)

    val sophia = Person("Sophia")
    val claudia = Person("Claudia")
    sophia likes claudia                                       // 5
}



//-------------------------------------- Operator Functions
fun doOperatorFunctions() {
    operator fun Int.times(str: String) = str.repeat(this)       // 1
    println(2 * "Bye ")                                          // 2

    operator fun String.get(range: IntRange) = substring(range)  // 3
    val str = "Always forgive your enemies; nothing annoys them so much."
    println(str[0..14])
}




//--------------------------------------- Functions with vararg Parameters
fun doFunctionsWithVarargParameters() {
    fun printAll(vararg messages: String) {                            // 1
        for (m in messages) println(m)
    }
    printAll("Hello", "Hallo", "Salut", "Hola", "你好")                 // 2

    fun printAllWithPrefix(vararg messages: String, prefix: String) {  // 3
        for (m in messages) println(prefix + m)
    }
    printAllWithPrefix(
        "Hello", "Hallo", "Salut", "Hola", "你好",
        prefix = "Greeting: "                                          // 4
    )

    fun log(vararg entries: String) {
        printAll(*entries)                                             // 5
    }
}



// ---------------------------------------- Variables
fun doVariables() {
    //-------------------
    var a: String = "initial"  // 1
    println(a)
    val b: Int = 1             // 2
    val c = 3                  // 3

    //-------------------
    var e: Int  // 1
    //println(e)  // 2

    //-------------------
    val d: Int  // 1
    fun someCondition() = true
    if (someCondition()) {
        d = 1   // 2
    } else {
        d = 2   // 2
    }
    println(d) // 3
}
