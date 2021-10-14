package br.com.zup.bytebank

import br.com.zup.bytebank.exception.FalhaAutenticacaoException
import br.com.zup.bytebank.exception.SaldoInsuficienteException
import br.com.zup.bytebank.model.*

fun main() {

    println("Hello World!")

    val gerente = Gerente("Rafael", "13265498745", 2500.0, "123")
    println(gerente.auth("123"))

    val cliente = Cliente("Rafael", "32165498745", Endereco("Teste", "132", "Teste", "13265-000"),"abc")
    println(cliente.auth("abc"))

    val contaSalario = ContaSalario(cliente, "1010", 1000.0, "1")
    val contaPoupanca = ContaPoupanca(cliente, "1010", 1000.0, "2")

    try {
        contaPoupanca.transferencia(contaSalario, 20000.0, "1")
    } catch (e : SaldoInsuficienteException) {
        println(e.message)
        e.printStackTrace()
    } catch (e : FalhaAutenticacaoException) {
        println(e.message)
        e.printStackTrace()
    }

    println(contaSalario.saldo)
    println(contaPoupanca.saldo)

}