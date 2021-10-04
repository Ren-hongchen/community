package top.renhongchen.community.exception;

public class CustomizeHardException extends RuntimeException{
    private String message;

    public CustomizeHardException(CustomizeErrorCode errorCode) {
        this.message = errorCode.getMessage();
    }

    public CustomizeHardException(String message, Integer code) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
