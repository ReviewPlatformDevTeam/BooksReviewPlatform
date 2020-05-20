package com.uj.projects.booksplatform.error.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter@Setter@AllArgsConstructor@NoArgsConstructor
public class ErrorResponse {
    private Map<String, String> parameters = new HashMap<>();
    private List<String> additionalMessages = new ArrayList<>();

    public ErrorResponse(Map<String, String> parameters){
        this.parameters = parameters;
    }

    public ErrorResponse(String errorMessage){
        this.additionalMessages.add(errorMessage);
    }
}
