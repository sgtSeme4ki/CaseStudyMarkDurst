package com.DBSystelGmbH.CaseStudyMarkDurst.common.exceptions;

public class EntityNotFoundException extends BadRequestException {

    public EntityNotFoundException(String message) {
        super(message);
    }
}
