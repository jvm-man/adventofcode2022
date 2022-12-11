package com.github.jvmman.adventofcode.dayten.parttwo

import java.io.File

fun main() {
    var x = 1
    var cycle = 1
    val cycleValues = mutableMapOf<Int, Int>()

    File("src/main/resources/input-day-10.txt").forEachLine {
        if (it.startsWith("addx")) {
            cycleValues[cycle++] = x
            x += it.split(" ")[1].toInt()
            cycleValues[cycle++] = x
        } else {
            cycleValues[cycle++] = x
        }
    }

    var lineCount = 0
    for (i in 1..240) {
        val currentPositionInLine = i - lineCount * 40
        if (currentPositionInLine in cycleValues[i]!!-1..cycleValues[i]!!+1) {
            print("#")
        } else {
            print(".")
        }
        if (i % 40 == 0) {
            lineCount++
            println()
        }
    }
}