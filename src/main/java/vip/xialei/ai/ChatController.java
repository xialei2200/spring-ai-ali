package vip.xialei.ai;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
public class ChatController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String chat(String question) {
        return "";
    }
}
