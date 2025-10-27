package org.example.services

import org.example.Clothing
import org.example.Product
import java.io.File


class StockManagementService {

    /**
     * Cria um CSV com todos os itens do estoque com (código, nome, quantidade).
     *
     * @param  String que é o path para a pasta que será criado o csv.
     * @param MutableListde de produtos em estoque.
     */
    fun criarEstoqueGeralCSV(filePath: String, produtos: MutableList<Product>) {
        val path = filePath + "/estoque_geral.csv"

        val arquivo = File(path)

        // Escreve o cabeçalho
        arquivo.printWriter().use { out ->
            //Escrevendo cabeçalho
            out.println("CODIGO,NOME,QUANTIDADE")

            // Escrevendo cada produto em uma linha
            for (p in produtos) {
                val linha = "${p.productCode},${p.name},${p.amount}"
                out.println(linha)
            }
        }
    }


    /**
     * Cria um CSV com todas as categorias do estoque com (Categoria, quantidade).
     *
     * @param  String que é o path para a pasta que será criado o csv.
     * @param MutableListde de produtos em estoque.
     */
    fun criarEstoquePorCategoriaCSV(filePath: String, produtos: MutableList<Product>) {
        val path = filePath + "/estoque_categorias.csv"

        val arquivo = File(path)

        // Escreve o cabeçalho
        arquivo.printWriter().use { out ->
            //Escrevendo cabeçalho
            out.println("CATEGORIA,QUANTIDADE")

            var sumClothings : Int = 0
            var sumCollectible : Int = 0
            var sumElectronic : Int = 0

            // Somando por categoria
            produtos.forEach {
                if(it.productType == "ROUPA") sumClothings += it.amount
                if(it.productType == "COLECIONAVEL") sumCollectible += it.amount
                if(it.productType == "ELETRONICO") sumElectronic += it.amount
            }

            // Escrevendo cada produto em uma linha
            out.println("ROUPA,${sumClothings}")
            out.println("COLECIONAVEL,${sumCollectible}")
            out.println("ELETRONICO,${sumElectronic}")
        }
    }
}