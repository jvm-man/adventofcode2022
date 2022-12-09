package com.github.jvmman.adventofcode.dayseven.partone

import java.io.File
import java.nio.file.Path

fun main() {
    val pathSizeMap = mutableMapOf<Path, Int>()
    var currentPath = Path.of("/")
    var accumulator = 0

    File("src/main/resources/input-day-7.txt").forEachLine {
        when {
            it.startsWith("$") -> {
                val args = it.split(" ")

                if (args[1] == "cd") {
                    if (accumulator != 0) {
                        pathSizeMap[currentPath] = accumulator
                    }

                    accumulator = 0

                    currentPath = when {
                        args[2] == ".." -> {
                            currentPath.parent
                        }

                        args[2] == "/" -> {
                            Path.of("/")
                        }

                        else -> {
                            currentPath.resolve(args[2])
                        }
                    }

                    if (!pathSizeMap.containsKey(currentPath)) {
                        pathSizeMap[currentPath] = 0
                    }
                } else if (args[1] == "ls") {
                    accumulator = 0
                }
            }

            !it.startsWith("dir") -> {
                accumulator += it.split(" ")[0].toInt()
            }
        }
    }

    if (accumulator != 0) {
        pathSizeMap[currentPath] = accumulator
    }

    println(pathSizeMap.entries.map { it.key.sumChildrenSize(pathSizeMap) }.filter { it <= 100_000 }.sum())
}

fun Path.sumChildrenSize(pathSizeMap: Map<Path, Int>): Int {
    return pathSizeMap.filter {
        it.key.startsWith(this)
    }.map {
        it.value
    }.sum()
}