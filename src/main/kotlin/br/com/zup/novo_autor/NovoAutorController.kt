package br.com.zup.novo_autor

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import javax.validation.Valid

@Validated
@Controller("/autores")
class NovoAutorController(val repository: AutorRepository) {

    @Post
    fun cadastrarAutor(@Valid request: NovoAutorRequest) {
        request.paraAutor()
            .also { println("Dados da requisição convertidos para um objeto de domínio.") }
            .let { autor ->  repository.save(autor) }
            .also { println("Dados persistidos no banco de dados.") }
    }

    @Get
    fun listarAutores(): HttpResponse<List<DetalhaAutorResponse>> {
        return repository.findAll()
            .also { println("Busca os autores presentes no banco de dados.") }
            .map { autor -> DetalhaAutorResponse(autor) }
            .also { println("Mapeia os autores para um DTO de resposta.") }
            .let { autores -> HttpResponse.ok(autores) }
            .also { println("Retorna uma lista de autores com status 200.")}
    }
}