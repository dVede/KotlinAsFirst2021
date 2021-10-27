@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import kotlin.math.abs
import kotlin.math.sqrt
import kotlin.math.*

// Урок 4: списки
// Максимальное количество баллов = 12
// Рекомендуемое количество баллов = 8
// Вместе с предыдущими уроками = 24/33

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.lowercase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая (2 балла)
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double {
    var a = 0.0
    for (i in v.indices) {
        a += sqr(v[i])
    }
    return if (v.size == 0) 0.0
    else sqrt(a)
}

/**
 * Простая (2 балла)
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    return if (list.size == 0) 0.0
    else list.sum() / list.size
}

/**
 * Средняя (3 балла)
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    return if (list.size == 0) list
    else {
        val a = list.sum() / list.size
        for (i in 0 until list.size) {
            list[i] -= a
        }
        list
    }
}

/**
 * Средняя (3 балла)
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int {
    return if (a.size == 0 && b.size == 0) 0
    else {
        var c = 0
        for (i in a.indices) {
            c += a[i] * b[i]
        }
        return c
    }
}

/**
 * Средняя (3 балла)
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int): Int = TODO()

/**
 * Средняя (3 балла)
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> = TODO()

/**
 * Средняя (3 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> = TODO()

/**
 * Сложная (4 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String = TODO()

/**
 * Средняя (3 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> = TODO()

/**
 * Сложная (4 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */
fun convertToString(n: Int, base: Int): String = TODO()

/**
 * Средняя (3 балла)
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int = TODO()

/**
 * Сложная (4 балла)
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */
fun decimalFromString(str: String, base: Int): Int {
    val map = mapOf(
        "0" to 0, "1" to 1, "2" to 2, "3" to 3, "4" to 4, "5" to 5,
        "6" to 6, "7" to 7, "8" to 8, "9" to 9, "a" to 10, "b" to 11,
        "c" to 12, "d" to 13, "e" to 14, "f" to 15, "g" to 16, "h" to 17,
        "i" to 18, "j" to 19, "k" to 20, "l" to 21, "m" to 22, "n" to 23,
        "o" to 24, "p" to 25, "q" to 26, "r" to 27, "s" to 28, "t" to 29,
        "u" to 30, "v" to 31, "w" to 32, "x" to 33, "y" to 34, "z" to 35
    )
    var result = 0
    val count = str.length
    val b = str.reversed()
    for (i in 0 until count) {
        val a = b[i]
        result += map.getOrDefault("$a", 0) * ((base.toDouble()).pow(i)).toInt()
    }
    return result
}

/**
 * Сложная (5 баллов)
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String = TODO()

/**
 * Очень сложная (7 баллов)
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    val firstMap = mapOf(
        1 to "сто", 2 to "двести", 3 to "триста", 4 to "четыреста",
        5 to "пятьсот", 6 to "шестьсот", 7 to "семьсот", 8 to "восемьсот",
        9 to "девятьсот", 20 to "двадцать", 30 to "тридцать",
        40 to "сорок", 50 to "пятьдесят", 60 to "шестьдесят",
        70 to "семьдесят", 80 to "восемьдесят", 90 to "девяносто"
    )
    val secondMap = mapOf(
        1 to "один", 2 to "два", 3 to "три", 4 to "четыре",
        5 to "пять", 6 to "шесть", 7 to "семь", 8 to "восемь",
        9 to "девять", 10 to "десять", 11 to "одиннадцать",
        12 to "двенадцать", 13 to "тринадцать", 14 to "четырнадцать",
        15 to "пятнадцать", 16 to "шестнадцать", 17 to "семнадцать",
        18 to "восемнадцать", 19 to "девятнадцать"
    )
    var num = n
    var count = 0
    var result = ""
    while (num > 0) {
        count += 1
        num /= 10
    }
    val counter = count
    while (count > 0) {
        when (count) {
            6 -> {
                val a = n / (10.0.pow(5)).toInt()
                result += firstMap[a]
                count -= 1
            }
            5 -> {
                val a = (n / (10.0.pow(4)).toInt()) % 10
                if (a == 0) {
                    result += ""
                    count -= 1
                } else {
                    if (counter == 5) {
                        if (a == 1) {
                            val b = (n / (10.0.pow(3)).toInt()) % 100
                            result += secondMap[b]
                            result += " тысяч"
                            count -= 2
                        } else {
                            result += firstMap[a * 10]
                            count -= 1
                        }
                    } else {
                        result += " "
                        if (a == 1) {
                            val b = (n / (10.0.pow(3)).toInt()) % 100
                            result += secondMap[b]
                            result += " тысяч"
                            count -= 2
                        } else {
                            result += firstMap[a * 10]
                            count -= 1
                        }
                    }
                }
            }
            4 -> {
                val a = (n / (10.0.pow(3)).toInt()) % 10
                if (a == 0) {
                    result += " тысяч"
                    count -= 1
                } else {
                    if (counter == 4) {
                        result += when (a) {
                            1 -> "одна"
                            2 -> "две"
                            else -> secondMap[a]
                        }
                        result += when (a) {
                            1 -> " тысяча"
                            2, 3, 4 -> " тысячи"
                            else -> " тысяч"
                        }
                        count -= 1
                    } else {
                        result += " "
                        result += when (a) {
                            1 -> "одна"
                            2 -> "две"
                            else -> secondMap[a]
                        }
                        result += when (a) {
                            1 -> " тысяча"
                            2, 3, 4 -> " тысячи"
                            else -> " тысяч"
                        }
                        count -= 1
                    }
                }
            }
            3 -> {
                val a = (n / (10.0.pow(2)).toInt()) % 10
                if (a == 0) {
                    result += ""
                    count -= 1
                } else {
                    if (counter == 3) {
                        result += firstMap[a]
                        count -= 1
                    } else {
                        result += " "
                        result += firstMap[a]
                        count -= 1
                    }
                }
            }
            2 -> {
                val a = (n / (10)) % 10
                if (a == 0) {
                    result += ""
                    count -= 1
                } else {
                    if (counter == 2) {
                        if (a == 1) {
                            val b = n % 100
                            result += secondMap[b]
                            count -= 2
                        } else {
                            result += firstMap[a * 10]
                            count -= 1
                        }
                    } else {
                        result += " "
                        if (a == 1) {
                            val b = n % 100
                            result += secondMap[b]
                            count -= 2
                        } else {
                            result += firstMap[a * 10]
                            count -= 1
                        }
                    }
                }
            }
            1 -> {
                val a = n % 10
                if (a == 0) {
                    result += ""
                    count -= 1
                } else {
                    if (counter == 1) {
                        result += secondMap[a]
                        count -= 1
                    } else {
                        result += " "
                        result += secondMap[a]
                        count -= 1
                    }
                }
            }
        }
    }
    return result
}