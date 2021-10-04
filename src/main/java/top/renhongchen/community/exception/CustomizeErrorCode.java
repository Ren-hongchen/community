package top.renhongchen.community.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode {

    QUESTION_NOT_FOUND("你找的问题不在了，要不要换个试试？","101"),
    PARENTID_NOT_FOUND("评论不在，要不要换个试试？","201"),
    TYPE_IS_WRONG("请求错误，请重试","202"),
    NOT_LOGIN("未登录，请登录","001");

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getCode() {
        return code;
    }

    private String message;
    private String code;

    CustomizeErrorCode(String message,String code) {
        this.message = message;
        this.code = code;
    }

    public String getMessageByCode(String code) {
        for (CustomizeErrorCode customizeErrorCode: CustomizeErrorCode.values()
             ) {
            if(customizeErrorCode.getCode() == code) {
                return customizeErrorCode.getMessage();
            }
        }
        return null;
    }
}