package br.com.zup.bytebank.model

import br.com.zup.bytebank.exception.SaldoInsuficienteException

class ContaSalario(
    titular: Cliente,
    numero: String,
    saldo: Double,
    override val senha: String
) : Conta(titular, numero, saldo
) {
    override fun transferencia(destino: Conta, valor: Double, senha: String) {
        throw SaldoInsuficienteException()
    }
}