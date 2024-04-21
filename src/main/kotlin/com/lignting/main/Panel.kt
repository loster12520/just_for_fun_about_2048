package com.lignting.main

import java.util.*
import kotlin.random.Random

class Panel(private val size: Int, private val odds: Double, seed: Long, private val safeTimes: Int) {
    constructor(size: Int, odds: Double, seed: Long) : this(size, odds, seed, 1)
    constructor(size: Int, odds: Double) : this(size, odds, System.currentTimeMillis())
    constructor(size: Int) : this(size, 0.1)
    constructor() : this(4)
    constructor(panel: MutableList<MutableList<Int>>) : this(panel.size) {
        this.panel = panel
    }

    lateinit var panel: MutableList<MutableList<Int>>
    val panelQueue = LinkedList<MutableList<MutableList<Int>>>()

    private val random = Random(seed)

    init {
        panel = mutableListOf()
        for (i in 1..size) {
            panel.add(mutableListOf())
            for (j in 1..size) {
                panel[i - 1].add(0)
            }
        }

        addNewBlock(2)
    }

    private fun addNewBlock() {
        val list = mutableListOf<Pair<Int, Int>>()
        panel.forEachIndexed { i, ints ->
            ints.forEachIndexed { j, int ->
                if (int == 0)
                    list.add(Pair(i, j))
            }
        }
        val place = list[random.nextInt(list.size)]
        panel[place.first][place.second] =
            if (random.nextDouble() < odds) 4
            else 2
    }

    private fun addNewBlock(i: Int) {
        for (x in 1..i) {
            addNewBlock()
        }
    }

    fun left() {
        save()
        panel.forEachIndexed { index, it ->
            val queue = LinkedList(it).let {
                while (it.remove(0));
                it
            }
            val res = mutableListOf<Int>()
            while (!queue.isEmpty()) {
                val int = queue.pollFirst()
                if (queue.peekFirst() == int) {
                    queue.pollFirst()
                    res.add(int * 2)
                } else {
                    res.add(int)
                }
            }
            panel[index] = res.let {
                if (it.size < size) {
                    val list = it.subList(0, it.size)
                    while (list.size < size)
                        list.add(0)
                    list
                } else
                    it
            }
        }
    }

    fun right() {
        save()
        panel.forEachIndexed { index, it ->
            val queue = LinkedList(it).let {
                while (it.remove(0));
                it
            }
            val res = mutableListOf<Int>()
            while (!queue.isEmpty()) {
                val int = queue.pollLast()
                if (queue.peekLast() == int) {
                    queue.pollLast()
                    res.add(0, int * 2)
                } else {
                    res.add(0, int)
                }
            }
            panel[index] = res.let {
                if (it.size < size) {
                    val list = it.subList(0, it.size)
                    while (list.size < size)
                        list.add(0, 0)
                    list
                } else
                    it
            }
        }
    }

    fun up() {
        save()
        for (i in 0..<size) {
            for (j in 0..<size) {

            }
        }
    }

    private fun save() {
        panelQueue.push(panel.let {
            val list = mutableListOf<MutableList<Int>>()
            for (i in 1..size) {
                list.add(mutableListOf())
                for (j in 1..size) {
                    list[i - 1].add(panel[i - 1][j - 1])
                }
            }
            list
        })
        if (panelQueue.size > safeTimes)
            panelQueue.pollLast()
    }

    fun reset() {
        if (!panelQueue.isEmpty()) {
            panel = panelQueue.pop()
        } else {
            println("aaa")
        }
    }
}