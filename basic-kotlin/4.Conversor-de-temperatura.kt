/*
Existem três padrões principais de temperatura usados no mundo: Celsius, Fahrenheit e Kelvin.

No código inicial fornecido no snippet abaixo, escreva um programa que converta uma temperatura de um padrão a outro usando estas fórmulas:

    Celsius para Fahrenheit: °F = 9/5 (°C) + 32
    Kelvin para Celsius: °C = K - 273,15
    Fahrenheit para Kelvin: K = 5/9 (°F - 32) + 273,15

O método String.format("%.2f", /* measurement */ ) é usado para converter um número em um tipo String com duas casas decimais.
*/

fun main() {
    val celsius : Double = 27.0
    val kelvin 	: Double = 350.0
    val fahrenheit 	: Double = 10.0
    
    val cTOf = { c: Double -> ((9.0/5.0)*c) + 32.0}
    val kTOc = { k: Double -> k-273.15}
    val fTOk = { f: Double -> (5.0/9.0)*(f-32.0) + 273.15}
    
    printFinalTemperature(celsius, "Celsius", "Fahrenheit", cTOf)
    printFinalTemperature(kelvin, "Kelvin", "Celsius", kTOc)
    printFinalTemperature(fahrenheit, "Fahrenheit", "Kelvin", fTOk)    
    
}


fun printFinalTemperature(
    initialMeasurement: Double, 
    initialUnit: String, 
    finalUnit: String, 
    conversionFormula: (Double) -> Double
) {
    val finalMeasurement = String.format("%.2f", conversionFormula(initialMeasurement)) // two decimal places
    println("$initialMeasurement degrees $initialUnit is $finalMeasurement degrees $finalUnit.")
}
