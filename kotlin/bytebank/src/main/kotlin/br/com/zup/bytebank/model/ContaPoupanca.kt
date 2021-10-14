package br.com.zup.bytebank.model

class ContaPoupanca(
    titular: Cliente,
    numero: String,
    saldo: Double,
    override val senha: String
) : Conta(titular, numero, saldo) {
}