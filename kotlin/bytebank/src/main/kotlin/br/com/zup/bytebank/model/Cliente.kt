package br.com.zup.bytebank.model

import br.com.zup.bytebank.control.Autentication

class Cliente(
    val nome: String,
    val cpf: String,
    var endereco: Endereco = Endereco(),
    override val senha: String
) : Autentication {
}