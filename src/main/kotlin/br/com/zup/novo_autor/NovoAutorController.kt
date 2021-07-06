package br.com.zup.novo_autor

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import javax.validation.Valid

@Validated
@Controller("/autores")
class NovoAutorController {

    @Post
    fun cadastrarAutor(@Valid request: NovoAutorRequest) {
        println(request)
    }
}