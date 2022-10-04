fun main() {
    val io = readln()
    var test = 0
    var result = 0
    for (i in io) {
        for (j in io) {
            if (i == j) test ++
        }
        if (test == 1) result ++
        test = 0
    }
    println(result)
}