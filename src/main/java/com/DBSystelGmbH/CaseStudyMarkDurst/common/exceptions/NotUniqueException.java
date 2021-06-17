package com.DBSystelGmbH.CaseStudyMarkDurst.common.exceptions;

public class NotUniqueException extends BadRequestException {

    public NotUniqueException(String message) {
        super(message);
    }
}
