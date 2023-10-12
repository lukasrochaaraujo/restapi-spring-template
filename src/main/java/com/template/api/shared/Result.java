package com.template.api.shared;

import java.util.HashSet;
import java.util.List;

public class Result<T> {
    private T result;
    private HashSet<String> errors = new HashSet<>();

    public Result(T result) {
        this.result = result;
    }

    public Result(String error) {
        errors.add(error);
    }

    public boolean hasErros() {
        return !errors.isEmpty();
    }

    public List<String> getErros() {
        return errors.stream().toList();
    }

    public boolean hasValue() {
        return result != null;
    }

    public T getValue() {
        return result;
    }
}
