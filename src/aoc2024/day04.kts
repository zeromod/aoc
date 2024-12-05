import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.math.max
import kotlin.math.min

Path("../../resources/aoc2024/day04").readLines().let { lines ->
    var count = 0
    fun String.isValid() {
        if (this == "XMAS") count++
        if (this == "SAMX") count++
    }

    fun IntRange.fixedOrder() = if (first > last) {
        buildList {
            var i = first
            while (i >= last) add(i--)
        }
    } else toList()

    lines.forEachIndexed { r, line ->
        line.forEachIndexed { c, char ->
            if (char == 'X') {
                val left = c - 3
                val right = c + 3
                val down = r + 3
                val up = r - 3
                runCatching { line.substring(startIndex = min(c, left), endIndex = max(c, left) + 1).isValid() }
                runCatching { line.substring(startIndex = min(c, right), endIndex = max(c, right) + 1).isValid() }
                runCatching { (r..up).fixedOrder().map { lines[it][c] }.joinToString("").isValid() }
                runCatching { (r..down).fixedOrder().map { lines[it][c] }.joinToString("").isValid() }

                var colPointer = c
                runCatching {
                    (r..down).fixedOrder().map { lines[it][colPointer--] }.joinToString("").isValid()
                }

                colPointer = c
                runCatching {
                    (r..down).fixedOrder().map { lines[it][colPointer++] }.joinToString("").isValid()
                }
                colPointer = c
                runCatching {
                    (r..up).fixedOrder().map { lines[it][colPointer--] }.joinToString("").isValid()
                }
                colPointer = c

                runCatching {
                    (r..up).fixedOrder().map { lines[it][colPointer++] }.joinToString("").isValid()
                }
            }
        }
    }
    count
}.also(::println)
//2496

Path("../../resources/aoc2024/day04").readLines().let { lines ->
    var count = 0
    fun String.isValid(): Boolean {
        println(this)
        return when {
            this == "MAS" -> true
            else -> this == "SAM"
        }
    }
    lines.forEachIndexed { r, line ->
        line.forEachIndexed { c, char ->
            if (char == 'A') {
                println("A in $r:$c")
                runCatching {
                    val isValid = buildString {
                        append(lines[r - 1][c - 1])
                        append(lines[r][c])
                        append(lines[r + 1][c + 1])
                    }.isValid() &&
                            buildString {
                                append(lines[r - 1][c + 1])
                                append(lines[r][c])
                                append(lines[r + 1][c - 1])
                            }.isValid()
                    if (isValid) count += 1
                }
            }
        }
    }
    count
}
//1967