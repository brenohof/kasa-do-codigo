package br.com.zup.novo_autor

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue

@Controller("/autores")
class BuscaAutorController(val repository: AutorRepository) {

    @Get
    fun listarAutores(@QueryValue(defaultValue = "") email: String): HttpResponse<Any> {
        if (email.isBlank()) {
            return repository.findAll()
                .map { autor -> DetalhaAutorResponse(autor) }
                .let { autores -> HttpResponse.ok(autores) }
        }

        val possivelAutor = repository.findByEmail(email)
        //val possivelAutor = repository.buscaPorEmail(email)

        if (possivelAutor.isEmpty) return HttpResponse.notFound()

        return possivelAutor.get()
            .let { autor -> HttpResponse.ok(DetalhaAutorResponse(autor)) }
    }
}