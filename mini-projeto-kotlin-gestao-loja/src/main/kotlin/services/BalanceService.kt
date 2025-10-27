package org.example.services

import org.example.Product
import java.io.File
import java.text.DecimalFormat
import kotlin.collections.forEach

class BalanceService {

    /**
     * Cria um CSV com balanço das compras e vendas (Compras, vendas, balancete).
     *
     * @param  String que é o path para a pasta que será criado o csv.
     * @param MutableListde de produtos em estoque.
     */
    fun criarBalanceteCSV(filePath: String, produtos: MutableList<Product>, produtosComprados: MutableList<Product>) {
        val path = filePath + "/balancete.csv"

        val arquivo = File(path)

        arquivo.printWriter().use { out ->
            var sumPurchase : Double = 0.0
            var sumSale : Double = 0.0

            // Somando por categoria
            for(i in produtos.indices) {
                sumPurchase += (produtosComprados[i].amount * produtosComprados[i].purchasePrice)
                sumSale += (produtosComprados[i].amount - produtos[i].amount) * produtos[i].salePrice
            }

            val df = DecimalFormat("#.##") // remove zeros desnecessários

            // Escrevendo cada produto em uma linha
            out.println("COMPRAS,${df.format(sumPurchase)}")
            out.println("VENDAS,${df.format(sumSale)}")
            out.println("BALANCETE,${df.format(sumSale-sumPurchase)}")
        }
    }
}