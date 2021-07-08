package br.com.zup.novo_autor

import com.fasterxml.jackson.annotation.JsonProperty

data class EnderecoResponse(
    @field:JsonProperty("code") val  cep: String,
    @field:JsonProperty("address") val logradouro: String,
    @field:JsonProperty("district") val bairro: String,
    @field:JsonProperty("city") val cidade: String,
    @field:JsonProperty("state") val estado: String
)
