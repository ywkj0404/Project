package kr.co.hanbit.product.management.presentation;

import java.util.List;

public class ErrorMessage {

    private List<String> errorList;

    public ErrorMessage(List<String> errorList) {
        this.errorList = errorList;
    }

    public List<String> getErrorList() {
        return errorList;
    }

}
