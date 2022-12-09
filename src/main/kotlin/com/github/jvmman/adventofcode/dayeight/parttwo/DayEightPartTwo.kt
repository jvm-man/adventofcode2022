package com.github.jvmman.adventofcode.dayeight.parttwo

import java.io.File

fun main() {
    val matrix = mutableListOf<List<Int>>()

    File("src/main/resources/input-day-8.txt").forEachLine { line ->
        matrix.add(line.toCharArray().map { character -> character.digitToInt() }.toList())
    }

    val width = matrix[0].size
    val height = matrix.size

    val matrixIndicesToScores = mutableMapOf<MatrixIndex, Int>()

    for (i in 0 until matrix.size) {
        for (j in 0 until matrix[0].size) {
            val centerPoint = MatrixIndex(i, j)

            val directionViewScores = mutableMapOf<Direction, Int>()
            for (direction in Direction.values()) {
                var nextTile: MatrixIndex? = null
                var counter = 0
                while (true) {
                    nextTile = getNextIndex(nextTile ?: centerPoint, direction)
                    if (!withinBounds(nextTile, width, height)) {
                        break
                    } else if (matrix.at(nextTile) < matrix.at(centerPoint)) {
                        counter++
                    } else {
                        counter++
                        break
                    }
                }
                directionViewScores[direction] = counter
            }

            var pointScore = 1
            for (i in directionViewScores.values) {
                pointScore *= i
            }

            matrixIndicesToScores[centerPoint] = pointScore
        }

        println(matrixIndicesToScores.maxBy {
            it.value
        })
    }
}

enum class Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT
}

fun getNextIndex(matrixIndex: MatrixIndex, direction: Direction): MatrixIndex {
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
}

fun withinBounds(matrixIndex: MatrixIndex, width: Int, height: Int): Boolean {
    return (matrixIndex.x in 0 until width) && (matrixIndex.y in 0 until height)
}

data class MatrixIndex(val x: Int, val y: Int)

fun List<List<Int>>.at(matrixIndex: MatrixIndex): Int {
    return this[matrixIndex.x][matrixIndex.y]
}