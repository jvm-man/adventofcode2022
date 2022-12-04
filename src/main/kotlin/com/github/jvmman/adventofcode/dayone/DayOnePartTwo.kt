package com.github.jvmman.adventofcode.dayone

import java.io.File

fun main() {
    val elfCalories = mutableListOf<Int>()
    var currentElf = 0

    File("src/main/resources/input-day-1.txt").forEachLine {
        if (it.isBlank()) {
            elfCalories.add(currentElf)
            currentElf = 0
        } else {
            currentElf += it.toInt()
        }
    }

    if (currentElf != 0) {
        elfCalories.add(currentElf)
    }

    println(elfCalories.sortedDescending().subList(0, 3).sum())
}