package lesson7.task1.fuzzing

import com.pholser.junit.quickcheck.Property
import edu.berkeley.cs.jqf.fuzz.JQF
import lesson7.task1.printMultiplicationProcess
import org.junit.runner.RunWith
import java.io.File

@RunWith(JQF::class)
class FuzzingTests {

    companion object {
        private const val OUTPUT = "output.txt"
    }

    @Property(trials = 1000)
    fun printMultiplicationProcessTest(lhv: Int, rhv: Int) {
        try {
            printMultiplicationProcess(lhv, rhv, OUTPUT)
        } catch (ex: IllegalArgumentException) {
            ex.printStackTrace()
        }
        File(OUTPUT).delete()
    }
}