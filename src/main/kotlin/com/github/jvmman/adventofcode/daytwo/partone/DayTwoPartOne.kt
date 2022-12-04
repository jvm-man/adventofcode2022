package com.github.jvmman.adventofcode.daytwo.partone

import java.io.File

enum class RockPaperScissors {
    ROCK, PAPER, SCISSORS;

    companion object {
        fun fromCode(code: Char): RockPaperScissors {
            return when(code) {
                'A', 'X' -> ROCK
                'B', 'Y' -> PAPER
                'C', 'Z' -> SCISSORS
                else -> throw IllegalArgumentException()
            }
        }
    }
}

fun calculateOurScore(opponentsChoice: RockPaperScissors, ourChoice: RockPaperScissors): Int {
    var totalScore = ourChoice.ordinal + 1

    // draw
    if (opponentsChoice == ourChoice) {
        totalScore += 3
    } else {
        // calculate winner
        if (ourChoice == RockPaperScissors.ROCK && opponentsChoice == RockPaperScissors.SCISSORS
            || ourChoice == RockPaperScissors.PAPER && opponentsChoice == RockPaperScissors.ROCK
            || ourChoice == RockPaperScissors.SCISSORS && opponentsChoice == RockPaperScissors.PAPER
        ) {
            totalScore += 6
        }
    }

    return totalScore
}

fun main() {
    var score = 0

    File("src/main/resources/input-day-2.txt").forEachLine {
        score += calculateOurScore(RockPaperScissors.fromCode(it[0]), RockPaperScissors.fromCode(it[2]))
    }

    println(score)
}