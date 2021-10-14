package br.com.zup.bytebank.model

import br.com.zup.bytebank.exception.SaldoInsuficienteException

class ContaCorrente(
    titular: Cliente,
    numero: String,
    saldo: Double,
    override val senha: String
) : Conta(titular, numero, saldo
) {
    override fun saca(valor: Double, senha: String) {
        val valorComTaxa = valor + 0.10
        try {
            super.saca(valorComTaxa, senha)
        } catch (e : SaldoInsuficienteException) {
            println(e.message)
            e.stackTrace
        }
    }
}