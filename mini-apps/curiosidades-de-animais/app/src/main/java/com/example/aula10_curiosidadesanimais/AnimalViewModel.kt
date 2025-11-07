package com.example.aula10_curiosidadesanimais

import androidx.lifecycle.ViewModel

class AnimalViewModel : ViewModel() {

    companion object {
        private val dogFacts = arrayOf(
            "Os cachorros têm cerca de 1.700 papilas gustativas!",
            "O nariz de cada cachorro é único!",
            "Cachorros suam através das patas!",
            "A audição dos cachorros é 10 vezes mais aguçada!",
            "Os cachorros entendem cerca de 250 palavras e gestos!",
            "O olfato canino é entre 10.000 e 100.000 vezes melhor que o humano!",
            "Cachorros sonham! Eles têm a mesma fase REM do sono que os humanos!",
            "Os cachorros se orientam pelo campo magnético da Terra!",
            "A raça de cachorro mais antiga é o Saluki!",
            "Cachorros sentem ciúmes de seus donos!"
        )

        private val catFacts = arrayOf(
            "Os gatos passam 70% de suas vidas dormindo!",
            "Gatos têm 32 músculos em cada orelha!",
            "Os bigodes dos gatos funcionam como sensores!",
            "Gatos não têm clavícula!",
            "Gatos fazem mais de 100 sons diferentes!",
            "Os gatos domesticados mais antigos datam de 9.500 anos atrás!",
            "Gatos usam os bigodes para medir espaços!",
            "O ronronar dos gatos ajuda na cura de ossos!",
            "Gatos não conseguem ver diretamente debaixo do nariz!",
            "Cada gato tem um padrão único de nariz!"
        )
    }

    fun getDogFacts(): String = dogFacts.random()
    fun getCatFacts(): String = catFacts.random()
}