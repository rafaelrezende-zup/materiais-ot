package br.com.zup.bytebank.model

class Analista(
    nome: String,
    cpf: String,
    salario: Double
) : Funcionario(nome, cpf, salario) {

    override val bonificacao = salario * 0.1

}