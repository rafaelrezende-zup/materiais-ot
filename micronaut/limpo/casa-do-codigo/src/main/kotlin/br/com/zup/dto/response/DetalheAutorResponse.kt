package br.com.zup.dto.response

import br.com.zup.domain.Autor

class DetalheAutorResponse(autor: Autor) {

    val nome = autor.nome
    val email = autor.email
    val descricao = autor.descricao

}