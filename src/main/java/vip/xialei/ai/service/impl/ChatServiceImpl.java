package vip.xialei.ai.service.impl;

import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import vip.xialei.ai.entity.ChatRequest;
import vip.xialei.ai.entity.ChatResponse;
import vip.xialei.ai.service.ChatService;

/**
 * AI 聊天服务实现类
 */
@Service
public class ChatServiceImpl implements ChatService {

    private final OpenAiChatModel chatModel;
    
    @Autowired
    public ChatServiceImpl(@Qualifier("deepseek-v3.2") OpenAiChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @Override
    public ChatResponse chat(ChatRequest request) {
        try {
            var response = chatModel.call(new Prompt(request.getQuestion()));
            
            return ChatResponse.builder()
                    .content(response.getResult().getOutput().getText())
                    .model("deepseek-v3.2")
                    .success(true)
                    .build();
        } catch (Exception e) {
            return ChatResponse.builder()
                    .content(null)
                    .model("deepseek-v3.2")
                    .success(false)
                    .errorMessage(e.getMessage())
                    .build();
        }
    }

    @Override
    public String chat(String question) {
        var response = chatModel.call(new Prompt(question));
        return response.getResult().getOutput().getText();
    }
}
