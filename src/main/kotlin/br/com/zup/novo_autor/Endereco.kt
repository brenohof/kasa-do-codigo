package br.com.zup.novo_autor

import javax.persistence.Embeddable

@Embeddable
class Endereco(
    enderecoResponse: EnderecoResponse,
    val numero: String
) {
    val cep= enderecoResponse.cep
    val logradouro = enderecoResponse.logradouro
    val bairro = enderecoResponse.bairro
    val cidade = enderecoResponse.cidade
    val uf = enderecoResponse.estado
}
