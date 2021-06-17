package com.DBSystelGmbH.CaseStudyMarkDurst.common.exceptions;

public class PagedResultIsEmptyException extends BadRequestException {

    public PagedResultIsEmptyException(String message) {
        super(message);
    }
}
