package lesson6.task1

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

data class BestHighJump(val jumps: String, val result: Int)
data class MostExpensive(val description: String, val result: String)

class ParseTests : FunSpec() {
    init {
        context("bestHighJump") {
            test("Empty string") {
                bestHighJump("") shouldBe -1
            }
            test("Blank string") {
                bestHighJump(" ") shouldBe -1
            }
            test("Incorrect format") {
                listOf(
                    BestHighJump("abc", -1),
                    BestHighJump("225 *** 247 +", -1),
                    BestHighJump("220 ++ 230 +- abc", -1),
                    BestHighJump("abc +%- 220 ++ 230 +", -1),
                    BestHighJump("-210 %-+ -220 +", -1),
                    BestHighJump("-210 %-+ -220 +", -1),
                ).forEach { (jumps, result) ->
                    bestHighJump(jumps) shouldBe result
                }
            }
            test("Plus sign front number") {
                bestHighJump("+230 %- +220 +") shouldBe 220
            }
            test("Unicode numbers") {
                bestHighJump("220 + \u0035\u0036\u0036 +") shouldBe 566
            }
            test("No successful jumps") {
                bestHighJump("225 -%- 247 -%% ") shouldBe -1
            }
            test("Start trailing zero") {
                bestHighJump("00234 %-- 00223 +") shouldBe 223
            }
            test("Newline characters") {
                bestHighJump("257 %----- 223 -+% 244\n --%+") shouldBe 223
                bestHighJump("257 %----- 223 -+% 244\r --%+") shouldBe 223
            }
            test("Gap between attempts") {
                bestHighJump("257 % - + 223 - + % 244 --%+") shouldBe 244
            }
            test("Starts with space character") {
                bestHighJump(" 225 %-+ 236 -+% 227 --%+") shouldBe 236
            }
            test("Ends with space character") {
                bestHighJump("243 %-+ 223 -+% 244 --%+ ") shouldBe 244
            }
            test("Correct tests") {
                bestHighJump("257 %----- 223 -+% 244 --%+") shouldBe 244
                bestHighJump("257 %-+ 223 -+% 244 --%+") shouldBe 257
            }
        }
        context("mostExpensive") {
            test("Blank input") {
                mostExpensive(" ") shouldBe ""
            }
            test("Empty input") {
                mostExpensive("") shouldBe ""
            }
            test("Equal prices") {
                mostExpensive("Огурцы 39.9; Мандарины 39.9") shouldBe "Мандарины"
            }
            test("Symbolic price") {
                mostExpensive("Носочки прикол; Мандарины 39.9") shouldBe ""
            }
            test("Plus sign front number") {
                mostExpensive("Огурцы +39.9; Мандарины 37.9") shouldBe "Огурцы"
            }
            test("Negative numbers") {
                mostExpensive("Огурцы -39.9; Мандарины -37.9") shouldBe ""
            }
            test("Start trailing zero") {
                mostExpensive("Огурцы 000035.9; Мандарины 00037.9") shouldBe "Мандарины"
            }
            test("Unicode price") {
                mostExpensive("Кокосы \u0035\u0036.\u0036; Апельсины \u0035\u0035.\u0036") shouldBe "Кокосы"
            }
            test("Double max and min value") {
                mostExpensive("Кокосы ${Double.MIN_VALUE}; Апельсины ${Double.MAX_VALUE}") shouldBe "Апельсины"
            }
            test("Incorrect input") {
                listOf(
                    MostExpensive("Огурцы 39.9; Мандарины 37.9; ", "Огурцы"),
                    MostExpensive("Огурцы 39.9; Мандарины 37.9; Помидоры", "Огурцы")
                ).forEach { (description, result) ->
                    shouldThrow<IndexOutOfBoundsException> {
                        mostExpensive(description) shouldBe result
                    }
                }
                mostExpensive("Огурцы 39.9; Мандарины 37.9; Помидоры ") shouldBe "Огурцы"
                mostExpensive("Огурцы 39.9; Мандарины 37.9;") shouldBe "Огурцы"
                mostExpensive("Огурцы  39.9") shouldBe ""
                mostExpensive(" Огурцы 39.9") shouldBe ""
                mostExpensive("Огурцы 39.9 ") shouldBe "Огурцы"
            }
            test("Correct tests") {
                listOf(
                    MostExpensive("Палочка 41.3; Клавиша 33.7; Рука 44.7; Мышь 46.7", "Мышь"),
                    MostExpensive("Палочка 46.8; Клавиша 33.7; Рука 46.8; Мышь 46.7", "Рука")
                ).forEach { (description, result) ->
                    mostExpensive(description) shouldBe result
                }
            }
        }
    }

}