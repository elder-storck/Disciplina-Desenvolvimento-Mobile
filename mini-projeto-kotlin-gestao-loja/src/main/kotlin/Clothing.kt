package org.example

class Clothing(
    name: String,
    amount: Int,
    purchasePrice: Double,
    salePrice: Double,
    productCode: String,
    productType: String,
    val type: ClothingType,
    val size: ClothingSize,
    val color: String,
    val secondaryColor: String,
) : Product(name, amount, purchasePrice, salePrice, productCode, productType) {
    //val secondaryColor: String = secondary

    fun printClothingDetails() {
        println("""
        Clothing Details:
        Name: $name
        amount: $amount
        Purchase Price: $purchasePrice
        Sale Price: $salePrice
        Product Code: $productCode
        Type: $type
        Size: $size
        Color: $color
    """.trimIndent())
    }
}