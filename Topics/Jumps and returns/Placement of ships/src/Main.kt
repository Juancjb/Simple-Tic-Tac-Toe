fun main() {
    var io: String
    var linha = "12345"
    var coluna = "12345"
    for (i in 0..2) {
        io = readln()
        linha = linha.replace(io[0].toString(), "")
        coluna = coluna.replace(io[2].toString(), "")
    }
    for (i in linha) {
        print(i)
        if (i == linha.last()) break
        print(" ")
    }
    println()
    for (i in coluna) {
        print(i)
        if (i == coluna.last()) break
        print(" ")
    }
}

