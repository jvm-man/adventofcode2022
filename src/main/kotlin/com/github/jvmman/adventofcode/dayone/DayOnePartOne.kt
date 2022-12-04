package com.github.jvmman.adventofcode.dayone

import java.io.File

fun main() {
    var maxCalories = 0
    var currentElf = 0

    File("src/main/resources/input-day-1.txt").forEachLine {
        if (it.isBlank()) {
            maxCalories = maxOf(maxCalories, currentElf)
            currentElf = 0
        } else {
            currentElf += it.toInt()
        }
    }

    println(maxCalories)
}