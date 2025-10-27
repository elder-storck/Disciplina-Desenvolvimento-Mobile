package org.example.services
import org.example.Clothing
import org.example.ClothingSize
import org.example.ClothingType
import org.example.CollectibleType
import org.example.Electronic
import org.example.ElectronicType
import org.example.MaterialType
import org.example.Product
import org.example.Relevance
import org.example.Collectible
import java.text.Normalizer
import java.io.File

class PurchaseSaleService {
    /**
     * Remove acentos de uma string e transforma todos os caracteres em maiúsculas.
     *
     * @param texto A string de entrada que será normalizada.
     * @return Uma nova string sem acentos e em letras maiúsculas.
     */
    public fun normalizeText(texto: String): String {
        val semAcento = Normalizer.normalize(texto, Normalizer.Form.NFD)
            .replace(Regex("\\p{InCombiningDiacriticalMarks}+"), "")
        return semAcento.uppercase()
    }

    /**
     * Ler o CSV de compras e retorna uma lista de produtos que foram comprados.
     *
     * @param  O path para a pasta que contém o csv.
     * @return Uma lista de produtos que foram comprados.
     */
    fun readCsvPurchases(filePath: String): MutableList<Product> {
        val path = filePath + "/compras.csv"
        val linhas = File(path).readLines()
        val produtos = mutableListOf<Product>()

        for (linha in linhas.drop(1)) { // drop(1) pula o cabeçalho, se existir
            val colunas = linha.split(",")
            if (colunas.size >= 13) { // Evita erro se faltar coluna
                val amount = colunas[1].toInt()
                val name = normalizeText(colunas[2])
                val purchasePrice = colunas[3].toDoubleOrNull() ?: 0.0
                val salePrice = colunas[4].toDoubleOrNull() ?: 0.0
                val typeproduct = normalizeText(colunas[5])

                if (typeproduct == "ROUPA") {
                    val productCode = "R-" + colunas[0]
                    val type = runCatching {
                        enumValueOf<ClothingType>(colunas[6].trim().uppercase())
                    }.getOrElse { ClothingType.CAMISA }
                    val size = runCatching {
                        enumValueOf<ClothingSize>(colunas[7].trim().uppercase())
                    }.getOrElse { ClothingSize.M }
                    val color = normalizeText(colunas[8])
                    val secondaryColor = normalizeText(colunas[9])

                    val product =
                        Clothing(name, amount, purchasePrice, salePrice, productCode, typeproduct, type, size, color, secondaryColor)
                    produtos.add(product)

                } else if (typeproduct == "ELETRONICO") {
                    val productCode = "E-" + colunas[0]
                    val type = runCatching {
                        enumValueOf<ElectronicType>(colunas[6].trim().uppercase())
                    }.getOrElse { ElectronicType.OUTROS }
                    val version = colunas[10].toInt()
                    val manufacturer = colunas[11].toInt()

                    val product = Electronic(
                        name,
                        amount,
                        purchasePrice,
                        salePrice,
                        productCode,
                        typeproduct,
                        type,
                        version,
                        manufacturer
                    )
                    produtos.add(product)

                } else {
                    val productCode = "C-" + colunas[0]
                    val type = runCatching {
                        enumValueOf<CollectibleType>(colunas[6].trim().uppercase())
                    }.getOrElse { CollectibleType.OUTROS }
                    val materialType = runCatching {
                        enumValueOf<MaterialType>(colunas[12].trim().uppercase())
                    }.getOrElse { MaterialType.MISTURADO }
                    val relevance = runCatching {
                        enumValueOf<Relevance>(colunas[13].trim().uppercase())
                    }.getOrElse { Relevance.COMUM }

                    val product = Collectible(
                        name,
                        amount,
                        purchasePrice,
                        salePrice,
                        productCode,
                        typeproduct,
                        type,
                        materialType,
                        relevance
                    )
                    produtos.add(product)
                }
            } else {
                println("Linha inválida: $linha")
            }
        }
        return produtos
    }


    /**
     * Ler o CSV de vendas e modifica a lista de produtos em estoque.
     *
     * @param  String que é o path para a pasta que contém o csv.
     * @param MutableListde de produtos em estoque.
     */
    fun readCsvSales(filePath: String, produtos: MutableList<Product>) {
        val path = filePath + "/vendas.csv"
        //obtem as linhas
        val linhas = File(path).readLines()

        // percorre cada linha e descarta a primeira (cabeçalho)
        for (linha in linhas.drop(1)) {
            val colunas = linha.split(",")
            // garante a existencia de todas as colunas
            if (colunas.size >= 2) {
                // Pega o código e a quantidade do CSV
                val codigo = normalizeText(colunas[0])
                val quantidade = colunas[1].toIntOrNull() ?: 0

                // Procura o produto pelo codigo
                val prod = produtos.find { it.productCode == codigo }
                if (prod != null) {
                    // Modifica a quantidade daquele produto
                    prod.amount -= quantidade
                } else {
                    println("Produto não encontrado: $codigo")
                }
            } else {
                println("Linha inválida: $linha")
            }
        }

    }
}