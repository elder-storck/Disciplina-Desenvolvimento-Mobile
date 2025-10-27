package org.example

import org.example.services.BalanceService
import org.example.services.PurchaseSaleService
import org.example.services.SearchService
import org.example.services.StockManagementService

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main(args: Array<String>) {
    // Verificar se foram passados os argumentos necessários
    if (args.size < 2) {
        println("Uso: java -jar miniprojeto.jar <pasta_entrada> <pasta_saida>")
        return
    }

    val inputPath = args[0]
    val outputPath = args[1]

    // Garantir que a pasta de saída existe
    val outputDir = java.io.File(outputPath)
    if (!outputDir.exists()) {
        outputDir.mkdirs()
    }
    //val inputPath : String = "/home/elder/IdeaProjects/miniprojeto/src/main/resources/exemplo_4/entrada/"
    //val outputPath : String = "/home/elder/IdeaProjects/miniprojeto/src/main/resources/exemplo_4/saida/"



    // Lendo compras.csv
    val purchaseService = PurchaseSaleService()
    val products = purchaseService.readCsvPurchases(inputPath)

    // Guardando a quantidade de products comprados
    val purchasedProducts: MutableList<Product> = products.map { p ->
        Product( name = p.name, amount = p.amount, purchasePrice = p.purchasePrice, salePrice = p.salePrice, productCode = p.productCode, productType = p.productType)
    }.toMutableList()

    // Lendo planilha com as vendas
    purchaseService.readCsvSales(inputPath, products)

    // Criando planilhas do estoque
    val stockManagementService = StockManagementService()
    stockManagementService.criarEstoqueGeralCSV(outputPath, products)
    stockManagementService.criarEstoquePorCategoriaCSV(outputPath, products)

    // Criando planilha de balancete
    val balanceService = BalanceService()
    balanceService.criarBalanceteCSV(outputPath, products, purchasedProducts)

    /*
    for (p in products) {
        when (p) {
            is Clothing -> p.printClothingDetails()
            is Electronic -> p.printElectronicsDetails()
            is Collectible -> p.printCollectibleDetails()
            else -> println("Produto genérico: ${p.name}")
        }
        println()
    }*/

    // Criando planilha com busca
    val searchService = SearchService()
    searchService.realizarBuscaESalvarResultado(inputPath,outputPath, products)

}