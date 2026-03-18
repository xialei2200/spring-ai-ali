package vip.xialei.ai.configuration;

import com.alibaba.cloud.ai.dashscope.api.DashScopeApi;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatModel;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatOptions;
import com.alibaba.cloud.ai.dashscope.chat.observation.DashScopeChatModelObservationConvention;
import io.micrometer.observation.ObservationRegistry;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.model.tool.ToolCallingManager;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.ai.retry.RetryUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

/**
 * Spring AI 配置类
 */
@Configuration
public class AiConfiguration {

    @Value("${spring.ai.close.ai.openai-api-key}")
    private String closeAiApiKey;
    @Value("${spring.ai.close.ai.openai-base-url}")
    private String closeAiBaseUrl;
    @Value("${spring.ai.close.ai.dashScopeApiKey}")
    private String dashScopeApiKey;

    @Bean
    public OpenAiApi openAiApi() {
        return OpenAiApi.builder()
                .apiKey(closeAiApiKey)
                .baseUrl(closeAiBaseUrl)
                .build();
    }

    @Bean
    public OpenAiChatOptions openAiChatOptions() {
        return OpenAiChatOptions.builder()
                .model("deepseek-v3.2")
                .temperature(0.5)
                .maxTokens(200000)
                .build();
    }

    @Bean("deepseek-v3.2")
    public OpenAiChatModel deepseek(OpenAiApi openAiApi, OpenAiChatOptions openAiChatOptions) {
        return new OpenAiChatModel(openAiApi, openAiChatOptions,
                ToolCallingManager.builder().build(),
                RetryUtils.DEFAULT_RETRY_TEMPLATE,
                ObservationRegistry.NOOP);
    }
    @Bean("qwen")
    public ChatModel qwen() {
        DashScopeApi dashScopeApi = DashScopeApi.builder()
                .apiKey(dashScopeApiKey)
                .baseUrl("https://dashscope.aliyuncs.com")
                .build();
        // Create DashScope ChatModel instance
        DashScopeChatModel chatModel = DashScopeChatModel.builder()
                .dashScopeApi(dashScopeApi)
                .defaultOptions(DashScopeChatOptions.builder()
                        .model("qwen3-max")
                        .temperature(0.5)
                        .maxToken(10000)
                        .build())
                .build();
        Flux<String> stream = chatModel.stream("请写一个关于春天的现代诗，要求10行以内");
        // 流式打印到控制台
        System.out.println("流式打印：");
        stream.subscribe(System.out::print);
        System.out.println();
        return chatModel;
    }



}
