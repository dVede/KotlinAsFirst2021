package lesson5.task1.fuzzing

import com.pholser.junit.quickcheck.Property
import edu.berkeley.cs.jqf.fuzz.JQF
import lesson5.task1.extractRepeats
import lesson5.task1.findSumOfTwo
import lesson6.task1.bestHighJump
import lesson6.task1.mostExpensive
import org.junit.runner.RunWith

@RunWith(JQF::class)
class FuzzingTests {
    @Property(trials = 1000)
    fun extractRepeatsTest(list: List<String>) {
        extractRepeats(list)
    }

    @Property(trials = 1000)
    fun findSumOfTwoTest(list: List<Int>, number: Int) {
        findSumOfTwo(list, number)
    }
}