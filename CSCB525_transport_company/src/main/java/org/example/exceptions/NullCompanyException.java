package org.example.exceptions;

public class NullCompanyException extends Throwable {
    public NullCompanyException(String companyIdCannotBeNull) {
        super(companyIdCannotBeNull);
    }
}
