package com.robotcms.common.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;

/**
 * <pre>
 * 系统日志注解
 * </pre>
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {
	String value() default "";
}
