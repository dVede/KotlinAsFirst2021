package lesson5.task1.fuzzing

import com.pholser.junit.quickcheck.generator.Size
import edu.berkeley.cs.jqf.fuzz.Fuzz
import edu.berkeley.cs.jqf.fuzz.JQF
import lesson5.task1.extractRepeats
import lesson5.task1.findSumOfTwo
import org.junit.jupiter.api.Assertions
import org.junit.runner.RunWith
import kotlin.random.Random

@RunWith(JQF::class)
class GenFuzzingTest {

    @Fuzz
    fun correctFindSumOfTwoTest(@Size(min = 2, max = 20) list: List<Int>) {
        val index1 = list.size - 1
        val element1 = list[index1]
        val index2: Int = Random.nextInt(0, index1)
        val element2 = list[index2]
        val res = findSumOfTwo(list, element1 + element2)
        Assertions.assertEquals(res, Pair(index2, index1))
    }

    @Fuzz
    fun emptyFindSumOfTwoTest(@Size(min = 0, max = 0) list: List<Int>, number: Int) {
        val res = findSumOfTwo(list, number)
        Assertions.assertEquals(res, Pair(-1, -1))
    }

    @Fuzz
    fun correctExtractRepeats(@Size(min = 1, max = 10) list: List<String>) {
        val size = list.size
        val newList = mutableListOf<String>()
        val loopIndex: Int = Random.nextInt(1, 30)
        for (i in 0 until loopIndex) {
            val index: Int = Random.nextInt(0, size)
            newList.add(list[index])
        }
        newList.addAll(list)

        val res = extractRepeats(newList)
        Assertions.assertNotEquals(emptyMap<String, Int>(), res)
    }

    @Fuzz
    fun allUniqTest(list: List<String>) {
        val newList = list.map { it }.toSet().toList()

        val res = extractRepeats(newList)
        Assertions.assertEquals(emptyMap<String, Int>(), newList)
    }
}