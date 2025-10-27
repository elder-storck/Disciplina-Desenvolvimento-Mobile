package org.example.services

import org.example.Clothing
import org.example.ClothingSize
import org.example.ClothingType
import org.example.Collectible
import org.example.CollectibleType
import org.example.Electronic
import org.example.ElectronicType
import org.example.MaterialType
import org.example.Product
import org.example.Relevance
import java.io.File
import java.text.Normalizer

class SearchService {
    /**
     * Normaliza o texto removendo acentos e convertendo para maiúsculas.
     *
     * @param texto O texto a ser normalizado.
     * @return O texto normalizado sem acentos e em maiúsculas.
     */
    fun normalizarTexto(texto: String): String {
        if (texto.isBlank() || texto == "-") return ""
        val semAcento = Normalizer.normalize(texto, Normalizer.Form.NFD)
            .replace(Regex("\\p{InCombiningDiacriticalMarks}+"), "")
        return semAcento.uppercase().trim()
    }


    /**
     * Mapeia o valor da categoria do CSV para o formato interno.
     *
     * @param csvValue O valor da categoria do arquivo CSV.
     * @return A categoria mapeada ou null se não for reconhecida.
     */
    private fun mapearCategoria(csvValue: String): String? {
        if (csvValue == "-" || csvValue.isBlank()) return null
        return when (normalizarTexto(csvValue)) {
            "ROUPA" -> "ROUPA"
            "ELETRONICO", "VIDEOGAME", "VIDEO-GAME" -> "ELETRONICO"
            "COLECIONAVEL", "BONECO" -> "COLECIONAVEL"
            else -> null
        }
    }


    /**
     * Mapeia o valor do tipo do CSV para o formato interno.
     *
     * @param csvValue O valor do tipo do arquivo CSV.
     * @return Um par contendo o tipo mapeado e um booleano indicando se é busca por video-game.
     */
    private fun mapearTipo(csvValue: String): Pair<String?, Boolean> {
        if (csvValue == "-" || csvValue.isBlank()) return Pair(null, false)
        val valorNormalizado = normalizarTexto(csvValue)

        if (valorNormalizado == "VIDEOGAME" || valorNormalizado == "VIDEO-GAME") {
            return Pair("ELETRONICO", true)
        }

        val tipo = when (valorNormalizado) {
            "CAMISA", "MOLETOM", "ACESSORIO" -> valorNormalizado
            "JOGO", "PORTATIL", "OUTROS" -> valorNormalizado
            "BONECO", "LIVRO" -> valorNormalizado
            else -> null
        }
        return Pair(tipo, false)
    }


    /**
     * Realiza a busca e salva o resultado em arquivo CSV.
     *
     * @param inputPath O caminho para a pasta de entrada.
     * @param outputPath O caminho para a pasta de saída.
     * @param produtos A lista de produtos disponíveis no estoque.
     */
    fun realizarBuscaESalvarResultado(inputPath: String, outputPath: String, produtos: MutableList<Product>) {
        val inPath = "$inputPath/busca.csv"
        val file = File(inPath)

        if (!file.exists()) {
            return
        }

        val quantidades = quantidadeDisponivel(inPath, produtos)
        salvarResultadoCSV(outputPath, quantidades)
    }


