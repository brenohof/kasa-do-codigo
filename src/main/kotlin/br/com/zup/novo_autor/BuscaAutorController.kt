package br.com.zup.novo_autor

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/autores")
class BuscaAutorController(val repository: AutorRepository) {

    @Get
    fun listarAutores(): HttpResponse<List<DetalhaAutorResponse>> {
        return repository.findAll()
            .map { autor -> DetalhaAutorResponse(autor) }
            .let { autores -> HttpResponse.ok(autores) }
    }
}