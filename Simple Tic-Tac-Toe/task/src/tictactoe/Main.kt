package tictactoe

fun main() {
    // Lista 3 x 3 com caracteres de espaço representando o campo vazio
    var entrada = mutableListOf(
        mutableListOf(' ',' ',' '),
        mutableListOf(' ',' ',' '),
        mutableListOf(' ',' ',' ')
    )
    val teste = teste(entrada)// Testa o campo e retorna uma MutableList Int 4 x 2 com a analise do campo
    // Gera uma String com o estado do campo a partir de uma MutableList Char 3 x 3 e uma MutableList Int 4 x 2
    var mensagem = mensagem(entrada, teste)
    val entrada2 = mutableListOf<Int>()// Lista para as novas entradas do jogo
    var cont = 2// Armazena as rodadas para distinção em par e impar, inicia em par
    grade(entrada)// Cria a grade a partir de uma MutableList 3 x 3 e apresenta
    //Testa a grade até que a mensagem "Game not finished" mude
    while (mensagem == "Game not finished") {
        entrada2.clear()// limpa a lista para receber uma nova jogada
        var io2 = readln().split(" ")
        // Testa se a entrada é numérica, se não for reinicia o loop, se for carrega os numeros na lista entrada2
        if (!io2[0][0].isDigit() || !io2[1][0].isDigit()) {
            println("You should enter numbers!")
            continue
        } else {
            // Os numeros são carregados subtraindo 1 para coincidir com o range das listas que inicia em 0 e não em 1
            entrada2.add(io2[0].toInt() - 1)
            entrada2.add(io2[1].toInt() - 1)
        }
        // Testa os numeros carregados em entrada2
        when {
            // Testa o range das entradas
            entrada2[0] !in 0..2 || entrada2[1] !in 0..2 -> println("Coordinates should be from 1 to 3!")
            // Testa se a celula correspondente esta vazia
            entrada[entrada2[0]][entrada2[1]] != ' ' -> println("This cell is occupied! Choose another one!")
            /* Testa se a rodada é par, se for, 'X' é carregado na celula,
             * o campo atualizado e mostrado e a rodada é atualizada
             */
            cont % 2 == 0 -> {
                entrada[entrada2[0]][entrada2[1]] = 'X'
                grade(entrada)
                cont ++
            }
            else -> {
                /* Se nenhuma das alternativas for verdadeira, 'O' é carregado na celula,
                 * o campo atualizado e mostrado e a rodada é atualizada
                 */
                entrada[entrada2[0]][entrada2[1]] = 'O'
                grade(entrada)
                cont ++
            }
        }
        var teste2 = teste(entrada) // O campo atualizado é testado e a lista do estado atualizado é carregada na variavel
        mensagem = mensagem(entrada, teste2) // A mensagem da variavel é atualizada com base no campo atualizado
    }
    // Mensagem final exibida
   println(mensagem)
}
fun teste(campo: MutableList<MutableList<Char>>): MutableList<MutableList<Int>> {
    /*
     * Essa função retorna uma MutableList<Int> 4 x 2 , com a coluna 0 representando o numero de vitórias
     *  e a coluna 1 representando a posição do caracter vencedor na lista de caracteres de entrada
     */
    val coordenada = mutableListOf(
        mutableListOf(0, 0),// linha, posição caracter vencedor
        mutableListOf(0, 0),// coluna, posição caracter vencedor
        mutableListOf(0, 0),// diagonais, posição caracter vencedor
        mutableListOf(0, 0)// quantidade de espaços, não utilizado
    )
    //Percorre o campo e testa vitórias nas linhas, colunas e conta espaços vazios
    for (i in 0..2) {
        when {
            //Testa as linhas
            (campo[i][0] == campo[i][1] && campo[i][0] == campo[i][2])
                    &&
                    (campo[i][0] == 'X' || campo[i][0] == 'O') -> {
                coordenada[0][0]++
                coordenada[0][1] = i
                    }
            //Testa as colunas
            (campo[0][i] == campo[1][i] && campo[0][i] == campo[2][i])
                    &&
                    (campo[0][i] == 'X' || campo[0][i] == 'O') -> {
                coordenada[1][0]++
                coordenada[1][1] = i
                    }
        }
        //Conta espaços vazios
        for (j in 0..2) {
           when {
               campo[i][j] == ' ' -> coordenada[3][0]++
               else -> continue
           }
        }
    }
    // Testa vitória nas diagonais
    if (campo[1][1] == 'X' ||  campo[1][1] =='O') {
        if (
            (campo[0][0] == campo[1][1] && campo[1][1] == campo[2][2])
            ||
            (campo[2][0] == campo[1][1] && campo[1][1] == campo[0][2])
        ) {
            coordenada[2][0]++
            coordenada[2][1] = 1
        }
    }
    return coordenada
}
fun mensagem(ent: MutableList<MutableList<Char>>, test: MutableList<MutableList<Int>>): String {
    /*
     * Essa função retorna uma String a partir da analise comparativa do campo MutableList<Char> 3 x 3
     * com o campo MutableList<Int> 4 x 2
     */
    val cond: String
    val linha = test[0][0]
    val coluna = test[1][0]
    val diagonal = test[2][0]
    val espaco = test[3][0]
    when {
        linha == 1 -> cond = ent[test[0][1]][0] + " wins"
        coluna == 1 -> cond = ent[0][test[1][1]] + " wins"
        diagonal == 1 -> cond = ent[1][1] + " wins"
        espaco > 0 -> cond = "Game not finished"
        else -> cond = "Draw"
    }
    return cond
}
fun grade(entrada: MutableList<MutableList<Char>>) {
    /*
     *Essa função cria a grade a partir de uma lista 3 x 3
     */
    val linha = "-"
    val parede = "|"
    println(linha.repeat(9))
    for (i in 0 until 3) {
        print("$parede ")
        for (j in 0 until 3) {
            print("${entrada[i][j]} ")
        }
        println(parede)
    }
    println(linha.repeat(9))
}