package br.com.zup.bytebank.model

abstract class Funcionario(
    nome: String,
    cpf: String,
    salario: Double,
) {

    abstract val bonificacao: Double

}