package com.revature.nimble.annotations;

import java.lang.annotation.*;

/*
    Custom annotation for mapping a field of object to corresponding column in a database table
*/

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MapColumn {
    public String columnName() default "";
}
