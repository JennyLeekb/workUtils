package com.ld.support.controller;

import com.ld.support.consts.BaseReturnVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @Description 用户控制器
 * @Author lkb
 * @CreateDate: 2019/10/21
 */
@RestController
@RequestMapping("/user")
@Validated
public class UserController {


    @GetMapping("/getByUserId")
    public BaseReturnVo getByUserId(@Valid @NotNull(message = "userId 为空") Integer userId){

        return BaseReturnVo.success();
    }



}
