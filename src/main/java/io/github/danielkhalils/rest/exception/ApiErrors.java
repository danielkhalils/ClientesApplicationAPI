package io.github.danielkhalils.rest.exception;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class ApiErrors {

    private List<String> errors;

    public ApiErrors(List<String> erros) {
        this.errors = erros;
    }

    public ApiErrors(String message) {
        this.errors = Arrays.asList(message);
    }

}
