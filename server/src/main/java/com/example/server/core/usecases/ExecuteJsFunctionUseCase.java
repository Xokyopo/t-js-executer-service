package com.example.server.core.usecases;

import com.example.server.core.buisnesruls.JsExecuteRule;
import com.example.server.core.buisnesruls.ResponseFormatRule;
import com.example.server.core.models.FunctionResult;
import com.example.server.core.models.JsExecuteRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ExecuteJsFunctionUseCase {

    private static final JsExecuteRule JS_EXECUTE_RULE = new JsExecuteRule();
    private static final ResponseFormatRule RESPONSE_FORMAT_RULE = new ResponseFormatRule();

    private final long functionsExecuteInterval;

    @Autowired
    public ExecuteJsFunctionUseCase(@Value("${functions.execute.interval:10}") long functionsExecuteInterval) {
        this.functionsExecuteInterval = functionsExecuteInterval;
    }

    public Flux<String> use(JsExecuteRequest request) {
        AtomicInteger firstFunctionCounter = new AtomicInteger(0);
        AtomicInteger secondFunctionCounter = new AtomicInteger(0);
        Duration delay = Duration.ofMillis(functionsExecuteInterval);

        Flux<FunctionResult> firstFunction = Flux
                .<JsExecuteRule.Function>generate((sink) -> {
                    sink.next(JS_EXECUTE_RULE.createFunction(request.getFirstFunction()));
                    sink.complete();
                })
                .repeat(request.getRepeatsCount() - 1)
                .delayElements(delay)
                .map(function -> new FunctionResult(1, firstFunctionCounter.incrementAndGet(), function));

        Flux<FunctionResult> secondFunction = Flux
                .<JsExecuteRule.Function>generate((sink) -> {
                    sink.next(JS_EXECUTE_RULE.createFunction(request.getSecondFunction()));
                    sink.complete();
                })
                .repeat(request.getRepeatsCount() - 1)
                .delayElements(delay)
                .map(function -> new FunctionResult(2, secondFunctionCounter.incrementAndGet(), function));


        if (JsExecuteRequest.AlignmentType.CSV.equals(request.getResponseAlignment())) {
            return firstFunction.zipWith(secondFunction)
                    .map(objects -> RESPONSE_FORMAT_RULE.csvResultFormat(
                            objects.getT1(),
                            firstFunctionCounter.get(),
                            objects.getT2(),
                            secondFunctionCounter.get()));
        }

        return firstFunction
                .mergeWith(secondFunction)
                .map(RESPONSE_FORMAT_RULE::basicResultFormat);
    }
}
