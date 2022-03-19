package lesson7.task1

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.io.File

data class PrintMultiplicationProcess(val lhv: Int, val rhv: Int, val result: String)

private fun assertFileContent(name: String, expectedContent: String) {
    val file = File(name)
    val content = file.readLines().joinToString("\n")
    expectedContent shouldBe content

}

class FilesTest : FunSpec() {
    init {
        fun test(lhv: Int, rhv: Int, res: String) {
            printMultiplicationProcess(lhv, rhv, "temp.txt")
            assertFileContent("temp.txt", res.trimIndent())
            File("temp.txt").delete()
        }

        context("printMultiplicationProcess") {
            test("Input less or equal then zero") {
                listOf(
                    PrintMultiplicationProcess(0, 1356, ""),
                    PrintMultiplicationProcess(-1, 1356, ""),
                    PrintMultiplicationProcess(1356, 0, ""),
                    PrintMultiplicationProcess(1356, -1, "")
                ).forEach { (lhv, rhv, result) ->
                    shouldThrow<IllegalArgumentException> {
                        test(lhv, rhv, result)
                    }
                }
            }
            test("Correct tests") {
                listOf(
                    PrintMultiplicationProcess(
                        84736, 111, """
                           84736
                        *    111
                        --------
                           84736
                        + 84736
                        +84736
                        --------
                         9405696"""
                    ),
                    PrintMultiplicationProcess(
                        87643, 1, """
                         87643
                        *    1
                        ------
                         87643
                        ------
                         87643"""
                    ),
                    PrintMultiplicationProcess(
                        1, 97642, """
                             1
                        *97642
                        ------
                             2
                        +   4
                        +  6
                        + 7
                        +9
                        ------
                         97642"""
                    ),
                    PrintMultiplicationProcess(
                        84736, 4769, """
                                  84736
                             *     4769
                             ----------
                                 762624
                             +  508416
                             + 593152
                             +338944
                             ----------
                              404105984"""
                    )
                ).forEach { (lhv, rhv, result) ->
                    test(lhv, rhv, result)
                }
            }
        }
    }
}