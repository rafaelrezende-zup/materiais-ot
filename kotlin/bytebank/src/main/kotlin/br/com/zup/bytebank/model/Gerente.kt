package br.com.zup.bytebank.model

class Gerente(
    nome: String,
    cpf: String,
    salario: Double,
    senha: String
) : FuncionarioAdmin(nome, cpf, salario, senha) {

    override val bonificacao = salario * 0.2

}