package br.com.zup.bytebank.model

class Diretor(
    nome: String,
    cpf: String,
    salario: Double,
    senha: String,
    val plr: Double
) : FuncionarioAdmin(nome, cpf, salario, senha) {

    override val bonificacao = (salario * 0.3) + plr

}