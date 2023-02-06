package com.tuling.dynamic.datasource.annoAop;

import com.ryan.test.MessageType;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author liuzongshuai
 * @date 2023/1/29 14:33
 */
@MessageType(type = 10)
@Component
@Data
public class LogMessage {

    /**
     * 模块编号
     */
    private String moduleName;

    /**
     * 行为编号
     */
    private String actionName;
}
