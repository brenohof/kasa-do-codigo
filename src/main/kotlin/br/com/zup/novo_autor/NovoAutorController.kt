package br.com.zup.novo_autor

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post

@Controller("/autores")
class NovoAutorController {

    @Post
    fun cadastrarAutor(request: NovoAutorRequest) {
        println(request)
    }
}