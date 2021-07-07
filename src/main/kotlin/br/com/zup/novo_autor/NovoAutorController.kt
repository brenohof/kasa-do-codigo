package br.com.zup.novo_autor

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.uri.UriBuilder
import io.micronaut.validation.Validated
import javax.validation.Valid

@Validated
@Controller("/autores")
class NovoAutorController(val repository: AutorRepository) {

    @Post
    fun cadastrarAutor(@Valid request: NovoAutorRequest): HttpResponse<Any> {
        return request.paraAutor()
            .let { autor ->  repository.save(autor) }
            .let { autor -> UriBuilder.of("/autores/{id}")
                    .expand(mutableMapOf(Pair("id", autor.id))) }
            .let { uri -> HttpResponse.created(uri) }
    }

    @Get
    fun listarAutores(): HttpResponse<List<DetalhaAutorResponse>> {
        return repository.findAll()
            .map { autor -> DetalhaAutorResponse(autor) }
            .let { autores -> HttpResponse.ok(autores) }
    }
}