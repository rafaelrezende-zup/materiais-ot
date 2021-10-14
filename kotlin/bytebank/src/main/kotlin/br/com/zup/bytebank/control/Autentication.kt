package br.com.zup.bytebank.control

interface Autentication {

    val senha: String

    fun auth(senha: String) : Boolean {
        if (this.senha == senha) {
            return true
        }
        return false
    }

}