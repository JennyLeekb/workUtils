package com.ld.support.beans.dto;

import com.ld.support.consts.BaseRequestBody;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @Description 查询商户合作信息的dto
 * @Author lkb
 * @CreateDate: 2019/10/19
 */
@Data
@Valid
public class ListLogDto extends BaseRequestBody {

    /**
     * 公海表id
     */
    @NotNull(message = "shopPublicId 为空")
    private Integer shopPublicId;

    /**
     * 分页-第几页
     */
    @NotNull(message = "pageNum 为空")
    private Integer pageNum;

    /**
     * 分页-页大小
     */
    @NotNull(message = "pageSize 为空")
    private Integer pageSize;

    /**
     * 0-普通用户 1-KA用户
     */
    @NotNull(message = "shopType 为空")
    private Integer shopType;


}
