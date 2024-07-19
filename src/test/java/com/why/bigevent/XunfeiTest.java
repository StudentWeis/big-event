package com.why.bigevent;

import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.why.bigevent.config.TencentCosConfig;
import com.why.bigevent.utils.MyUtil;

@SpringBootTest
public class XunfeiTest {

    private static String url = "https://audit.iflyaisol.com//audit/v2/syncText";
    private static final String content = "塔利班组织联合东突组织欲图";// 送检文本

    @Test
    public void test() throws Exception {

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
                "  \"content\": \"" + content + "\"\n" +
                "}";
        // 获取鉴权
        Map<String, String> urlParams = MyUtil.getAuth(APPID, APIKey, APISecret);
        // 发起请求
        String returnResult = MyUtil.doPostJson(url, urlParams, json);
        System.out.println("文本合规返回结果：\n" + returnResult);
    }

}
