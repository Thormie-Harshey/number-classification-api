// # Handles API requests
package com.example.number_classifier_api.controller;

import com.example.number_classifier_api.model.NumberResponse;
// import com.example.number_classifier_api.model.ErrorResponse;
import com.example.number_classifier_api.service.NumberService;

import org.springframework.http.HttpStatus;
// import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // Enables CORS

public class NumberController {
        
    private final NumberService numberService;

    public NumberController(NumberService numberService) {
        this.numberService = numberService;
    }

    @GetMapping("/classify-number")
    public ResponseEntity<?> classifyNumber(@RequestParam String number) {
        try {
            int num = Integer.parseInt(number);
            return numberService.classifyNumber(num);
        // } catch (NumberFormatException e) {
        //     return new ErrorResponse(number, true);   
        // }
        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST) // Explicitly set status to 400
                             .body(new ErrorResponse(number, true));
        // } catch (NumberFormatException e) {
        // return ResponseEntity.Request().body(new ErrorResponse(number, true));
        }
    }

    // Exception Handler for Invalid Input (Handles NumberFormatException globally)
    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<ErrorResponse> handleInvalidInput(NumberFormatException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                             .body(new ErrorResponse("Invalid number format", true));
    }
}

class ErrorResponse {
    private final String number;
    private final boolean error;

    public ErrorResponse(String number, boolean error) {
        this.number = number;
        this.error = error;
    }

    public String getNumber() {
        return number;
    }

    public boolean isError() {
        return error;
    }
}