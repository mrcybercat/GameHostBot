package com.team3m.bot.commands.abstracts.annotations;

import com.team3m.bot.commands.abstracts.CommandType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CommandAnnotation {
    CommandType value();
}
