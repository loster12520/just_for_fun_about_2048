package com.lignting.main

import java.awt.Color
import java.awt.image.BufferedImage
import javax.swing.ImageIcon
import javax.swing.JFrame
import javax.swing.JLabel

fun main() {
    val image = BufferedImage(400, 300, BufferedImage.TYPE_INT_ARGB)
    for (i in 1..<400) {
        val color = rainbow_color(i / 400.0)
        for (j in 1..<300) {
//            println("$i $j $color")
            image.setRGB(i, j, color)
        }
    }
    val frame = JFrame("shaderTest")
    frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    frame.setSize(800, 600)
    val imageIcon = ImageIcon(image)
    val label = JLabel(imageIcon)
    frame.add(label)
    frame.isVisible = true
}

fun rainbow_color(x: Double): Int {
    // 输入需要[0,1)
    val breakpoints = doubleArrayOf(0.0, 1.0 / 6, 2.0 / 6, 3.0 / 6, 4.0 / 6, 5.0 / 6, 1.0)
    val colors = arrayOf(
        intArrayOf(255, 0, 0),   // 红色
        intArrayOf(255, 255, 0), // 黄色
        intArrayOf(0, 255, 0),   // 绿色
        intArrayOf(0, 255, 255), // 青色
        intArrayOf(0, 0, 255),   // 蓝色
        intArrayOf(255, 0, 255)  // 紫色
    )

    // 确定x所在的彩虹颜色段
    for (i in 0..<breakpoints.size - 1) {
        if (breakpoints[i] <= x && x < breakpoints[i + 1]) {
            // 使用线性插值计算RGB值
            val t = (x - breakpoints[i]) / (breakpoints[i + 1] - breakpoints[i])
            val (r1, g1, b1) = colors[i]
            val (r2, g2, b2) = if (i < colors.size - 1) colors[i + 1] else colors[0] // 循环回到开始
            val r = (r1 * (1 - t) + r2 * t).toInt()
            val g = (g1 * (1 - t) + g2 * t).toInt()
            val b = (b1 * (1 - t) + b2 * t).toInt()
            return Color(r, g, b).rgb
        }
    }

    // 如果x不在0到1之间，抛出异常
    throw IllegalArgumentException("x should be between 0 and 1")
}