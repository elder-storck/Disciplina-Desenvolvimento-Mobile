/*
Geralmente, o preço dos ingressos é diferente dependendo da idade dos usuários.

No código inicial fornecido no snippet abaixo, escreva um programa que calcule estes preços de ingressos com base na idade:
	- O preço do ingresso é US$ 15 para pessoas de até 12 anos.
    - O preço padrão do ingresso é US$ 30 para pessoas com idade entre 13 e 60 anos. Às segundas-feiras, você pode aplicar um desconto para que o ingresso padrão dessa faixa etária custe US$ 25.
    - O preço de US$ 20 do ingresso para idosos é válido para pessoas com 61 anos ou mais. Suponha que a idade máxima de um frequentador de cinema seja de 100 anos.
    - Um valor -1 indica que o preço é inválido quando um usuário inserir uma idade fora das especificações.

*/

fun main() {
    val child = 5
    val adult = 28
    val senior = 87
    
    val isMonday = true
    
    println("The movie ticket price for a person aged $child is \$${ticketPrice(child, isMonday)}.")
    println("The movie ticket price for a person aged $adult is \$${ticketPrice(adult, isMonday)}.")
    println("The movie ticket price for a person aged $senior is \$${ticketPrice(senior, isMonday)}.")
}

fun ticketPrice(age: Int, isMonday: Boolean): Int {
    if(age <= 12) return 15
    if(age <= 60 && isMonday) return 25
    if(age <= 60 && !isMonday) return 30
    if(age >= 61 && age <= 100) return 20
    return -1
}
