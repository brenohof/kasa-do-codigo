package br.com.zup.novo_autor

data class EnderecoResponse(
    val cep: String,
    val logradouro: String,
    var complemento: String = "",
    val bairro: String,
    val localidade: String,
    val uf: String
)
