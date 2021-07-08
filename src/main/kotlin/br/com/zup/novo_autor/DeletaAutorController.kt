package br.com.zup.novo_autor

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.PathVariable

@Controller("/autores")
class DeletaAutorController(val autorRepository: AutorRepository) {

    @Delete("/{id}")
    fun deletarAutor(@PathVariable id: Long): HttpResponse<Any> {
        val optionalAutor = autorRepository.findById(id)

        if (optionalAutor.isEmpty)
            return HttpResponse.notFound()

        return optionalAutor.get()
            .let { autor ->
                autorRepository.delete(autor)
                HttpResponse.ok()
            }
    }
}