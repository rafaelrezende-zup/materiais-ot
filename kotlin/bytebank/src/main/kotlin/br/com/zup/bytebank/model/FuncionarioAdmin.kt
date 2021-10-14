package br.com.zup.bytebank.model

import br.com.zup.bytebank.control.Autentication

abstract class FuncionarioAdmin(
    nome: String,
    cpf: String,
    salario: Double,
    override val senha: String
) : Funcionario(nome, cpf, salario), Autentication {
}