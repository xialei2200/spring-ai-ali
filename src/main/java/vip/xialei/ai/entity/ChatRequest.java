package vip.xialei.ai.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 聊天请求实体
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatRequest {
    
    /**
     * 问题内容
     */
    private String question;
    
    /**
     * 用户 ID（可选）
     */
    private String userId;
}
