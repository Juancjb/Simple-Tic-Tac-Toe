fun main() {
    val io = readln()
    for (i in io) {
        if (i.isDigit()) {
            println(i)
            break
        }
    }
}