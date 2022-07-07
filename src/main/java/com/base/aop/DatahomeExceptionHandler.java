package com.base.aop;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

// 全局异常处理 请求状态码默认为200
@RestControllerAdvice
public class DatahomeExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(DatahomeExceptionHandler.class);

    // Exception异常处理
    @ExceptionHandler(value = Exception.class)
    public String exceptionHandler(Exception e) {
        logger.error(e.getMessage(), e);
        return "系统异常";
    }

    // 接口字段校验@Validated异常处理
    @ExceptionHandler(value = BindException.class)
    public List<String> fieldExceptionHandler(BindException e) {
        List<FieldError> fieldErrors = e.getFieldErrors();
        List<String> messages = new ArrayList<>(fieldErrors.size());
        if (fieldErrors != null && fieldErrors.size() > 0) {
            for (int i = 0; i < fieldErrors.size(); i++) {
                messages.add(fieldErrors.get(i).getDefaultMessage());
            }
        }
        return messages;
    }

    // 业务层自定义返回错误数据
    @ExceptionHandler(value = ServiceException.class)
    public String serviceExceptionHandler(ServiceException e) {
        return e.getMessage();
    }

    @Data
    public static class ServiceException extends Exception {
        public ServiceException(String message) {
            super(message);
        }
    }

}
