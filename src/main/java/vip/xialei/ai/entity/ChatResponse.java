package vip.xialei.ai.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 聊天响应实体
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatResponse {
    
    /**
     * 响应内容
     */
    private String content;
    
    /**
     * 使用的模型
     */
    private String model;
    
    /**
     * 是否成功
     */
    private Boolean success;
    
    /**
     * 错误信息（如果有）
     */
    private String errorMessage;
}
