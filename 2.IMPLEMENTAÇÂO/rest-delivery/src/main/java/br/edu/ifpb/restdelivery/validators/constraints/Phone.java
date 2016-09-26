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
 * Anotation que valida o número de um telefone celular no formato (99)9999-99999
 * @author rafaelfeitosa - <a href="https://github.com/JoseRafael97 ></a>
 *
 */
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Pattern(regexp = ".((10)|([1-9][1-9]).)\\s[2-5][0-9]{3}-[0-9]{4}")
public @interface Phone {

	@OverridesAttribute(constraint = Pattern.class, name = "message")
	String message() default "{br.edu.ifpb.restdelivery.constraints.phone}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
