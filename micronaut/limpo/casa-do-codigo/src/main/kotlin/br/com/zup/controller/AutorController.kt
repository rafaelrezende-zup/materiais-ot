package br.com.zup.controller

import br.com.zup.dto.request.NovoAutorRequest
import br.com.zup.dto.response.DetalheAutorResponse
import br.com.zup.repository.AutorRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.*
import io.micronaut.http.uri.UriBuilder
import io.micronaut.validation.Validated
import javax.transaction.Transactional
import javax.validation.Valid

@Validated
@Controller("/autor")
class AutorController(val autorRepository: AutorRepository) {

    @Post
    @Transactional
    fun cadastra(@Body @Valid request: NovoAutorRequest): HttpResponse<Any>? {
        val autor = request.toDomain()
        autorRepository.save(autor)
        val uri = UriBuilder.of("/autor/{id}").expand(mutableMapOf(Pair("id", autor.id)))
        return HttpResponse.created(autor, uri)
    }

    @Get
    fun findAll() : HttpResponse<List<DetalheAutorResponse>> {
        val autores = autorRepository.findAll()
        val resposta = autores.map { i -> DetalheAutorResponse(i) }
        return HttpResponse.ok(resposta)
    }

    @Put("/{id}")
    @Transactional
    fun atualiza(@PathVariable id: Long, descricao: String) : HttpResponse<DetalheAutorResponse> {
        val possivelAutor = autorRepository.findById(id)
        if (possivelAutor.isEmpty) {
            return HttpResponse.notFound()
        }
        val autor = possivelAutor.get()
        autor.descricao = descricao
        return HttpResponse.ok(DetalheAutorResponse(autor))
    }

    @Delete("/{id}")
    @Transactional
    fun deleta(@PathVariable id : Long) : HttpResponse<Any> {
        val possivelAutor = autorRepository.findById(id)
        if (possivelAutor.isEmpty) {
            return HttpResponse.notFound()
        }
        val autor = possivelAutor.get()
        autorRepository.delete(autor)
        return HttpResponse.ok()
    }

}