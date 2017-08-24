package com.lsl.kotlindemo.classandobject

import org.junit.Test

/**
 * Created by liusilong on 2017/8/23.
 * 类和继承
 * note:
 *  Kotlin 中没有 "new" 关键字
 *  Kotlin 类中没有静态方法
 *
 */
class classAndInheritance {
    @Test
    fun main() {
//        创建一个类的实例
        var person = Person("Liu")
        var customer = Customer()

        var dog = Dog()
        dog.f()
        dog.a()
        println("====inner class====")
        var smallDog = dog.SmallDog(24);
        smallDog.g()
    }
}

/**
 * 在没有类header和类body的时候
 * 大括号可以省略
 */
class Empty

/**
 * Person类和他的主要的构造方法
 */
class Person(firstName: String) {
    //    初始化代码块
    init {
        println(firstName)
    }

    //    次要的构造方法
    constructor(name: String, age: Int) : this(name) {

    }
}

class Animal {
    constructor(name: String) {

    }

    constructor(name: String, age: Int) {

    }

}

/**
 * 这个类默认会有一个无参数的public
 * 的构造方法，外界可以创建他
 */
class canCreateMe {

}

/**
 * 这个类声明了私有的构造方法，外界不能创建
 */
class DontCreateMe private constructor() {

}

/**
 * 如果主构造方法中的参数有默认值
 * 则JVM会默认为这个类附加一个无参构造方法
 * 所以声明这个类以下两种方式都可以
 * val customer = Customer("name")
 * val customer = Customer()
 * 如果主构造方法中的参数没有给定默认值，则
 * 实例化这个类的时候就必须带上参数
 */
class Customer(val customerName: String = "liusilong") {
    init {
        println(customerName + "\t")
    }
}

/**
 * 继承
 */

//
/**
 * 父类： open关键字和final关键字的作用恰恰相反
 * 使用open修饰的类可以被继承。kotlin中的所有类默认是final的
 * 使用open修饰的方法可以被重写
 */
open class Base(P: Int) {
    open val x: Int get() = 5

    //    可以被子类重写
    open fun v() {}

    //    不可以被子类重写
    fun nv() {}
}

/**
 * 继承Base类，并初始化构造函数
 *
 * 此方法为final类，即不能被继承
 */
class Derived(p: Int) : Base(p) {
    override val x: Int
        get() = super.x

    //    重写父类的方法
    override fun v() {
        super.v()
    }
}

/**
 * 此方法为open类，即可以被继承
 */
open class AnotherDerived(p: Int) : Base(p) {
    /**
     * 因为 v 方法是Base类中的，如果不想AnotherDerived
     * 的子类重写此方法，则需要加上 final 关键字
     *
     * 不能被AnotherDerived的子类重写了
     */
    final override fun v() {
        super.v()
    }
}

/**
 * 继承 AnotherDerived
 * 不过此时已经不能重写 v 方法了
 * 因为他在父类中被 final 修饰
 */
class AnotherSub(p: Int) : AnotherDerived(p) {

}

//或者这样初始化带有主构造方法的父类
class Derived1 : Base {
    constructor(p: Int) : super(p) {}

    //    重写父类的方法
    override fun v() {
        super.v()
    }

}

//或者
open class View {
    constructor(name: String)
    constructor(name: String, age: Int)
}

class MyView : View {
    constructor(name: String) : super(name) {}
    constructor(name: String, age: Int) : super(name, age) {}
}

interface Foo {
    val count: Int
}

class Bar1(override val count: Int) : Foo

class Bar2 : Foo {
    override val count: Int = 0
    var name: String
        get() {
            return name
        }
        set(value) {
            this.name = value
        }

    private var address: String = "china"
        private get() = field
        set(value) {
            field = value
        }
}

open class Anim {
    open fun f() {
        println("Anim.f()")
    }

    open val x: Int get() = 1
}

open class Dog : Anim() {
    override fun f() {
        super.f()
        println("dog.f()")
    }

    override val x: Int
        get() = super.x + 1

    fun a() {
        println(x)
    }

    inner class SmallDog(age: Int) {
        var b = age
        fun g() {
            println(b)
            a()
            f()
        }
    }
}

open class A {
    open fun f() {
        println("A.f")
    }

    fun a() {
        println("A.a")
    }
}

interface B {
    fun f() {
        println("B.f")
    }

    fun b() {
        println("B.b")
    }
}

/**
 * 当继承的类和实现的接口中的方法名冲突时
 * 调用时须有指明类名
 */
class c() : A(), B {
    override fun f() {
        super<A>.f()
        super<B>.f()
    }
}

/**
 * 抽象类
 * 抽象类中的抽象方法或者比变量不需要用open来修饰
 * 子类就可以重写
 */
abstract class Parent {
    abstract var a: Int
    //    不需要 open 修饰
//   可以被子类重写
    abstract fun f()

    //    不能被子类重写
    fun d() {

    }
}

class child : Parent() {
    override var a: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
        set(value) {}

    override fun f() {

    }

}