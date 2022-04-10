package lesson6.task1.fuzzing

import com.pholser.junit.quickcheck.Property
import edu.berkeley.cs.jqf.fuzz.JQF
import lesson6.task1.bestHighJump
import lesson6.task1.mostExpensive
import org.junit.runner.RunWith

@RunWith(JQF::class)
class FuzzingTest {
    @Property(trials = 1000)
    fun bestHighJumpTest(jumps: String) {
        bestHighJump(jumps)
    }

    @Property(trials = 1000)
    fun mostExpensiveTest(description: String) {
        try {
            mostExpensive(description)
        } catch (e: IndexOutOfBoundsException) {
            e.printStackTrace()
        }
    }
}