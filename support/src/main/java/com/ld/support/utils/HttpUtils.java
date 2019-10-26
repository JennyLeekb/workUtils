package com.ld.support.utils;

import com.alibaba.fastjson.JSONObject;
import com.ld.support.consts.BaseReturnVo;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;

/**
 * @Description
 * @Author lkb
 * @CreateDate: 2019/10/19
 */
public class HttpUtils {

    private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    /**
     * 功能描述 Get 请求
     * @author lkb
     * @date 2019/10/21
     * @param
     * @return com.ld.support.consts.BaseReturnVo
     */
    public static BaseReturnVo get(URI uri){
        CloseableHttpResponse httpResponse = null;
        try{
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(uri);
            httpResponse = httpClient.execute(httpGet);
            Object result = null;
            /**请求发送成功，并得到响应**/
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                /**读取服务器返回过来的json字符串数据**/
                String str = EntityUtils.toString(httpResponse.getEntity());
                JSONObject jsonResult = JSONObject.parseObject(str);
                result = jsonResult.get("data");
            }
            return BaseReturnVo.success(result);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return BaseReturnVo.fail(e.getMessage());
        }finally {
            try{
                httpResponse.close();
            }catch (IOException e){
                logger.error(e.getMessage(),e);
                return BaseReturnVo.fail(e.getMessage());
            }
        }
    }

}
