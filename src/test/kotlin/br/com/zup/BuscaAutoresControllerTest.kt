package br.com.zup

import br.com.zup.novo_autor.*
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import javax.inject.Inject

@MicronautTest
class BuscaAutoresControllerTest {

    @field:Inject
    lateinit var autorRepository: AutorRepository

    @field:Inject
    @field:Client("/")
    lateinit var client: HttpClient

    lateinit var autor: Autor

    @BeforeEach
    internal fun setUp() {
        autor = Autor(
            nome = "George R. R. Martin",
            email = "georgerrmartin@email.com",
            descricao = "Autor de contos épicos como A song of fire and ice."
        )

        autorRepository.save(autor)
    }

    @AfterEach
    internal fun tearDown() {
        autorRepository.deleteAll()
    }

    @Test
    internal fun `deve retornar os detalhes de um autor`() {
        val response = client
            .toBlocking()
            .exchange("autores?email=${autor.email}", DetalhaAutorResponse::class.java)

        assertEquals(HttpStatus.OK, response.status)
        assertNotNull(response.body())
        assertEquals(autor.nome, response.body()!!.nome)
        assertEquals(autor.email, response.body()!!.email)
        assertEquals(autor.descricao, response.body()!!.descricao)
    }
}