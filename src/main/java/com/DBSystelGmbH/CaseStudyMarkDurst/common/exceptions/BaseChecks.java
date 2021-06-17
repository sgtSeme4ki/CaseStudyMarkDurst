package com.DBSystelGmbH.CaseStudyMarkDurst.common.exceptions;

import static org.springframework.util.StringUtils.hasText;

public interface BaseChecks<T> {

    default void checkNotNull(Object object) {
        if (object == null) {
            throw new ObjectIsNullException("Object is null");
        }
    }

    default void checkStringBlank(String parameter) {
        if (!hasText(parameter)) {
            throw new StringIsBlankException("String parameter is blank");
        }
    }

    default void checkIdValidity(Long id) {
        if (id == null) {
            throw new IdIsNullExceptionException("Id " + id + " is null");
        }
        if (id <= 0) {
            throw new IdNotValidException("Id " + id + " is not valid");
        }
    }

    default void checkIdEquality(Long objectId, Long id) {
        checkIdValidity(objectId);
        checkIdValidity(id);

        if (!objectId.equals(id)) {
            throw new IdMismatchException("Ids " + objectId + " and " + id + " do not match");
        }
    }
}
