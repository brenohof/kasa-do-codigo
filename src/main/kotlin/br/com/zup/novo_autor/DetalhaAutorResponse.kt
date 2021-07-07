package br.com.zup.novo_autor

class DetalhaAutorResponse(autor: Autor) {

    val nome = autor.nome
    val email = autor.email
    val descricao = autor.descricao
}
