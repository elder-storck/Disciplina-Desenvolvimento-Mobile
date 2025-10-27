package org.example

class Collectible(
    name: String,
    amount: Int,
    purchasePrice: Double,
    salePrice: Double,
    productCode: String,
    productType: String,
    val type: CollectibleType,
    val materialType: MaterialType,
    val relevance: Relevance,
) : Product(name, amount, purchasePrice, salePrice, productCode, productType) {
    //val secondaryColor: String = secondary
    fun printCollectibleDetails() {
        println("""
        Collectible Details:
        Name: $name
        Amount: $amount
        Purchase Price: $purchasePrice
        Sale Price: $salePrice
        Product Code: $productCode
        Type: $type
        Material: $materialType
        Relevance: $relevance
    """.trimIndent())
    }
}