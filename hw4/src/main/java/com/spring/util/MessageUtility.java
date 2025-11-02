package com.spring.util;

public class MessageUtility {
    
    public String getMessage() {
        return "Hello from Spring IoC Container! This object was injected by Spring. Object ID: " + this.hashCode();
    }
    
    public MessageUtility() {
        System.out.println("✅ MessageUtility object created! Object ID: " + this.hashCode());
    }
}