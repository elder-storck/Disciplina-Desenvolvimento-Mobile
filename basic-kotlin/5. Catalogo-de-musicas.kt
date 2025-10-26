/*
Imagine que você precise criar um app de reprodução de música.

Crie uma classe que represente a estrutura de uma música. A classe Song precisa incluir estes elementos de código:

    Propriedades do título, artista, ano de lançamento e contagem de reprodução.
    Uma propriedade que indica se a música é famosa. Se a contagem de reprodução for menor que 1.000, considere que não é famosa.
    Um método que mostra uma descrição de música neste formato:

"[Título], de [artista], lançado em [ano de lançamento]."
*/

class Music constructor (val title: String,val artist: String,val releaseYear: Int,val playCount : Int){
	val isFamous: Boolean
        get() = playCount >= 1000    
    
    fun display() {
        println("$title, de $artist, lançado em $releaseYear")
    }
}

fun main(){
    
    val music01 = Music("Bohemian Rhapsody", "Queen", 1975, 5000000)
    
    music01.display()
    println("${music01.title} é famosa? ${music01.isFamous}")
}
