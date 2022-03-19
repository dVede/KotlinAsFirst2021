package lesson5.task1

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

data class ExtractRepeats(val test: List<String>, val result: Map<String, Int>)
data class FindSumOfTwo(val test: List<Int>, val number: Int, val result: Pair<Int, Int>)

class MapTests : FunSpec() {
    init {
        context("extractRepeats") {
            test("Нет повторений") {
                extractRepeats(listOf("a", "b", "c")) shouldBe emptyMap()
            }
            test("Пустые входные данные") {
                extractRepeats(emptyList()) shouldBe emptyMap()
            }
            test("Символ перевода на новую строку") {
                extractRepeats(listOf("\n", "\n", "\r", "\r")) shouldBe mapOf("\n" to 2, "\r" to 2)
            }
            test("Корректные тесты") {
                listOf(
                    ExtractRepeats(
                        listOf("", "", "", " ", " "),
                        mapOf("" to 3, " " to 2)
                    ),
                    ExtractRepeats(
                        listOf("a", "b", "a", "a", "B", "b", "A"),
                        mapOf("a" to 3, "b" to 2)
                    ),
                    ExtractRepeats(
                        listOf("ABC", "ABC", "abc", "ABC", "abc", "CBA", "aBc"),
                        mapOf("ABC" to 3, "abc" to 2)
                    )
                ).forEach { (test, result) ->
                    extractRepeats(test) shouldBe result
                }
            }
        }
        context("findSumOfTwo") {
            test("Пустой входной список") {
                findSumOfTwo(emptyList(), 7) shouldBe Pair(-1, -1)
            }
            test("Нет суммы чисел") {
                findSumOfTwo(listOf(4, 5, 6, 8), 7) shouldBe Pair(-1, -1)
            }
            test("Взаимодействие с нулем") {
                listOf(
                    FindSumOfTwo(listOf(7, 0), 7, Pair(0, 1)),
                    FindSumOfTwo(listOf(0, 7), 7, Pair(0, 1)),
                ).forEach { (test, number, result) ->
                    findSumOfTwo(test, number) shouldBe result
                }
            }
            test("Переполнение") {
                findSumOfTwo(listOf(Int.MAX_VALUE, 1), -2147483648) shouldBe Pair(0, 1)
            }
            test("Корректные тесты") {
                listOf(
                    FindSumOfTwo(listOf(-1, 5), 4, Pair(0, 1)),
                    FindSumOfTwo(listOf(1, 2, 2, 3), 4, Pair(1, 2)),
                    FindSumOfTwo(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9), 15, Pair(6, 7))
                ).forEach { (test, number, result) ->
                    findSumOfTwo(test, number) shouldBe result
                }
            }
        }
    }

}

