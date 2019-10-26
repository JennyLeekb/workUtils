package com.ld.support.consts;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @Description
 * @Author lkb
 * @CreateDate: 2019/10/19
 */
@Valid
@Data
public class BaseRequestBody {
    /**
     * token
     */
    @NotNull(message = "token 为空")
    private String access_token;

    /**
     * 环境  0-测试 1-预发布
     */
    @NotNull(message = "environment 为空")
    private Integer environment;

}
