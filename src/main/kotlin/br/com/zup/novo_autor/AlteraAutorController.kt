package br.com.zup.novo_autor

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Put
import javax.transaction.Transactional

@Controller("/autores")
class AlteraAutorController(val autorRepository: AutorRepository) {

    @Put("/{id}")
    @Transactional
    fun atualizar(@PathVariable id: Long, descricao: String): HttpResponse<Any> {
        val optionalAutor = autorRepository.findById(id)

        if (optionalAutor.isEmpty)
            return HttpResponse.notFound()

        return optionalAutor.get().let { autor ->
            autor.descricao = descricao
            autorRepository.update(autor)
            HttpResponse.ok(DetalhaAutorResponse(autor))
        }
    }
}