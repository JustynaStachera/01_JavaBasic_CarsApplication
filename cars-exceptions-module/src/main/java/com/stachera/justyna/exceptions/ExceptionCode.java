package com.stachera.justyna.exceptions;

/**
 * Developed by Justyna Stachera on 15.01.2019.
 * Package name: com.stachera.justyna.exceptions
 * Last modified 20:25.
 * Copyright (c) 2019. All rights reserved.
 */
public enum ExceptionCode
{
    CODE_250("FIELD VALIDATION");
    
    private String description;
    
    ExceptionCode(String description)
    {
        this.description = description;
    }
    
    public String getDescription()
    {
        return description;
    }
}
