package lesson6.task1.fuzzing

import com.pholser.junit.quickcheck.generator.InRange
import edu.berkeley.cs.jqf.fuzz.Fuzz
import edu.berkeley.cs.jqf.fuzz.JQF
import lesson6.task1.bestHighJump
import lesson6.task1.mostExpensive
import org.junit.Assume.assumeTrue
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.runner.RunWith
import java.util.regex.Pattern


@RunWith(JQF::class)
class GenFuzzingTest {

    companion object {
        private const val REGEX = "(([0-9]+ (\\+|-|%)+)( [0-9]+ (\\+|-|%)+)*)"
    }

    @Fuzz
    fun incorrectInputTest(string: String) {
        val pattern = Pattern.compile(REGEX)
        val matcher = pattern.matcher(string)
        assumeTrue(!matcher.find())
        val res: Int = bestHighJump(string)
        assertEquals(-1, res)
    }

    @Fuzz
    fun correctInputTest(
        @InRange(min = "1") res1: Int,
        @InRange(min = "1") res2: Int,
        @InRange(min = "1") res3: Int
    ) {
        val sb = StringBuilder()
        sb.append("$res1 --+ $res2 -+ $res3 +")
        val max = listOf(res1, res2, res3).maxOrNull()
        val res = bestHighJump(sb.toString())
        assertEquals(max, res)
    }

    @Fuzz
    fun incorrectAttemptsTest(res1: Int, res2: Int) {
        val res = bestHighJump("$res1 $res2")
        assertEquals(res, -1)
    }

    @Fuzz
    fun noAttemptsTest(
        @InRange(min = "1") res1: Int,
        @InRange(min = "1") res2: Int
    ) {
        val sb = StringBuilder()
        sb.append("$res1")
        val res = bestHighJump(sb.toString())
        assertEquals(res, -1)
    }

    @Fuzz
    fun emptyDescriptionTest() {
        val res = mostExpensive("")
        assertEquals(res, "")
    }

    @Fuzz
    fun correctDescriptionTest(
        item1: String, price1: Double,
        item2: String, price2: Double,
        item3: String, price3: Double
    ) {
        val res = mostExpensive("$item1 $price1; $item2 $price2; $item3 $price3")
        val actual = when (listOf(price1, price2, price3).maxOrNull()) {
            price1 -> item1
            price2 -> item2
            price3 -> item3
            else -> ""
        }
        assertEquals(res, actual)
    }

    @Fuzz
    fun incorrectPriceTest(
        item1: String, price1: Double,
        item2: String, price2: Double,
        item3: String, price3: String
    ) {
        val res = mostExpensive("$item1 $price1; $item2 $price2; $item3 $price3")
        val actual = when (listOf(price1, price2).maxOrNull()) {
            price1 -> item1
            price2 -> item2
            else -> ""
        }
        assertEquals(res, actual)
    }
}