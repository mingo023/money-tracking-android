package com.cs50.finalprojectcs50.utils;

import com.google.android.material.textfield.TextInputLayout;

public class FieldValidation {
    private TextInputLayout layout;
    private boolean isFailure = false;

    public FieldValidation(TextInputLayout layout) {
        this.layout = layout;
    }

    public FieldValidation required(String value, String errorMessage) {
        if (value.isEmpty()) {
            this.layout.setError(errorMessage);
            this.isFailure = true;
        } else {
            this.layout.setError(null);
        }
        return this;
    }

    public int validate() {
        return isFailure ? 1 : 0;
    }
}
