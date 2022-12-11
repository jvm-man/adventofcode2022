package com.github.jvmman.adventofcode.dayten.partone

import java.io.File

fun main() {
    var x = 1
    var cycle = 1
    val cycleValues = mutableMapOf<Int, Int>()

    File("src/main/resources/input-day-10.txt").forEachLine {
        if (it.startsWith("addx")) {
            cycle++
            cycleValues[cycle] = x
            cycle++
            x += it.split(" ")[1].toInt()
            cycleValues[cycle] = x
        } else {
            cycle++
            cycleValues[cycle] = x
        }
    }

    val sum = cycleValues[20]!!*20 + cycleValues[60]!!*60 + cycleValues[100]!!*100 + cycleValues[140]!!*140 + cycleValues[180]!!*180 + cycleValues[220]!!*220
    println(sum)
}