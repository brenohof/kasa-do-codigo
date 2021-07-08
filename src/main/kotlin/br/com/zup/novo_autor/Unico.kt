package br.com.zup.novo_autor

import io.micronaut.core.annotation.AnnotationValue
import io.micronaut.validation.validator.constraints.ConstraintValidator
import io.micronaut.validation.validator.constraints.ConstraintValidatorContext
import javax.inject.Singleton
import javax.persistence.EntityManager
import javax.transaction.Transactional
import javax.validation.Constraint
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.CONSTRUCTOR
import kotlin.annotation.AnnotationTarget.FIELD
import kotlin.reflect.KClass

@MustBeDocumented
@Target(FIELD, CONSTRUCTOR)
@Retention(RUNTIME)
@Constraint(validatedBy = [UnicoValidator::class])
annotation class Unico(
    val message: String = "Já existe registro com esse valor.",
    val field: String,
    val entity: KClass<*>
)

@Singleton
open class UnicoValidator(val entityManager: EntityManager): ConstraintValidator<Unico, String> {

    @Transactional
    override fun isValid(
        value: String?,
        annotationMetadata: AnnotationValue<Unico>,
        context: ConstraintValidatorContext,
    ): Boolean {
        val entity = annotationMetadata.classValue("entity").get()
        val field = annotationMetadata.stringValue("field").get()

        if (field.isBlank()) return true

        return entityManager
            .createQuery("SELECT 1 FROM ${entity.simpleName} WHERE $field = :value")
            .setParameter("value", value)
            .resultList.isEmpty()
    }

}
