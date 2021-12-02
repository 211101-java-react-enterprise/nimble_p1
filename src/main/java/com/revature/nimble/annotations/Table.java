package com.revature.nimble.annotations;

import java.lang.annotation.*;

/*
  Custom annotation for mapping a table in database
*/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Table {
    public String tableName() default "";
}
