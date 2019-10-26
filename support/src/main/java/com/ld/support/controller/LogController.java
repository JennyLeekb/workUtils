package com.ld.support.controller;

import com.ld.support.beans.dto.ListLogDto;
import com.ld.support.consts.BaseReturnVo;
import com.ld.support.consts.UrlConst;
import com.ld.support.utils.HttpUtils;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @Description: 查询商户合作记录
 * @Author lkb
 * @CreateDate: 2019/10/19
 */
@RestController
@RequestMapping("/shop")
@Validated
public class LogController {

    private Logger logger = LoggerFactory.getLogger(LogController.class);

    @GetMapping("/listLog")
    public BaseReturnVo listShopLog(@Valid @RequestBody ListLogDto logDto){
        try {
            String s = "";
            if(logDto.getEnvironment() == 0){
                s = UrlConst.HTTP ;
            }else if(logDto.getEnvironment() == 1){
                s = UrlConst.HTTPS ;
            }else {
                return BaseReturnVo.fail("请选择环境!");
            }
            URI uri = new URIBuilder().setScheme(s)
                    .setHost(UrlConst.HOST)
                    .setPath(UrlConst.LIST_LOG)
                    .setParameter("access_token", logDto.getAccess_token())
                    .setParameter("shopId", String.valueOf(logDto.getShopPublicId()))
                    .setParameter("pageNum", String.valueOf(logDto.getPageNum()))
                    .setParameter("pageSize", String.valueOf(logDto.getPageSize()))
                    .setParameter("shopType", String.valueOf(logDto.getShopType()))
                    .build();
            return HttpUtils.get(uri);
        }catch (URISyntaxException e){
            logger.error(e.getMessage(),e);
            return BaseReturnVo.fail(e.getMessage());
        }
    }




}
