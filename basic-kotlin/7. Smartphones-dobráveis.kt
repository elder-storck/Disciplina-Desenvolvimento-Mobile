/*
Normalmente, pressionar o botão liga/desliga do smartphone ativa ou desativa a tela dele. Por outro lado, se um smartphone dobrável estiver dobrado, a tela interna principal dele não vai ser ativada quando o botão liga/desliga for pressionado.

No código inicial fornecido no snippet abaixo, escreva uma classe FoldablePhone herdada da classe Phone. Ela vai conter o seguinte:

    Uma propriedade que indica se o smartphone está dobrado.
    Um comportamento da função switchOn() diferente da classe Phone para que ela só ative a tela quando o telefone não estiver dobrado.
    Métodos para mudar o estado da dobra.
*/

open class Phone(var isScreenLightOn: Boolean = false){
    open fun switchOn() {
        isScreenLightOn = true
    }
    
    fun switchOff() {
        isScreenLightOn = false
    }
    
    fun checkPhoneScreenLight() {
        val phoneScreenLight = if (isScreenLightOn) "on" else "off"
        println("The phone screen's light is $phoneScreenLight.")
    }
}
class FoldablePhone(isScreenLightOn: Boolean, var isFolded : Boolean = false) : Phone(isScreenLightOn){
	override fun switchOn() {
        if(!isFolded) isScreenLightOn = true
    }
    
    // Dobrar o telefone
    fun fold(){
        isFolded = true
    }
    
    // Desdobrar o telefone
    fun unFold(){
        isFolded = false
    }
      
}

fun main(){
    
}
