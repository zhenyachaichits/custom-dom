package com.epam.task1.logic.validator;

import com.epam.task1.logic.validator.exception.DocumentValidatorException;

import java.io.File;
import java.io.IOException;

/**
 * Created by Zheny Chaichits on 09.12.2015.
 */
public interface DocumentValidator {
    boolean isDocumentValid(File file) throws DocumentValidatorException, IOException;
}
