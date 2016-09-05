package org.wuda.fastej.validate;

import java.util.List;

public class ValidatedBean<T> {
    private final T originBean;
    private final boolean isValid;
    private List<ValidateMessage> validateMessages;

    public ValidatedBean(boolean isValid, T originBean) {
        this.isValid = isValid;
        this.originBean = originBean;
    }

    public ValidatedBean(boolean isValid, T originBean, List<ValidateMessage> validateMessages) {
        this.isValid = isValid;
        this.originBean = originBean;
        this.validateMessages = validateMessages;
    }

    public boolean isValid() {
        return isValid;
    }

    public T getOriginBean() {
        return originBean;
    }

    public List<ValidateMessage> getValidateMessages() {
        return validateMessages;
    }

    public void setValidateMessages(List<ValidateMessage> validateMessages) {
        this.validateMessages = validateMessages;
    }
}
