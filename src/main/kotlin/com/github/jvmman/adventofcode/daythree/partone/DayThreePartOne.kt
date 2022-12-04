package com.github.jvmman.adventofcode.daythree.partone

import java.io.File

fun splitRuckSack(ruckSack: String): List<String> {
    return ruckSack.chunked(ruckSack.length / 2)
}

fun String.getUniqueCharacters(): Set<Char> {
    val uniqueCharacters = mutableSetOf<Char>()

    for (character in this) {
        uniqueCharacters.add(character)
    }

    return uniqueCharacters
}

fun main() {
    var sum = 0

    File("src/main/resources/input-day-3.txt").forEachLine {
        val compartments = splitRuckSack(it)

        val compartmentOneContents = compartments[0].getUniqueCharacters()
        val compartmentTwoContents = compartments[1].getUniqueCharacters()

        val intersection = compartmentOneContents.intersect(compartmentTwoContents)
        val result = intersection.first()

        sum += if (result.isUpperCase()) {
            result.code - 38
        } else {
            result.code - 96
        }
    }

    println(sum)
}