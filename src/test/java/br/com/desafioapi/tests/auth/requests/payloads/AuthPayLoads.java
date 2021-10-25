package br.com.desafioapi.tests.auth.requests.payloads;

import org.json.JSONObject;

public class AuthPayLoads {
    public JSONObject jSonAuthLogin(){
        JSONObject payloadLogin = new JSONObject();

        payloadLogin.put("username", "admin");
        payloadLogin.put("password", "password123");

        return payloadLogin;

    }
}
