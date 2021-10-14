package br.com.zup.bytebank.model

class Endereco(
    var logradouro: String = "",
    var numero: String = "",
    var bairro: String = "",
    var cep: String = ""
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Endereco

        if (logradouro != other.logradouro) return false
        if (numero != other.numero) return false
        if (bairro != other.bairro) return false
        if (cep != other.cep) return false

        return true
    }

    override fun hashCode(): Int {
        var result = logradouro.hashCode()
        result = 31 * result + numero.hashCode()
        result = 31 * result + bairro.hashCode()
        result = 31 * result + cep.hashCode()
        return result
    }
}