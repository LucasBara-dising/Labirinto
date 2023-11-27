import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.system.exitProcess

//Systema rodanod em koltin

fun main() {
    //cosntroi arena
    //1 = paredes
    //2 = chegada
    val mapa = arrayOf(
        intArrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1),
        intArrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1),
        intArrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1),
        intArrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 1),
        intArrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1),
        intArrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1),
        intArrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1),
        intArrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1),
        intArrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1),
        intArrayOf(1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1),
        intArrayOf(1, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1),
        intArrayOf(1, 0, 0, 0, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1),
        intArrayOf(1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1),
        intArrayOf(1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1),
        intArrayOf(1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 1, 0, 1),
        intArrayOf(1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1),
        intArrayOf(1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 1, 0, 1),
        intArrayOf(1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1),
        intArrayOf(1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1),
        intArrayOf(1, 0, 1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1),
        intArrayOf(1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1),
        intArrayOf(1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1),
        intArrayOf(1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 1, 1),
        intArrayOf(1, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1),
        intArrayOf(1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1),
        intArrayOf(1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1)
    )


    //caminho andado
    val positionY: Queue<Int> = LinkedList()
    val positionX: Queue<Int> = LinkedList()

    //historico
    val historicoY = ArrayDeque<Int>()
    val historicoX = ArrayDeque<Int>()

    //posisão inicial
    //sempre vai comercar na seguda casa da ultima linha
    var x = 1
    var y = mapa.size-1 // pega ultima posiao

    positionX.add(x)
    positionY.add(y)

    var px = 0
    var py = 0

    val op = 0
    var volta=0

    println("---------Comandos-----------")
    println("|        w->Cima           |")
    println("|        A->Esquerda       |")
    println("|        S->Baixo          |")
    println("|        D->Direita        |")
    println("|        V->Volta          |")
    println("----------------------------")

    while (op != 9) {
        printMap(mapa, x, y)

        // Simulação de entrada de teclado
        val scanner = Scanner(System.`in`)
        val ch = scanner.nextLine()

        when (ch) {
            "w" -> {
                px = 0
                py = -1
            }

            "d" -> {
                px = 1
                py = 0
            }

            "s" -> {
                px = 0
                py = 1
            }

            "a" -> {
                px = -1
                py = 0
            }

            "v" -> {
                volta=1
            }
            else->{
                println("Comando incorreto")
                continue
            }
        }

        if (volta==1){
            historicoX.removeLast()
            historicoY.removeLast()

            //guarda a posição do historico
            x=historicoX.last()
            y=historicoY.last()
            volta=0

        }else{
            x += px
            y += py

            //guarda a posição
            positionX.add(x)
            positionY.add(y)

            //coloca no historico
            historicoX.addLast(x)
            historicoY.addLast(y)
        }

        //se bater na borda finaliza
        if (mapa[y][x]==1){
            println("Perdeu")
            exitProcess(0)
        }

        //se chegar ao final
        if (mapa[y][x]==2){
            println("\uD83C\uDF89 Seu caminho! \uD83C\uDF89")
            //mostra caminhao percorrido
            animacaoVitoria(mapa,historicoX,historicoY)
            println("\uD83C\uDF1F Você ganhou! \uD83C\uDF1F")
            exitProcess(0)
        }
    }


    Thread.sleep(500)
}

fun animacaoVitoria(mapa: Array<IntArray>, historicoX : ArrayDeque<Int>, historicoY : ArrayDeque<Int>){
    var cont=0
    do {
        for (n in 1..10){
            println()
        }

        printMap(mapa,historicoX[cont],historicoY[cont])
        cont++
        Thread.sleep(1000)

    }while (cont<historicoX.size)

}

fun printMap(mapa: Array<IntArray>, x: Int, y: Int) {
    for (r in mapa.indices) {//rodando o map
        for (n in mapa[r].indices)
            print(
                when {
                    n == x && r == y -> "@"//personagem
                    mapa[r][n] == 0 -> " "//campo
                    mapa[r][n] == 1 -> "█"//parede
                    mapa[r][n] == 2 -> "#"//chegada
                    else -> "/"
                }
            )
        println()
    }
}

