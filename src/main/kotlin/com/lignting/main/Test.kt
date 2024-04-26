package com.lignting.main

fun main() {
//    val panel=Panel()
//
//    panel.panel.toString().forEach {
//        if (it == '[')
//            print("\n$it")
//        else
//            print(it)
//    }
//    println("\n---\n")
//
//    panel.left()
//
//    panel.panel.toString().forEach {
//        if (it == '[')
//            print("\n$it")
//        else
//            print(it)
//    }
//    println("\n---\n")


    val panel1 = Panel(
        mutableListOf(
            mutableListOf(0, 0, 0, 8),
            mutableListOf(0, 0, 0, 4),
            mutableListOf(0, 0, 0, 2),
            mutableListOf(0, 0, 0, 2)
        )
    )


    panel1.down()


    panel1.panel.toString().forEach {
        if (it == '[')
            print("\n$it")
        else
            print(it)
    }
    println("\n---\n")
}