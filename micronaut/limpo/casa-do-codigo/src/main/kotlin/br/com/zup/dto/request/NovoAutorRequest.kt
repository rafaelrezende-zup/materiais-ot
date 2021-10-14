package br.com.zup.dto.request

import br.com.zup.domain.Autor
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Introspected
data class NovoAutorRequest(@field:NotBlank val nome: String,
                       @field:NotBlank @field:Email val email: String,
                       @field:NotBlank @field:Size(max = 400) val descricao: String) {

    fun toDomain(): Autor {
        return Autor(this.nome, this.email, this.descricao)
    }

}