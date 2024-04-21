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
            mutableListOf(0, 0, 0, 0),
            mutableListOf(0, 0, 0, 0),
            mutableListOf(0, 0, 0, 0),
            mutableListOf(2, 2, 4, 8)
        )
    )


    panel1.right()


    panel1.panel.toString().forEach {
        if (it == '[')
            print("\n$it")
        else
            print(it)
    }
    println("\n---\n")
}