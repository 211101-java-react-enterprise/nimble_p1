package com.revature.nimble.annotations;


import java.lang.annotation.*;

/*
    Custom annotation to map primary key with a field in object
*/
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Key {
    public String keyName() default "";
}
