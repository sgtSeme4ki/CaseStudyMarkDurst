package com.DBSystelGmbH.CaseStudyMarkDurst.common.exceptions;

public class StringIsBlankException extends BadRequestException {

    public StringIsBlankException(String message) {
        super(message);
    }
}
