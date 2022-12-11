package com.github.jvmman.adventofcode.dayeleven.partone

import java.io.File

fun main() {
    val monkeys = mutableListOf<Monkey>()

    var currentMonkey: Monkey? = null

    File("src/main/resources/input-day-11.txt").forEachLine {
        if (it.isBlank() && currentMonkey != null) {
            monkeys.add(currentMonkey!!)
            currentMonkey = null
        }

        when {
            it.startsWith("Monkey") -> {
                currentMonkey = Monkey()
            }

            it.startsWith("  Starting items") -> {
                currentMonkey!!.items = it.replace("  Starting items: ", "").split(", ").map { it.toInt() }.toMutableList()
            }

            it.startsWith("  Operation") -> {
                val expressionParts = it.replace("  Operation: new = old ", "").split(" ")
                val operand = expressionParts[0]

                if (expressionParts[1] == "old") {
                    when (operand) {
                        "+" -> {
                            currentMonkey!!.operation = { x -> x + x }
                        }

                        "*" -> {
                            currentMonkey!!.operation = { x -> x * x }
                        }
                    }
                } else {
                    val number = expressionParts[1].toInt()

                    when (operand) {
                        "+" -> {
                            currentMonkey!!.operation = { x -> x + number }
                        }

                        "*" -> {
                            currentMonkey!!.operation = { x -> x * number }
                        }
                    }
                }
            }

            it.startsWith("  Test") -> {
                currentMonkey!!.testForDivisibility = it.replace("  Test: divisible by ", "").toInt()
            }

            it.startsWith("    If true: ") -> {
                currentMonkey!!.passToMonkeyOnTrue = it.replace("    If true: throw to monkey ", "").toInt()
            }

            it.startsWith("    If false: ") -> {
                currentMonkey!!.passToMonkeyOnFalse = it.replace("    If false: throw to monkey ", "").toInt()
            }
        }
    }

    // iterate rounds
    for (i in 0 until 20) {
        for (monkey in monkeys) {
            val items = mutableListOf(*monkey.items!!.toTypedArray())

            for (itemId in 0 until items.size) {
                monkey.inspectedTimes++

                val item = items[itemId]
                var worryLevel = items[itemId]
                worryLevel = (monkey.operation!!)(worryLevel) / 3

                if (worryLevel % monkey.testForDivisibility!! == 0) {
                    monkeys[monkey.passToMonkeyOnTrue!!].items!!.add(worryLevel)
                    monkey.items!!.remove(item)
                } else {
                    monkeys[monkey.passToMonkeyOnFalse!!].items!!.add(worryLevel)
                    monkey.items!!.remove(item)
                }
            }
        }
    }

    println(monkeys.map { it.inspectedTimes }.sorted().reversed())
}

data class Monkey(
    var operation: ((old: Int) -> Int)? = null,
    var testForDivisibility: Int? = null,
    var passToMonkeyOnTrue: Int? = null,
    var passToMonkeyOnFalse: Int? = null,
    var items: MutableList<Int>? = null,
    var inspectedTimes: Int = 0
)