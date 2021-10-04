package top.renhongchen.community.exception;

public class CustomizeAppException extends RuntimeException{
    private String code;

    public CustomizeAppException(CustomizeErrorCode errorCode) {
        this.code = errorCode.getCode();
    }

    public CustomizeAppException(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return code;
    }

}
