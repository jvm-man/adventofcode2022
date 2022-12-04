package com.github.jvmman.adventofcode.daythree.parttwo

import java.io.File

fun String.getUniqueCharacters(): Set<Char> {
    val uniqueCharacters = mutableSetOf<Char>()

    for (character in this) {
        uniqueCharacters.add(character)
    }

    return uniqueCharacters
}

fun main() {
    var sum = 0
    val groups = mutableListOf<String>()

    File("src/main/resources/input-day-3.txt").forEachLine {
        if (groups.size < 3) {
            groups.add(it)
        } else {
            sum += compute(groups)

            groups.clear()
            groups.add(it)
        }
    }

    sum += compute(groups)

    println(sum)
}

private fun compute(groups: MutableList<String>): Int {
    val intersection = groups.map { group ->
        group.getUniqueCharacters()
    }.reduce { first, second ->
        first.intersect(second)
    }

    val result = intersection.first()

    return if (result.isUpperCase()) {
        result.code - 38
    } else {
        result.code - 96
    }
}