package com.ws.example.service;

import org.springframework.stereotype.Service;

@Service
public class TestImpl implements TestInterface {
    public String test() {
        return "this is test";
    }
}
