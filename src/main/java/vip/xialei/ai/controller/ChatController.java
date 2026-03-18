package vip.xialei.ai.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vip.xialei.ai.entity.ChatRequest;
import vip.xialei.ai.entity.ChatResponse;
import vip.xialei.ai.service.ChatService;

/**
 * AI 聊天控制器
 */
@RestController
@RequestMapping("/model")
public class ChatController {
    
    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @RequestMapping(value = "/chat", method = RequestMethod.GET)
    public String chat(@RequestParam("question") String question) {
        return chatService.chat(question);
    }
    
    @RequestMapping(value = "/advanced", method = RequestMethod.POST)
    public ChatResponse advancedChat(@RequestParam ChatRequest request) {
        return chatService.chat(request);
    }
}
