package com.ld.support.aop;

import com.ld.support.consts.BaseReturnVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: 全局异常处理
 * @Author lkb
 * @CreateDate: 2019/10/19
 */
@ControllerAdvice
public class ExceptionAdvice {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public BaseReturnVo handleException(HttpServletRequest request, HttpServletResponse response,
                                        Exception e) {
        if (e instanceof BindException) {
            BindException bindException = (BindException) e;
            BindingResult result = bindException.getBindingResult();
            List<ObjectError> allErrors = result.getAllErrors();
            StringBuilder sb = new StringBuilder();
            for (ObjectError item : allErrors) {
                sb.append(((FieldError) item).getField());
                sb.append(":");
                sb.append(item.getDefaultMessage());
                sb.append(";");
            }
            response.setStatus(200);
            return new BaseReturnVo(200, sb.toString());
        }
        if (e instanceof javax.validation.ConstraintViolationException) {
            ConstraintViolationException constraintViolationException = (ConstraintViolationException) e;
            response.setStatus(200);
            return new BaseReturnVo(500, constraintViolationException.getMessage());
        } else if (e instanceof MethodArgumentNotValidException) {
            BindingResult bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();
            String errorMsg = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining("|"));
            response.setStatus(200);
            return new BaseReturnVo(500, errorMsg);
        } else if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) {
            logger.info("No mapping exception", e);
            response.setStatus(404);
            return BaseReturnVo.fail("No mapping exception");
        }  //夸服务调用异常
        else {
            logger.error("发生未捕获的异常:", e);
            return new BaseReturnVo(1,
                    "系统异常");
        }
    }
}

