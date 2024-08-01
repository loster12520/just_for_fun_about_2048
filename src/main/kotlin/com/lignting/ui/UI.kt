package com.lignting.ui

import com.lignting.main.Panel
import javafx.application.Application
import javafx.scene.Group
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import javafx.scene.text.Font
import javafx.scene.text.Text
import javafx.stage.Stage
import kotlin.math.log2

class StageFor2048Two : Application() {

    private var panel = Panel()

    override fun start(stage: Stage?) {
        stage?.title = "2048 by lignting"
        stage?.scene = Scene(initFlush(), 500.0, 500.0)
        stage?.show()
        stage?.addEventFilter(KeyEvent.KEY_PRESSED) {
            when (it.code) {
                KeyCode.A, KeyCode.UP -> panel.left()
                KeyCode.W, KeyCode.DOWN ->
                    if (it.isControlDown)
                        stage.close()
                    else
                        panel.up()

                KeyCode.S, KeyCode.DOWN -> panel.down()
                KeyCode.D, KeyCode.RIGHT -> panel.right()
                KeyCode.R -> panel.reset()
                KeyCode.SPACE -> panel = Panel()
                KeyCode.CONTROL, KeyCode.WINDOWS, KeyCode.ALT, KeyCode.SHIFT -> {}
                else -> {
                    throw RuntimeException("Unhandled key code: ${it.code}")
                }
            }
            stage.scene = Scene(flush(), 500.0, 500.0)
        }
    }

    private fun initFlush(): Parent {
        val res = Group()

        val size = panel.size
        val padding = 30.0
        val spacing = 20.0
        val width = 500.0
        val height = 500.0

        val blockWidth = (width - 2 * padding + spacing) / size - spacing
        val blockHeight = (height - 2 * padding + spacing) / size - spacing

        panel.panel.forEachIndexed { i, it ->
            it.forEachIndexed { j, it ->
                val rectangele = Rectangle()
                rectangele.x = blockWidth * j + spacing * j + padding
                rectangele.y = blockHeight * i + spacing * i + padding
                rectangele.width = blockWidth
                rectangele.height = blockHeight
                rectangele.arcWidth = 20.0
                rectangele.arcHeight = 20.0
                res.children.add(rectangele)
                if (it == 0) {
                    rectangele.fill = Color.LIGHTGRAY
                } else {
                    rectangele.fill = getColor(it)
                    val text = Text(it.toString())
                    text.font = Font.font(
                        "可口可乐在乎体 楷体",
                        when (it.toString().length) {
                            5 -> 30.0
                            4 -> 40.0
                            else -> 50.0
                        }
                    )
                    val bounds = text.boundsInLocal
                    text.x = rectangele.x + blockWidth / 2 - bounds.width / 2
                    text.y = rectangele.y + blockHeight / 2 - bounds.height / 2 +
                            when (it.toString().length) {
                                5 -> 28
                                4 -> 38
                                else -> 48
                            }
                    res.children.add(text)
                }
            }
        }
        return res
    }

    private fun flush(): Parent {
        val res = Group()

        val size = panel.size
        val padding = 30.0
        val spacing = 20.0
        val width = 500.0
        val height = 500.0

        val blockWidth = (width - 2 * padding + spacing) / size - spacing
        val blockHeight = (height - 2 * padding + spacing) / size - spacing

        panel.panel.forEachIndexed { i, it ->
            it.forEachIndexed { j, it ->
                val rectangele = Rectangle()
                rectangele.x = blockWidth * j + spacing * j + padding
                rectangele.y = blockHeight * i + spacing * i + padding
                rectangele.width = blockWidth
                rectangele.height = blockHeight
                rectangele.arcWidth = 20.0
                rectangele.arcHeight = 20.0
                res.children.add(rectangele)
                if (it == 0) {
                    rectangele.fill = Color.LIGHTGRAY
                } else {
                    rectangele.fill = getColor(it)
                    val text = Text(it.toString())
                    text.font = Font.font(
                        "可口可乐在乎体 楷体",
                        when (it.toString().length) {
                            5 -> 30.0
                            4 -> 40.0
                            else -> 50.0
                        }
                    )
                    val bounds = text.boundsInLocal
                    text.x = rectangele.x + blockWidth / 2 - bounds.width / 2
                    text.y = rectangele.y + blockHeight / 2 - bounds.height / 2 +
                            when (it.toString().length) {
                                5 -> 28
                                4 -> 38
                                else -> 48
                            }
                    res.children.add(text)
                }
            }
        }
        return res
    }

    fun launch() = Application.launch()

}

private fun getColor(i: Int): Color {
    val rgb = hsvToRgb((log2((i - 1).toDouble()) * 22.5f) / 360, 0.5, 1.0)
    return Color(rgb.first, rgb.second, rgb.third, 1.0)
}

private fun hsvToRgb(h: Double, s: Double, v: Double): Triple<Double, Double, Double> {
    val i = (h * 6).toInt() % 6
    val f = (h * 6 - i)
    val p = v * (1 - s)
    val q = v * (1 - f * s)
    val t = v * (1 - (1 - f) * s)
    return when (i) {
        0 -> Triple(v, t, p)
        1 -> Triple(q, v, p)
        2 -> Triple(p, v, t)
        3 -> Triple(p, q, v)
        4 -> Triple(t, p, v)
        else -> Triple(v, p, q)
    }
}