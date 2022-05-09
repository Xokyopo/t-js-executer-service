package com.example.server.ui.web;

import com.example.server.core.models.JsExecuteRequest;
import com.example.server.core.usecases.ExecuteJsFunctionUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class JsExecuteController {

    private final ExecuteJsFunctionUseCase useCase;

    @Autowired
    public JsExecuteController(ExecuteJsFunctionUseCase useCase) {
        this.useCase = useCase;
    }


    @CrossOrigin
    @PostMapping(path = "/jsFunctionsExecute",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.TEXT_EVENT_STREAM_VALUE
    )
    public Flux<String> executeJsFunction(@RequestBody JsExecuteRequest request) {
        return this.useCase.use(request)
                .onErrorResume(throwable -> Mono.just(throwable.getMessage()));
    }
}
