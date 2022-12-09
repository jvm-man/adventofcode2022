package com.github.jvmman.adventofcode.dayeight.partone

import java.io.File
import kotlin.math.max

fun main() {
    val matrix = mutableListOf<List<Int>>()
    val selectedIndices = mutableSetOf<MatrixIndex>()

    File("src/main/resources/input-day-8.txt").forEachLine { line ->
        matrix.add(line.toCharArray().map { character -> character.digitToInt() }.toList())
    }

    val maxLength = maxOf(matrix.size, matrix[0].size)
    val width = matrix[0].size
    val height = matrix.size
    var currentMatrixIndex: MatrixIndex? = null

    for (direction in Direction.values()) {
        currentMatrixIndex = null
        println("Processing direction $direction")

        for (i in 0 until maxLength) {
            var maxTreeHeight = -1
            currentMatrixIndex = null
            println("Processing index $i")
            do {
                currentMatrixIndex = getNextIndex(i, currentMatrixIndex, width, height, direction)
                println("Current index is $currentMatrixIndex")
                if (!withinBounds(currentMatrixIndex, width, height)) {
                    break
                }

                if (matrix[currentMatrixIndex.x][currentMatrixIndex.y] > maxTreeHeight) {
                    println("It is viewable, max tree height is $maxTreeHeight while it's " + matrix[currentMatrixIndex.x][currentMatrixIndex.y])
                    selectedIndices.add(currentMatrixIndex)
                } else {
                    println("It is invisible")
                }

                maxTreeHeight = max(maxTreeHeight, matrix[currentMatrixIndex.x][currentMatrixIndex.y])
            } while (withinBounds(currentMatrixIndex!!, width, height))
        }
    }

    println(selectedIndices)
    println(selectedIndices.size)
}

enum class Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT
}

fun getNextIndex(index: Int, matrixIndex: MatrixIndex?, width: Int, height: Int, direction: Direction): MatrixIndex {
    if (matrixIndex != null) {
        return when (direction) {
            Direction.LEFT -> {
                MatrixIndex(matrixIndex.x, matrixIndex.y + 1)
            }

            Direction.DOWN -> {
                MatrixIndex(matrixIndex.x + 1, matrixIndex.y)
            }

            Direction.UP -> {
                MatrixIndex(matrixIndex.x - 1, matrixIndex.y)
            }

            Direction.RIGHT -> {
                MatrixIndex(matrixIndex.x, matrixIndex.y - 1)
            }
        }
    } else {
        return when (direction) {
            Direction.LEFT -> {
                MatrixIndex(index, 0)
            }

            Direction.RIGHT -> {
                MatrixIndex(index, width - 1)
            }

            Direction.DOWN -> {
                MatrixIndex(0, index)
            }

            Direction.UP -> {
                MatrixIndex(height - 1, index)
            }
        }
    }
}

fun withinBounds(matrixIndex: MatrixIndex, width: Int, height: Int): Boolean {
    return (matrixIndex.x in 0 until width) && (matrixIndex.y in 0 until height)
}

data class MatrixIndex(val x: Int, val y: Int)