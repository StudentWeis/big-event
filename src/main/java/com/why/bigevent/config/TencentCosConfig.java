package com.why.bigevent.config;

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
        String secretId = "AKIDlMlvrZN4WpBPI3ZtyufIix1Wu4zfGNLB";
        String secretKey = "lKgsreG0d6RpkV3MVPTNApJcibMzXyIV";
        Region region = new Region("ap-beijing");

        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        ClientConfig clientConfig = new ClientConfig(region);
        COSClient cosClient = new COSClient(cred, clientConfig);
        return cosClient;
    }
}
