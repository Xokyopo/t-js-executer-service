package com.example.server.core.buisnesruls;

import com.example.server.core.exceptions.ExceptionsFactory;
import com.example.server.core.utils.JsFunctionNameExtractor;
import org.springframework.stereotype.Component;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

@Component
public class JsExecuteRule {

    private static final String SCRIPT_ENGINE_NAME = "nashorn";

    public Function createFunction(String function) {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName(SCRIPT_ENGINE_NAME);

        try {
            engine.eval(function);

            return new Function(engine, JsFunctionNameExtractor.extract(function));
        } catch (ScriptException e) {
            throw ExceptionsFactory.createFunctionCreatingException(e);
        }
    }

    public static class Function {

        private final Invocable function;
        private final String functionName;

        public Function(ScriptEngine engine, String functionName) {
            this.function = (Invocable) engine;
            this.functionName = functionName;
        }

        public Object execute(Object... args) {
            try {
                return this.function.invokeFunction(this.functionName, args);
            } catch (ScriptException | NoSuchMethodException e) {
                throw ExceptionsFactory.createFunctionExecuteException(e);
            }
        }
    }
}
