package com.ccut.dachuang.Exception;

import com.ccut.dachuang.common.CommonResponse;
import com.ccut.dachuang.common.ErrorEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class globalExceptionProcessor {

    @ExceptionHandler(RuntimeException.class)
    public <T> CommonResponse<T>  runtimeException(RuntimeException exception){
        log.info("[运行异常],[{}]",exception.getMessage());
        return new CommonResponse<>(exception.getMessage(),null,"444");
    }

    @ExceptionHandler(CustomizeException.class)
    public <T> CommonResponse<T> customizeException(CustomizeException exception){
        ErrorEnum error = exception.getError();
        log.info("[发生自定义错误] : [状态码:{}  异常信息:{}  描述:{} ]",error.getStatusCode(),error.getMessage(),error.getDescribe());
        return new CommonResponse<>(error.getMessage(),null,error.getStatusCode());
    }

}
