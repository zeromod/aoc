import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.math.abs

Path("../../resources/aoc2024/day02").readLines().also { lines ->
    val levels = lines.map { line -> line.split(" ").map { it.toLong() } }
    levels.map { isLineSafe(it) }.count { it }.also(::println)
    levels.map { line ->
        line.indices.any {
            isLineSafe(line.toMutableList().apply { removeAt(it) })
        }
    }.count { it }.also(::println)

}
//236,308

fun isLineSafe(levels: List<Long>): Boolean {
    var increasing: Boolean? = null
    return levels.zipWithNext { a, b ->
        val diff = a - b
        when (diff) {
            in 1..3 -> {
                if (increasing == false) {
                    false
                } else {
                    increasing = true
                    true
                }
            }

            in -3..-1 -> {
                if (increasing == true) {
                    false
                } else {
                    increasing = false
                    true
                }
            }

            else -> false
        }
    }.all { it }
}