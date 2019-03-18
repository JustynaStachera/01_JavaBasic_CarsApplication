package com.stachera.justyna.exceptions;

/**
 * Developed by Justyna Stachera on 15.01.2019.
 * Package name: com.stachera.justyna.exceptions
 * Last modified 20:24.
 * Copyright (c) 2019. All rights reserved.
 */
public class CustomException extends RuntimeException
{
    private ExceptionInfo exceptionInfo;
    
    public CustomException(ExceptionCode exceptionCode, String exceptionMessage)
    {
        this.exceptionInfo = new ExceptionInfo(exceptionCode, exceptionMessage);
    }
    
    public ExceptionInfo getExceptionInfo()
    {
        return exceptionInfo;
    }
}
