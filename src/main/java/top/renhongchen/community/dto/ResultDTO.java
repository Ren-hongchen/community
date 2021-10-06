package top.renhongchen.community.dto;

import lombok.Data;
import top.renhongchen.community.exception.CustomizeAppException;
import top.renhongchen.community.exception.CustomizeErrorCode;

@Data
public class ResultDTO {
    private String code;
    private String message;

    public static ResultDTO errorOf(String code,String message) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        return resultDTO;
    }

    public static ResultDTO okOf() {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode("200");
        resultDTO.setMessage("成功");
        return resultDTO;
    }

    public static ResultDTO errorOf(CustomizeErrorCode errorCode) {
        return errorOf(errorCode.getCode(), errorCode.getMessage());
    }

}
