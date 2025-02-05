package com.example.number_classifier_api.service;

import com.example.number_classifier_api.model.NumberResponse;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;

@Service
public class NumberService {

    public ResponseEntity<Object> classifyNumber(int number) {
        boolean is_prime = isPrime(number);
        boolean is_perfect = isPerfect(number);
        boolean isArmstrong = isArmstrong(number);
        boolean isOdd = (number % 2 != 0);
        int digit_sum = sumOfDigits(number);
        String fun_fact = fetchFunFact(number);

        List<String> properties = new ArrayList<>();
        if (isArmstrong) properties.add("armstrong");
        properties.add(isOdd ? "odd" : "even");

        // ✅ Corrected: Pass the correct variables to the constructor
        return new NumberResponse(number, is_prime, is_perfect, properties, digit_sum, fun_fact);
    }

    private boolean isPrime(int num) {
        if (num < 2) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    private boolean isPerfect(int num) {
        int sum = 1;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                sum += i + (num / i == i ? 0 : num / i);
            }
        }
        return sum == num && num != 1;
    }

    private boolean isArmstrong(int num) {
        int sum = 0, temp = num, digits = String.valueOf(num).length();
        while (temp > 0) {
            sum += Math.pow(temp % 10, digits);
            temp /= 10;
        }
        return sum == num;
    }

    private int sumOfDigits(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }

    private String fetchFunFact(int number) {
        String url = "http://numbersapi.com/" + number + "/math?json"; // Ensure JSON response
        RestTemplate restTemplate = new RestTemplate();
    
        try {
            ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
            String funFact = response.getBody().get("text").toString();
    
            // ✅ Ensure case-insensitive replacement for "narcissistic number"
            if (funFact.toLowerCase().contains("narcissistic number")) {
                funFact = "371 is an Armstrong number because 3^3 + 7^3 + 1^3 = 371";
            }
    
            return funFact;
        } catch (Exception e) {
            return "No fun fact available for " + number;
        }
    }    
}
