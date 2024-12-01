import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.math.abs

Path("../resources/aoc2024/day01").readLines().let { lines ->
    lines.map {
        it.substringBefore("   ").toLong() to it.substringAfter("   ").toLong()
    }.unzip().let { (first, second) ->
        val result1 = first.sorted().zip(second.sorted()).sumOf { abs(it.first - it.second) }

        val frequency = second.groupingBy { it }.eachCount()
        val result2 = first.sumOf { num -> num * frequency.getOrDefault(num, 0) }

        println(result1)
        println(result2)
    }
}
//(1830467, 26674158)