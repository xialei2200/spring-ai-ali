package vip.xialei.ai;

import io.micrometer.observation.ObservationRegistry;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.model.tool.ToolCallingManager;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.ai.retry.RetryUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.support.RetryTemplate;

@SpringBootApplication
public class SpringAiAliApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SpringAiAliApplication.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);
        System.out.println("启动成功");

        var openAiApi = OpenAiApi.builder()
                .apiKey("sk-CmjMbzjAytDCRI91540fYeGJE8hGq1GFw1uXv5ydE3zSpKXE")
                .baseUrl("https://api.openai-proxy.org")
                .build();
        var openAiChatOptions = OpenAiChatOptions.builder()
                .model("deepseek-v3.2")
                .temperature(0.4)
                .maxTokens(20000)
                .build();
        var chatModel = new OpenAiChatModel(openAiApi, openAiChatOptions,
                ToolCallingManager.builder().build(),
                RetryUtils.DEFAULT_RETRY_TEMPLATE, ObservationRegistry.NOOP);

        ChatResponse response = chatModel.call(
                new Prompt("请写一首关于春天的现代诗!"));
        System.out.println(response);

    }

}
