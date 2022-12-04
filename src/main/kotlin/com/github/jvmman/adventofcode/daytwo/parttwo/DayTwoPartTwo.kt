package com.github.jvmman.adventofcode.daytwo.parttwo

import java.io.File

enum class RockPaperScissors {
    ROCK, PAPER, SCISSORS;

    companion object {
        fun fromCode(code: Char): RockPaperScissors {
            return when (code) {
                'A' -> ROCK
                'B' -> PAPER
                'C' -> SCISSORS
                else -> throw IllegalArgumentException()
            }
        }

        fun forResult(opponentsChoice: RockPaperScissors, resultCode: Char): RockPaperScissors {
            return when (opponentsChoice) {
                ROCK -> when (resultCode) {
                    'X' -> SCISSORS
                    'Y' -> ROCK
                    'Z' -> PAPER
                    else -> throw IllegalArgumentException()
                }

                PAPER -> when (resultCode) {
                    'X' -> ROCK
                    'Y' -> PAPER
                    'Z' -> SCISSORS
                    else -> throw IllegalArgumentException()
                }

                SCISSORS -> when (resultCode) {
                    'X' -> PAPER
                    'Y' -> SCISSORS
                    'Z' -> ROCK
                    else -> throw IllegalArgumentException()
                }
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
        val opponentsChoice = RockPaperScissors.fromCode(it[0])
        score += calculateOurScore(
            opponentsChoice,
            RockPaperScissors.forResult(opponentsChoice, it[2])
        )
    }

    println(score)
}