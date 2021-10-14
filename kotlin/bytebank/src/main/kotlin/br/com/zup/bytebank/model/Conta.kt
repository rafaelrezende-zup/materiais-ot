package br.com.zup.bytebank.model

import br.com.zup.bytebank.control.Autentication
import br.com.zup.bytebank.exception.FalhaAutenticacaoException
import br.com.zup.bytebank.exception.SaldoInsuficienteException

abstract class Conta(
    val titular: Cliente,
    val numero: String,
    var saldo: Double) : Autentication {

    fun deposita(valor: Double) {
        saldo += valor
    }

    open fun saca(valor: Double, senha: String) {
        if (!auth(senha)) {
            throw FalhaAutenticacaoException()
        }
        if (saldo <= 0) {
            println("Conta negativa ou nula")
            throw SaldoInsuficienteException()
        }
        if ((saldo - valor) < 0) {
            println("Você não pode ficar com saldo negativo")
            throw SaldoInsuficienteException()
        }
        saldo -= valor;
    }

    open fun transferencia(destino: Conta, valor: Double, senha: String) {
        if (!auth(senha)) {
            throw FalhaAutenticacaoException()
        }
        if (saldo <= 0) {
            println("Conta negativa ou nula")
            throw SaldoInsuficienteException()
        }
        if ((saldo - valor) < 0) {
            println("Você não pode ficar com saldo negativo")
            throw SaldoInsuficienteException()
        }
        saca(valor, senha)
        destino.deposita(valor)
    }

    override fun auth(senha: String): Boolean {
        return titular.auth(senha)
    }

}