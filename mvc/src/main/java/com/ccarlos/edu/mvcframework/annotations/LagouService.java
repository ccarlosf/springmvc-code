package com.ccarlos.edu.mvcframework.annotations;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface LagouService {
    String value() default "";
}
