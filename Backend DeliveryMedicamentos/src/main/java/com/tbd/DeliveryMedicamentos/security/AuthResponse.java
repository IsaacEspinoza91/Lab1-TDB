package com.tbd.DeliveryMedicamentos.security;


public class AuthResponse {

    private String token;

    // Constructor
    public AuthResponse() {
    }

    public AuthResponse(String token) {
        this.token = token;
    }


    // Gettes y Setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    // Builder
    public static class Builder {
        private String token;

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public AuthResponse build() {
            AuthResponse response = new AuthResponse();
            response.setToken(this.token);
            return response;
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
