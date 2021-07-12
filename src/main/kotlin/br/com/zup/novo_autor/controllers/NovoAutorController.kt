package br.com.zup.novo_autor.controllers

import br.com.zup.novo_autor.AutorRepository
import br.com.zup.novo_autor.EnderecoClient
import br.com.zup.novo_autor.NovoAutorRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.http.uri.UriBuilder
import io.micronaut.validation.Validated
import javax.validation.Valid

@Validated
@Controller("/autores")
class NovoAutorController(
    val repository: AutorRepository,
    val enderecoClient: EnderecoClient
) {

    @Post
    fun cadastrarAutor(@Body @Valid request: NovoAutorRequest): HttpResponse<Any> {
        val enderecoResponse = try {
            enderecoClient.consultaJSON(request.cep)
//            enderecoClient.consultaXML(request.cep)
        } catch (ex: HttpClientResponseException) {
            return HttpResponse.unprocessableEntity()
        }

        return request.paraAutor(enderecoResponse.body()!!)
            .let { autor ->  repository.save(autor) }
            .also { autor -> println(autor.toString()) }
            .let { autor -> UriBuilder.of("/autores/{id}")
                    .expand(mutableMapOf(Pair("id", autor.id))) }
            .let { uri -> HttpResponse.created(uri) }

    }


}