    /**
     * Calcula a quantidade disponível de produtos com base nos critérios de busca.
     *
     * @param inputPath O caminho para o arquivo de busca.
     * @param produtos A lista de produtos disponíveis no estoque.
     * @return Uma lista com as quantidades encontradas para cada busca.
     */
    fun quantidadeDisponivel(inputPath: String,  produtos: MutableList<Product>): MutableList<Int> {
        //val inPath = "$inputPath/busca.csv"
        //val outPath = "$inputPath/resultado_busca.csv"
        val file = File(inputPath)

        if (!file.exists()) {
            return mutableListOf()
        }

        val linhas = file.readLines()
        if (linhas.size <= 1) {
            return mutableListOf()
        }

        val quantidades = mutableListOf<Int>()

        for (linha in linhas.drop(1)) {
            try {
                val colunas = linha.split(",").map { it.trim() }

                if (colunas.size >= 9) {
                    var produtosFiltrados = produtos.toList()

                    val categoriaCSV = colunas[0]
                    val tipoCSV = colunas[1]
                    val tamanhoCSV = colunas[2]
                    val corPrimariaCSV = colunas[3]
                    val corSecundariaCSV = colunas[4]
                    val versaoCSV = colunas[5]
                    val anoFabricacaoCSV = colunas[6]
                    val materialCSV = colunas[7]
                    val relevanciaCSV = colunas[8]

                    val categoria = mapearCategoria(categoriaCSV)
                    if (categoria != null) {
                        produtosFiltrados = produtosFiltrados.filter { p ->
                            normalizarTexto(p.productType) == categoria
                        }
                    }

                    val (tipoMapeado, isVideoGameSearch) = mapearTipo(tipoCSV)
                    if (tipoMapeado != null) {
                        if (isVideoGameSearch) {
                            produtosFiltrados = produtosFiltrados.filter { p ->
                                p is Electronic
                            }
                        } else {
                            produtosFiltrados = produtosFiltrados.filter { p ->
                                when (p) {
                                    is Clothing -> normalizarTexto(p.type.name) == tipoMapeado
                                    is Electronic -> normalizarTexto(p.type.name) == tipoMapeado
                                    is Collectible -> normalizarTexto(p.type.name) == tipoMapeado
                                    else -> false
                                }
                            }
                        }
                    }

                    if (tamanhoCSV != "-" && tamanhoCSV.isNotBlank()) {
                        val tamanho = normalizarTexto(tamanhoCSV)
                        produtosFiltrados = produtosFiltrados.filter { p ->
                            p is Clothing && normalizarTexto(p.size.name) == tamanho
                        }
                    }

                    if (corPrimariaCSV != "-" && corPrimariaCSV.isNotBlank()) {
                        val corPrimaria = normalizarTexto(corPrimariaCSV)
                        produtosFiltrados = produtosFiltrados.filter { p ->
                            p is Clothing && normalizarTexto(p.color) == corPrimaria
                        }
                    }

                    if (corSecundariaCSV != "-" && corSecundariaCSV.isNotBlank()) {
                        val corSecundaria = normalizarTexto(corSecundariaCSV)
                        produtosFiltrados = produtosFiltrados.filter { p ->
                            p is Clothing && normalizarTexto(p.secondaryColor) == corSecundaria
                        }
                    }

                    if (versaoCSV != "-" && versaoCSV.isNotBlank()) {
                        val versao = versaoCSV.toIntOrNull()
                        if (versao != null) {
                            produtosFiltrados = produtosFiltrados.filter { p ->
                                p is Electronic && p.version == versao
                            }
                        }
                    }

                    if (anoFabricacaoCSV != "-" && anoFabricacaoCSV.isNotBlank()) {
                        val anoFabricacao = anoFabricacaoCSV.toIntOrNull()
                        if (anoFabricacao != null) {
                            produtosFiltrados = produtosFiltrados.filter { p ->
                                p is Electronic && p.manufactureYear == anoFabricacao
                            }
                        }
                    }

                    if (materialCSV != "-" && materialCSV.isNotBlank()) {
                        val material = normalizarTexto(materialCSV)
                        produtosFiltrados = produtosFiltrados.filter { p ->
                            p is Collectible && normalizarTexto(p.materialType.name) == material
                        }
                    }

                    if (relevanciaCSV != "-" && relevanciaCSV.isNotBlank()) {
                        val relevancia = normalizarTexto(relevanciaCSV)
                        produtosFiltrados = produtosFiltrados.filter { p ->
                            p is Collectible && normalizarTexto(p.relevance.name) == relevancia
                        }
                    }

                    val quantidadeTotal = produtosFiltrados.sumOf { it.amount }
                    quantidades.add(quantidadeTotal)
                }
            } catch (e: Exception) {
                quantidades.add(0)
            }
        }

        return quantidades
    }


    /**
     * Salva o resultado da busca em um arquivo CSV.
     *
     * @param outputPath O caminho para a pasta de saída.
     * @param quantidades A lista de quantidades encontradas para cada busca.
     */
    fun salvarResultadoCSV(outputPath: String, quantidades: MutableList<Int>) {
        val path = "$outputPath/resultado_busca.csv"
        val file = File(path)

        val linhas = mutableListOf<String>()
        linhas.add("BUSCAS,QUANTIDADE")

        for ((index, quantidade) in quantidades.withIndex()) {
            linhas.add("${index + 1},$quantidade")
        }

        file.writeText(linhas.joinToString("\n"))
    }
}