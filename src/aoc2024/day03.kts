import kotlin.io.path.Path
import kotlin.io.path.readText

Path("../../resources/aoc2024/day03").readText().let { text ->
    "mul\\((\\d+),(\\d+)\\)".toRegex().findAll(text).sumOf {
        val x = it.value.substringAfterLast("(").substringBeforeLast(",").toLong()
        val y = it.value.substringAfterLast(",").substringBeforeLast(")").toLong()
        x * y
    }
}.also(::println)
//179834255

Path("../../resources/aoc2024/day03").readText().let { text ->
    var valid = true
    "(mul\\((\\d+),(\\d+)\\))|(don't\\(\\))|(do\\(\\))".toRegex().findAll(text)
        .mapNotNull { match ->
            when (match.value) {
                "do()" -> valid = true
                "don't()" -> valid = false
            }
            when {
                valid && match.value != "do()" -> match.value
                else -> null
            }
        }.sumOf {
            val x = it.substringAfterLast("(").substringBeforeLast(",").toLong()
            val y = it.substringAfterLast(",").substringBeforeLast(")").toLong()
            x * y
        }
}
//80570939
