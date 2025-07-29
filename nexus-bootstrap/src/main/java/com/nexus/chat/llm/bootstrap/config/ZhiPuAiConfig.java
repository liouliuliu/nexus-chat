package com.nexus.chat.llm.bootstrap.config;

import org.springframework.ai.zhipuai.ZhiPuAiChatModel;
import org.springframework.ai.zhipuai.api.ZhiPuAiApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZhiPuAiConfig {
    
    @Bean
    public ZhiPuAiApi zhiPuAiApi(@Value("${spring.ai.zhipuai.api-key}") String apiKey) {
        return new ZhiPuAiApi(apiKey);
    }
    
    @Bean
    public ZhiPuAiChatModel  zhiPuAiChatModel(ZhiPuAiApi zhiPuAiApi) {
        return new ZhiPuAiChatModel(zhiPuAiApi);
    }
}
