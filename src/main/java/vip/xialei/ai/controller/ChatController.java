package vip.xialei.ai.controller;

import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * AI 聊天控制器
 */
@RestController
@RequestMapping("/model")
public class ChatController {

    @Autowired
    private ChatModel chatModel;

    public ChatController(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @RequestMapping(value = "/chat", method = RequestMethod.GET)
    public String chat(@RequestParam(value = "message", defaultValue = "你是什么模型？") String message) {
        return chatModel.call(message);
    }

    @RequestMapping(value = "/chatStream", method = RequestMethod.GET)
    public Flux<String> chatStream(@RequestParam(value = "message", defaultValue = "你是什么模型？") String message) {
        Flux<String> result = chatModel.stream(new UserMessage(message));
        return result;
    }
}
