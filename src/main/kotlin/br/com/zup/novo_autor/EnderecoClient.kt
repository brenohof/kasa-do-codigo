package br.com.zup.novo_autor

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Get
import io.micronaut.http.client.annotation.Client

@Client("http://viacep.com.br/ws")
interface EnderecoClient {

    @Get("/{cep}/json")
    fun consulta(cep: String): HttpResponse<EnderecoResponse>
}