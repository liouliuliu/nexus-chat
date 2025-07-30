package com.nexus.chat.llm.ai.http;

import com.openai.client.OpenAIClient;
import com.openai.core.http.StreamResponse;
import com.openai.models.chat.completions.ChatCompletion;
import com.openai.models.chat.completions.ChatCompletionChunk;
import com.openai.models.chat.completions.ChatCompletionCreateParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.function.Consumer;

@Slf4j
@RestController()
@CrossOrigin("*")
@RequestMapping("/api/v1/qianFan/")
public class QianFanController {
    
    @Qualifier("qianFanClient")
    private OpenAIClient openAIClient;
    
    
    @RequestMapping("/ai/generate")
    public String generate(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        ChatCompletionCreateParams params = ChatCompletionCreateParams.builder()
                .addUserMessage(message) 
                .model("ernie-tiny-8k") 
                .build();
        ChatCompletion chatCompletion = openAIClient.chat().completions().create(params);
        chatCompletion.choices().forEach(choice -> {
            log.info("Choice: {}", choice.message().content());
        });
        return chatCompletion.choices().get(0).message().content().get();
    }
    
//    @RequestMapping("/ai/generateStream")
//    public Flux<ChatResponse> generateStream(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
//        ChatCompletionCreateParams params = ChatCompletionCreateParams.builder()
//                .addUserMessage("你好") // 对话messages信息
//                .model("ernie-tiny-8k") // 模型对应的model值，请查看支持的模型列表：https://cloud.baidu.com/doc/WENXINWORKSHOP/s/wm7ltcvgc
//                .build();
//
//        StreamResponse<ChatCompletionChunk> chatCompletion = openAIClient.chat().completions().createStreaming(params);
//        Consumer<ChatCompletionChunk> consumer = s -> System.out.println(s.choices().get(0).delta().content());
//        chatCompletion.stream().forEach(consumer);
//
////        return qianFanChatModel.stream(message);
//    }
}
