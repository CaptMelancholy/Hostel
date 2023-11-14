package com.example.hostel.beans.password;

import com.example.hostel.exceptions.CommandException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Password {
    private static Password instance;

    public static Password getInstance() {
        if(instance == null) {
            instance = new Password();
        }
        return instance;
    }

    public String hashPassword(String password) throws CommandException {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new CommandException(e.getMessage());
        }
        byte[] hashedBytes = md.digest(password.getBytes());

        StringBuilder hexString = new StringBuilder();
        for (byte b : hashedBytes) {
            hexString.append(String.format("%02x", b));
        }

        return hexString.toString();
    }
}
