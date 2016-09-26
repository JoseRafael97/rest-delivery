package br.edu.ifpb.restdelivery.validators.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;
/**
 * Anotation que válida a String de um cep no formato 99999-999
 * @author rafaelfeitosa - <a href="https://github.com/JoseRafael97 ></a>
 *
 */
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Pattern(regexp = "^\\d{5}[-]\\d{3}$")
public @interface CEP {

	@OverridesAttribute(constraint = Pattern.class, name = "message")
	String message() default "{br.edu.ifpb.restdelivery.constraints.cep}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
