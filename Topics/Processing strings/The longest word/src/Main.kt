fun main() {
    val a = readln().split(" ").toMutableList()
    var c = 0
    var d = 0
    var e = ""
    for (i in 0 until a.size) {
        if (d < a[c].length) {
            d = a[c].length
            e = a[c]
        }
        c++
    }
    print(e)
}