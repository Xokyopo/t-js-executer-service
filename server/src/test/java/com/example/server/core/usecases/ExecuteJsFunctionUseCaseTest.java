package com.example.server.core.usecases;

import com.example.server.core.exceptions.ExceptionsFactory;
import com.example.server.core.models.JsExecuteRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

public class ExecuteJsFunctionUseCaseTest {

    private ExecuteJsFunctionUseCase useCase;

    @BeforeEach
    public void init() {
        this.useCase = new ExecuteJsFunctionUseCase(10);
    }

    @Test
    public void use_ShouldReturnTrue_WhenExecuteWithBasicAlignment() {
        String firstFunction = "function plus(a) {for (i=0; i < 100000000; i++){}; return a + a}";
        String secondFunction = "function multiple(a) {return a * a}";
        JsExecuteRequest.AlignmentType alignmentType = JsExecuteRequest.AlignmentType.BASIC;
        int repeats = 5;

        JsExecuteRequest request = new JsExecuteRequest(firstFunction, secondFunction, repeats, alignmentType);

        StepVerifier.create(this.useCase.use(request))
                .expectAccessibleContext()
                .then()
                .expectNextCount(repeats * 2)
                .verifyComplete();

    }

    @Test
    public void use_ShouldReturnTrue_WhenExecuteWithCSVAlignment() {
        String firstFunction = "function plus(a) { for (i=0; i < 100000000; i++){}; return a + a}";
        String secondFunction = "function multiple(a) {return a * a}";
        JsExecuteRequest.AlignmentType alignmentType = JsExecuteRequest.AlignmentType.CSV;
        int repeats = 5;

        JsExecuteRequest request = new JsExecuteRequest(firstFunction, secondFunction, repeats, alignmentType);

        StepVerifier.create(this.useCase.use(request))
                .expectAccessibleContext()
                .then()
                .expectNextCount(repeats)
                .verifyComplete();
    }

    @Test
    public void use_ShouldThrowFunctionCreatingException_WhenHaveInvalidFunctionDeclaration() {
        String firstFunction = "plus(a) => { return a + a}";
        String secondFunction = "function multiple(a) {return a * a}";
        JsExecuteRequest.AlignmentType alignmentType = JsExecuteRequest.AlignmentType.CSV;
        int repeats = 5;

        JsExecuteRequest request = new JsExecuteRequest(firstFunction, secondFunction, repeats, alignmentType);

        StepVerifier.create(this.useCase.use(request))
                .verifyError(ExceptionsFactory.createFunctionCreatingException(new Exception()).getClass());
    }

    @Test
    public void use_ShouldThrowFunctionExecuteException_WhenJsFunctionThrowException() {
        String firstFunction = "function plus(a) { throw new Error('что то пошло не так'); return a + a}";
        String secondFunction = "function multiple(a) {return a * a}";
        JsExecuteRequest.AlignmentType alignmentType = JsExecuteRequest.AlignmentType.CSV;
        int repeats = 5;

        JsExecuteRequest request = new JsExecuteRequest(firstFunction, secondFunction, repeats, alignmentType);

        StepVerifier.create(this.useCase.use(request))
                .verifyError(ExceptionsFactory.createFunctionExecuteException(new Exception()).getClass());
    }
}
