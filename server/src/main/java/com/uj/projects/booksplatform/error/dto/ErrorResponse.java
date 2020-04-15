package com.uj.projects.booksplatform.error.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter@Setter@AllArgsConstructor@NoArgsConstructor
public class ErrorResponse {
    private Map<String, String> parameters;
    private List<String> additionalMessages;

    public ErrorResponse(Map<String, String> parameters){
        this.parameters = parameters;
    }
}
