package br.com.zup

import br.com.zup.novo_autor.EnderecoClient
import br.com.zup.novo_autor.EnderecoResponse
import br.com.zup.novo_autor.NovoAutorRequest
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.annotation.TransactionMode
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito
import javax.inject.Inject

@MicronautTest(
    rollback = false,
    transactionMode = TransactionMode.SINGLE_TRANSACTION,
    transactional = false
)
internal class NovoAutorControllerTest {

    @field:Inject
    lateinit var enderecoClient: EnderecoClient

    @field:Inject
    @field:Client("/")
    lateinit var client: HttpClient

    lateinit var novoAutorRequest: NovoAutorRequest

    @BeforeEach
    internal fun setUp() {
        novoAutorRequest = NovoAutorRequest(
            "Rafael Pontes",
            "rafael.ponte@zup.com.br",
            "O principe dos oceanos.",
            "38408222",
            "37"
        )

        val enderecoResponse = EnderecoResponse(
            "38408-222",
            "Rua das Laranjeiras",
            "Rio de Janeiro",
            "Rio de Janeiro",
            "RJ"
        )

        Mockito.`when`(enderecoClient.consultaJSON(novoAutorRequest.cep)).thenReturn(HttpResponse.ok(enderecoResponse))
    }

    @Test
    internal fun `deve cadastrar um novo autor`() {
        val request = HttpRequest.POST("/autores", novoAutorRequest)
        val response = client.toBlocking().exchange(request, Any::class.java)

        assertEquals(HttpStatus.CREATED, response.status)
        assertTrue(response.headers.contains("Location"))
        assertTrue(response.header("Location")!!.matches("/autores/\\d".toRegex()))
    }

    @MockBean(EnderecoClient::class)
    fun enderecoMock(): EnderecoClient {
        return Mockito.mock(EnderecoClient::class.java)
    }
}