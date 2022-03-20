package lesson7.task1

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.assertions.withClue
import io.kotest.core.spec.style.FunSpec
import io.kotest.core.test.TestCase
import io.kotest.core.test.TestResult
import io.kotest.matchers.file.shouldBeAFile
import io.kotest.matchers.file.shouldExist
import io.kotest.matchers.file.shouldHaveExtension
import io.kotest.matchers.file.shouldHaveName
import io.kotest.matchers.paths.shouldContainFile
import io.kotest.matchers.shouldBe
import java.io.File
import java.nio.file.Paths


data class PrintMultiplicationProcess(val lhv: Int, val rhv: Int, val result: String)

private fun assertFileContent(name: String, expectedContent: String) {
    val file = File(name)
    val content = file.readLines().joinToString("\n")
    expectedContent shouldBe content
}
abstract class MySpec(
    val fileName: String
) : FunSpec() {

    override fun afterTest(testCase: TestCase, result: TestResult) {
        super.afterTest(testCase, result)
        System.gc()
        File(fileName).delete()
    }
}

class FilesTest : MySpec(fileName = "test.txt") {
    init {
        fun test(lhv: Int, rhv: Int, res: String) {
            printMultiplicationProcess(lhv, rhv, fileName)
            assertFileContent(fileName, res.trimIndent())
        }
        context("printMultiplicationProcess") {
            test("file created") {
                printMultiplicationProcess(111, 111, fileName)
                val file = File(fileName)
                withClue("is in directory") {
                    Paths.get("").shouldContainFile(fileName)
                }
                withClue("is exist") {
                    file.shouldExist()
                }
                withClue("is file") {
                    file.shouldBeAFile()
                }
                withClue("with extesion") {
                    file.shouldHaveExtension(".txt")
                }
                withClue("with name") {
                    file.shouldHaveName(fileName)
                }
            }
            test("Input less or equal then zero") {
                listOf(
                    PrintMultiplicationProcess(0, 1356, ""),
                    PrintMultiplicationProcess(-1, 1356, ""),
                    PrintMultiplicationProcess(1356, 0, ""),
                    PrintMultiplicationProcess(1356, -1, "")
                ).forEach { (lhv, rhv, result) ->
                    shouldThrowExactly<IllegalArgumentException> {
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