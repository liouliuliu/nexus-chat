package com.nexus.chat.llm.bootstrap.config;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QianFanConfig {
    
    /*@Bean
    public QianFanApi qianFanApi(@Value("${spring.ai.qianfan.api-key}") String apiKey,
                                 @Value("${spring.ai.qianfan.secret-key}") String secretKey){
        return new QianFanApi(apiKey, secretKey);
    }
    
    @Bean
    public QianFanChatModel qianFanChatModel(QianFanApi qianFanApi){
        return new QianFanChatModel(qianFanApi);
    }*/
    
    @Bean("qianFanClient")
    public OpenAIClient QianFanClient(@Value("${spring.ai.qianfan.api-key}") String apiKey,
                                      @Value("${spring.ai.qianfan.base-url}") String baseUrl){
        return OpenAIOkHttpClient.builder()
                .apiKey(apiKey) //将your_APIKey替换为真实值，如何获取API Key请查看https://cloud.baidu.com/doc/WENXINWORKSHOP/s/Um2wxbaps#步骤二-获取api-key
                .baseUrl(baseUrl) //千帆ModelBuilder平台地址
                .build();
    }
    
}
