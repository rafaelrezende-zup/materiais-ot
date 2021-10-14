package br.com.zup.bytebank.control

class SistemaInterno {

    fun auth(user: Autentication, senha: String) {
        if (user.auth(senha)) {
            println("Autenticado o/")
            return
        }
        println("Falha na autenticação.")
    }

}