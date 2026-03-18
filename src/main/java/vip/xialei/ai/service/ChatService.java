package vip.xialei.ai.service;

import vip.xialei.ai.entity.ChatRequest;
import vip.xialei.ai.entity.ChatResponse;

/**
 * AI 聊天服务接口
 */
public interface ChatService {
    
    /**
     * 处理聊天请求
     * @param request 聊天请求
     * @return 聊天响应
     */
    ChatResponse chat(ChatRequest request);
    
    /**
     * 简单的聊天方法
     * @param question 问题
     * @return 回答
     */
    String chat(String question);
}
