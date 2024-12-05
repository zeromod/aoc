import kotlin.io.path.Path
import kotlin.io.path.readLines

Path("../../resources/aoc2024/day05").readLines().let { lines ->
    val split = lines.indexOf("")
    val (s1, s2) = lines.subList(0, split) to lines.subList(split + 1, lines.size)
    val rules = s1.map { it.split("|").let { rule -> rule[0].toLong() to rule[1].toLong() } }
    val pages = s2.map { it.split(",").map { it.toLong() } }
    pages.partition { page ->
        page.mapIndexed { index, num ->
            page.mapIndexed { cIndex, cNum ->
                when {
                    index != cIndex -> {
                        when {
                            index < cIndex -> rules.any { it.first == num && it.second == cNum }
                            index > cIndex -> rules.any { it.first == cNum && it.second == num }
                            else -> false
                        }
                    }

                    else -> true
                }
            }.all { it }
        }.all { it }
    }.let { (valid, invalid) ->
        valid.sumOf { it[it.size / 2] }.also(::println)

        invalid.map { pages ->
            pages.sortedWith { a, b ->
                when {
                    rules.filter { it.first == a }.map { it.second }.any { it == b } -> -1
                    rules.filter { it.second == a }.map { it.first }.any { it == b } -> 1
                    else -> a.compareTo(b)
                }
            }
        }.sumOf {
            it[it.size / 2]
        }
    }
}
//5166, 4679
