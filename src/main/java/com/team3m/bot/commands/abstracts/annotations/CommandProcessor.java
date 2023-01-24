package com.team3m.bot.commands.abstracts.annotations;

import com.team3m.bot.commands.abstracts.CommandType;
import com.team3m.bot.commands.abstracts.annotations.CommandAnnotation;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.*;

public class CommandProcessor extends AbstractProcessor {
    @Override
    public boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment roundEnv) {
        Map<TypeElement, Map<ExecutableElement, CommandType>>  classToMethod = null;
        TypeElement associatedClass;
        ExecutableElement associatedMethod;
        CommandType associatedValue;

        for (Element elem : roundEnv.getElementsAnnotatedWith(CommandAnnotation.class)) {
            if (elem.getKind() == ElementKind.METHOD) {
                associatedClass = (TypeElement) elem.getEnclosingElement();
                associatedMethod = (ExecutableElement) elem;
                associatedValue = elem.getAnnotation(CommandAnnotation.class).value();
                Map<ExecutableElement, CommandType> methodToValue;
                if(classToMethod.containsKey(associatedClass)){
                    methodToValue = classToMethod.get(associatedClass);
                    methodToValue.put(associatedMethod, associatedValue);
                } else {
                    methodToValue = new HashMap<ExecutableElement, CommandType>();
                    methodToValue.put(associatedMethod, associatedValue);
                    classToMethod.put(associatedClass, methodToValue);
                }
            }
        }

        for (Map.Entry<TypeElement, Map<ExecutableElement, CommandType>> entry: classToMethod.entrySet()) {
            Map<ExecutableElement, CommandType> methodToValue = entry.getValue();
            if((new ArrayList<>(methodToValue.values())).size() != (new HashSet<>(methodToValue.values())).size()){
                processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "@CommandAnnotation : annotated must use any value no more than once");
            }
        }

        return false;
    }
}
