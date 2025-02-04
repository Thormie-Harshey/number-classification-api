package com.example.number_classifier_api.model;

import java.util.List;

public class NumberResponse {
    private final int number;
    private final boolean is_prime;
    private final boolean is_perfect;
    private final List<String> properties;
    private final int digit_sum;
    private final String fun_fact;

    // ✅ Constructor with correct parameters
    public NumberResponse(int number, boolean is_prime, boolean is_perfect, List<String> properties, int digit_sum, String fun_fact) {
        this.number = number;
        this.is_prime = is_prime;
        this.is_perfect = is_perfect;
        this.properties = properties;
        this.digit_sum = digit_sum;
        this.fun_fact = fun_fact;
    }

    public int getNumber() { return number; }
    public boolean getIs_prime() { return is_prime; }
    public boolean getIs_perfect() { return is_perfect; }
    public List<String> getProperties() { return properties; }
    public int getDigit_sum() { return digit_sum; }
    public String getFun_fact() { return fun_fact; }
}
