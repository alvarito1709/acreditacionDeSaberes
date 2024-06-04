package com.agencia.acs.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public  class passwordGenerator {

    public static void main(String[] args){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = "123";

        String contraseñaCodeada = encoder.encode(password);
        System.out.println("password: 123" + "ahora es:" + contraseñaCodeada);

    }




}