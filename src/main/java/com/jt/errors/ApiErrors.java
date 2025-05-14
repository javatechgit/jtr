package com.jt.errors;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiErrors {

	private String error;
    private List<String> fieldErrorsMsg;

    
}
