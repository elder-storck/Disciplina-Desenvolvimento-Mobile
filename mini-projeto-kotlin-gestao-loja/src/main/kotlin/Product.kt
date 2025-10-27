package org.example

open class Product (val name : String, var amount : Int, val purchasePrice : Double, val salePrice : Double, val productCode : String, val productType : String) {
    fun displayProduct() {
    println("$amount do product $name, com o codigo $productCode foi comprado por $purchasePrice e vendido por $salePrice")
    }
}