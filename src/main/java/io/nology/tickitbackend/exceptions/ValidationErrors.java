package io.nology.tickitbackend.exceptions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ValidationErrors {
    private HashMap<String, ArrayList<String>> errors;

    public void addError(String field, String message) {
        if (this.errors.containsKey(field)) {
            errors.get(field).add(message);
        } else {
            ArrayList<String> newList = new ArrayList<>();
            newList.add(message);
            errors.put(field, newList);
        }
    }

    public ValidationErrors() {
        this.errors = new HashMap<>();
    }

    public boolean isEmpty() {
        return this.errors.isEmpty();
    }

    public boolean hasErrors() {
        return !this.isEmpty();
    }

    public Map<String, ArrayList<String>> getErrors() {
        return Collections.unmodifiableMap(this.errors);
    }

}
