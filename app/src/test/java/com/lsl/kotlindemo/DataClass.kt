package com.lsl.kotlindemo

import org.junit.Test

/**
 * Created by liusilong on 2017/8/24.
 * 数据类
 * 我们经常创建一些只保存数据的类
 */
class DataClass {
    val name = "Liusilong"
    val age = 24
    @Test
    fun main() {
        val person = Person(name = name, age = age)
        println(person)
        val child = Child(name, age)
        println(child)
        /**
         * 实例化Dog类的三种方式
         */
        val dog = Dog("jack", 9)
        val dog1 = Dog()
        val dog2 = Dog(null, 8)
        /**/

        /**
         * copy
         */
        val jack = User("jack", 1)
        val olderJack = jack.copy(age = 2)
        val rose = User(name = "rose", age = 9)
        val olderRose = rose.copy(age = 3)
        println(jack)
        println(olderJack)
        println(rose)
        println(olderRose)

        /**
         * data Class 的解构声明
         */
        val jane = User("jane", 34)
        val (name, age) = jane//数据对象（data class）的解构
        println("$name,$age years of age")
    }

    fun demo() {
        val str: String = ""
        when (str) {
            name -> println()
            "liusko" -> println()
        }
        val name = when {

            else -> "default"
        }
        when {
            1 in 0..9 -> println()
            4 is Int -> println()
            else -> println()
        }
    }


}

open class Person(name: String, age: Int)
data class Child(val name: String, val age: Int)
data class Dog(val name: String? = "", val age: Int = 0) {}
data class User(val name: String, val age: Int)


//===================密封类================
sealed class Expr

data class Const(val number: Double) : Expr()
data class Sum(val e1: Expr, val e2: Expr) : Expr()
object NotANumber : Expr()

//通常 when 作为表达式的时候都需要跟一个 else 子句
//但是这里条件是 密封类
//如果验证语句覆盖了密封类的所有情况，就不需要else子句了
fun eval(expr: Expr): Double = when (expr) {
    is Const -> expr.number
    is Sum -> eval(expr.e1)
    NotANumber -> Double.MAX_VALUE
// 不再需要 `else` 子句，因为我们已经覆盖了所有的情况
}