package com.github.jvmman.adventofcode.daysix.partone

import java.io.File

fun main() {
    val signalSize = 4 // 14 for part 2
    File("src/main/resources/input-day-6.txt").forEachLine {
        // increment substring size
        it.windowed(signalSize).forEachIndexed { index, s ->
            if (s.getUniqueCharacters().size == signalSize) {
                println(index + signalSize)
                return@forEachLine
            }
        }
    }
}

fun String.getUniqueCharacters(): Set<Char> {
    val uniqueCharacters = mutableSetOf<Char>()

    for (character in this) {
        uniqueCharacters.add(character)
    }

    return uniqueCharacters
}