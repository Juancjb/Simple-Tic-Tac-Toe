fun main() {
    val s1 = readln()
    val op = readln()
    val s2 = readln()
    println(
        when (op) {
            "equals" -> s1 == s2
            "plus" -> s1 + s2
            "endsWith" -> s1.substring((s1.length - s2.length)) == s2
            else -> "Unknown operation"
        }
    )
}