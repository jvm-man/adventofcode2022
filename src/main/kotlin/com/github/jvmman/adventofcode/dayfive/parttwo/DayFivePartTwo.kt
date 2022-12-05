package com.github.jvmman.adventofcode.dayfive.parttwo

import java.io.File

fun main() {
    var readMode = ReadMode.STACK

    val stacks = mutableMapOf<Int, ArrayDeque<Char>>()

    File("src/main/resources/input-day-5.txt").forEachLine {
        if (it.isBlank()) {
            readMode = ReadMode.COMMAND
        } else {

            when (readMode) {
                ReadMode.STACK -> {
                    val chunks = it.toCharArray().toList().chunked(4)

                    var index = 1;

                    chunks.map {
                        index++ to it
                    }.filter { chunk ->
                        chunk.second.contains('[')
                    }.map { crate ->
                        crate.first to crate.second[1]
                    }.forEach { (index, letter) ->
                        if (!stacks.containsKey(index)) {
                            stacks[index] = ArrayDeque()
                        }

                        stacks[index]!!.addLast(letter)
                    }
                }

                ReadMode.COMMAND -> {
                    val parameters = it.split("move ", " from ", " to ").subList(1, 4).map { it.toInt() }

                    val (amount, from, to) = parameters

                    val multipleCrates = ArrayDeque<Char>()
                    for (i in 0 until amount) {
                        val elementToMove = stacks[from]!!.removeFirst()
                        multipleCrates.addLast(elementToMove)
                    }

                    for (i in 0 until multipleCrates.size) {
                            stacks[to]!!.addFirst(multipleCrates.removeLast())
                    }
                }
            }
        }
    }

    stacks.entries.stream().sorted { o1, o2 -> o1.key - o2.key }.forEach {
        print(it.value.first())
    }
}

enum class ReadMode {
    STACK, COMMAND
}