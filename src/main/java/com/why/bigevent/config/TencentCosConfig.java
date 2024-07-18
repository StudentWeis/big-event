package com.why.bigevent.config;

import java.io.InputStream;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.region.Region;

@Configuration
public class TencentCosConfig {

    @Bean
    COSClient tencentCosClient() {
        Properties prop = new Properties();
        try (InputStream input = TencentCosConfig.class.getClassLoader().getResourceAsStream("secret.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find secret.properties");
            }
            prop.load(input);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        String secretId = prop.getProperty("secret.id");
        String secretKey = prop.getProperty("secret.key");
        Region region = new Region("ap-beijing");

        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        ClientConfig clientConfig = new ClientConfig(region);
        COSClient cosClient = new COSClient(cred, clientConfig);
        return cosClient;
    }
}
