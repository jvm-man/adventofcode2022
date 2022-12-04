package com.github.jvmman.adventofcode.dayfour.parttwo

import java.io.File

fun convertRangeExpressionToSet(range: String): Set<Int> {
    val rangeParts = range.split("-")

    val set = mutableSetOf<Int>()
    for (i in rangeParts[0].toInt() .. rangeParts[1].toInt()) {
        set.add(i)
    }

    return set
}

fun main() {
    var countOfFullyContainedAssignments = 0
    File("src/main/resources/input-day-4.txt").forEachLine {
        val assignmentPair = it.split(",")

        val firstSet = convertRangeExpressionToSet(assignmentPair[0])
        val secondSet = convertRangeExpressionToSet(assignmentPair[1])

        val intersection = firstSet.intersect(secondSet)

        if (intersection.isNotEmpty()) {
            countOfFullyContainedAssignments++
        }
    }

    println(countOfFullyContainedAssignments)
}