package br.com.zup

import java.io.FileInputStream
import java.io.FileOutputStream

fun main() {
    val request = FuncionarioRequest.newBuilder()
        .setNome("Rafael")
        .setCpf("16547265415")
        .setSalario(2500.0)
        .setAtivo(true)
        .setCargo(Cargo.DEV)
        .addEnderecos(FuncionarioRequest.Endereco.newBuilder()
            .setLogradouro("Av. 1")
            .setCep("35560000")
            .setComplemento("Rua Principal")
            .build())
        .build()

    println(request)

    request.writeTo(FileOutputStream("funcionario-request.bin"))

    val request2 = FuncionarioRequest.newBuilder().mergeFrom(FileInputStream("funcionario-request.bin"))

    request2.setCargo(Cargo.MANAGER)

    println(request2)
}