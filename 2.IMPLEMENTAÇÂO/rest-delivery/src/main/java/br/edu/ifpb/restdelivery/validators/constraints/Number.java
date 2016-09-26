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
 * Anotation que verifica se atributo possui somente números
 * 
 * @author rafaelfeitosa - <a href="https://github.com/JoseRafael97 ></a>
 *
 */
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Pattern(regexp = "\\d+")
public @interface Number {

	@OverridesAttribute(constraint = Pattern.class, name = "message")
	String message() default "{br.edu.ifpb.restdelivery.constraints.number}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
