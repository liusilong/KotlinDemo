package com.lsl.kotlindemo

import org.junit.Test

/**
 * Created by liusilong on 2017/8/24.
 * 扩展
 * 扩展函数
 * 扩展属性
 */
class Extensions {
    @Test
    fun main() {
//        list扩展方法  交换指定元素
        val l = mutableListOf(1, 2, 3)
        l.swap(1, 2)
        l.forEach { print(it.toString() + "\t") }

        println()
        val str = "LiuSiLong"
        println(str.sub(0..3))
        println(extendProperty())
        val c = C()
        c.caller(D())

    }

    /**
     * 扩展方法：
     *  交换指定位置的元素
     *  MutableList<T> 为扩展方法的接受类型
     */
    fun <T> MutableList<T>.swap(index1: Int, index2: Int) {
        val tmp = this[index1]
        this[index1] = this[index2]
        this[index2] = tmp
    }

    /**
     * 扩展方法
     *  截取指定位置的字符串
     *  String 为扩展方法的接受类型
     */
    fun String.sub(range: IntRange): String {
        return this.substring(range)
    }

    /**
     * 扩展toString方法
     * 这里的 Any? 为扩展方法的接受类型，可为空
     */
    fun Any?._toString(): String {
        if (this == null) {
            return "null"
        }
        // 空检测之后，“this”会自动转换为非空类型，所以下面的 toString()
        // 解析为 Any 类的成员函数
        return toString()
    }

    /**
     * 扩展属性
     */

    //   为List添加扩展属性 lastIndex 返回最后一个下标
    val <T> List<T>.lastIndex: Int
        get() = size - 1

    fun extendProperty(): Int {
        val list = listOf<Int>(1, 2, 3, 4)
        return list.lastIndex
    }
    //    fun printFoo(c: C) {
//        println(c.foo())
//    }
}

class D {
    fun bar() {
        println("bar in D")
    }

    fun child() {
        println("child in D")
    }

}

class C {
    fun baz() {
        println("baz in C")
    }

    //    在 C 中定义 D 的扩展方法
    fun D.foo() {
        bar()
        baz()
//        默认调用 扩展接受者 里面的方法
        child()
//        调用 分发接受者 里面的同名方法
        this@C.child()
    }

    fun child() {
        println("child in C")
    }

    //    调用 D 的扩展方法
    fun caller(d: D) {
        d.foo()
    }
}

