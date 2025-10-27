package org.example

class Electronic(
    name: String,
    amount: Int,
    purchasePrice: Double,
    salePrice: Double,
    productCode: String,
    productType: String,
    val type: ElectronicType,
    val version: Int,
    val manufactureYear: Int,
) : Product(name, amount, purchasePrice, salePrice, productCode, productType) {
    //val secondaryColor: String = secondary
    fun printElectronicsDetails() {
        println("""
        Electronics Details:
        Name: $name
        Amount: $amount
        categoria $type
        Purchase Price: $purchasePrice
        Sale Price: $salePrice
        Product Code: $productCode
        Type: $type
        Version: $version
        Manufacture Year: $manufactureYear
    """.trimIndent())
    }
}