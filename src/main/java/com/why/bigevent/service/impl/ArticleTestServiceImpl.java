package com.why.bigevent.service.impl;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Properties;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.why.bigevent.config.TencentCosConfig;
import com.why.bigevent.service.ArticleTestService;
import com.why.bigevent.utils.MyUtil;

@Service
public class ArticleTestServiceImpl implements ArticleTestService {

    private static String url = "https://audit.iflyaisol.com//audit/v2/syncText";

    @RabbitListener(queues = "hello.queue2")
    public void test(String articleContent) {
        Properties prop = new Properties();
        try (InputStream input = TencentCosConfig.class.getClassLoader().getResourceAsStream("secret.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find secret.properties");
            }
            prop.load(input);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        String APPID = prop.getProperty("secret.appid");
        String APISecret = prop.getProperty("secret.apisecret");
        String APIKey = prop.getProperty("secret.apikey");

        /**
         * 业务参数
         * --- 如果需要使用黑白名单资源，放开lib_ids与categories参数
         */
        String json = "{\n" +
                "  \"is_match_all\": 1,\n" +
                "  \"content\": \"" + articleContent + "\"\n" +
                "}";
        // 获取鉴权
        try {
            Map<String, String> urlParams = MyUtil.getAuth(APPID, APIKey, APISecret);
            String returnResult = MyUtil.doPostJson(url, urlParams, json);
            System.out.println("文本合规返回结果：\n" + returnResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("审核完毕" +LocalDateTime.now());
    }

}
