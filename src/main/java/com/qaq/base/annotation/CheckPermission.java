package com.qaq.base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import com.qaq.base.enums.CheckType;
import com.qaq.base.enums.PermissionEnum;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckPermission {
    String value() default "";

    CheckType type();

    PermissionEnum permission();
}
