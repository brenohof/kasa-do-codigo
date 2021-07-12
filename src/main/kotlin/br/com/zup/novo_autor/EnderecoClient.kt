package br.com.zup.novo_autor

import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Consumes
import io.micronaut.http.annotation.Get
import io.micronaut.http.client.annotation.Client

@Client("https://ws.apicep.com/cep/")
interface EnderecoClient {

    @Get(value = "/{cep}.json")
    fun consultaJSON(cep: String): HttpResponse<EnderecoResponse>

//    @Get(value = "/{cep}.xml")
//    @Consumes(MediaType.APPLICATION_XML)
//    fun consultaXML(cep: String): HttpResponse<EnderecoResponse>
}