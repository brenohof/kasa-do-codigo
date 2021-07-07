package br.com.zup.novo_autor

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "autores")
class Autor(
    val nome: String,
    val email: String,
    val descricao: String
) {
    @Id @GeneratedValue
    var id: Long? = null
    val criadoEm: LocalDateTime = LocalDateTime.now()
}