package com.stachera.justyna.exceptions;

import java.time.LocalDateTime;

/**
 * Developed by Justyna Stachera on 15.01.2019.
 * Package name: com.stachera.justyna.exceptions
 * Last modified 20:25.
 * Copyright (c) 2019. All rights reserved.
 */
class ExceptionInfo
{
    private ExceptionCode exceptionCode;
    private String exceptionMessage;
    private LocalDateTime exceptionDateTime;
    
    ExceptionInfo(ExceptionCode exceptionCode, String exceptionMessage)
    {
        this.exceptionCode = exceptionCode;
        this.exceptionMessage = exceptionMessage;
        generateDate();
    }

    public ExceptionCode getExceptionCode()
    {
        return exceptionCode;
    }

    public void setExceptionCode(ExceptionCode exceptionCode)
    {
        this.exceptionCode = exceptionCode;
    }

    public String getExceptionMessage()
    {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage)
    {
        this.exceptionMessage = exceptionMessage;
    }

    public LocalDateTime getExceptionDateTime()
    {
        return exceptionDateTime;
    }

    public void setExceptionDateTime(LocalDateTime exceptionDateTime)
    {
        this.exceptionDateTime = exceptionDateTime;
    }

    private void generateDate() {
        this.exceptionDateTime = LocalDateTime.now();
    }
}
