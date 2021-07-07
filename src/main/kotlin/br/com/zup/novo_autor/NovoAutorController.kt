package br.com.zup.novo_autor

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import javax.validation.Valid

@Validated
@Controller("/autores")
class NovoAutorController(val repository: AutorRepository) {

    @Post
    fun cadastrarAutor(@Valid request: NovoAutorRequest): Autor {
        return request.paraAutor()
            .also { println("Dados da requisição convertidos para um objeto de domínio.") }
            .let { autor ->  repository.save(autor) }
            .also { println("Dados persistidos no banco de dados.") }
    }
}