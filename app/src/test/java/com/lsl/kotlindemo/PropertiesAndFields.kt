package com.lsl.kotlindemo

import org.junit.Test

/**
 * Created by liusilong on 2017/8/24.
 */
class PropertiesAndFields {
    @Test
    fun main() {
        var demo = Demo()
        demo.setVisibility
        println(demo.getStr)


    }
}

class Demo {
    var setVisibility: String = "abc"
        set(value) {
            field = value
        }
    var getStr: String? = null
        get() {
            return field
        }

}