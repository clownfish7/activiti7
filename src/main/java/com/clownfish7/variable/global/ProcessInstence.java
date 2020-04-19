package com.clownfish7.variable.global;

import com.clownfish7.variable.Holiday;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author You
 * @create 2020-04-19 15:47
 */
public class ProcessInstence {
    public static void main(String[] args) {
        // 1. 创建 ProcessEngine 对象
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        // 2. 得到 RuntimeService 实例
        RuntimeService runtimeService = defaultProcessEngine.getRuntimeService();
        // 3. 设置 RuntimeService Global 变量
        Map<String, Object> variable = new HashMap<>();
        variable.put("num", 1);
        variable.put("num2", 2L);
        variable.put("num3", "3str");
        variable.put("holiday", new Holiday().id("1001").num(10));
        // 4. 启动 ProcessInstence
        runtimeService.startProcessInstanceByKey("holiday", variable);
        // anotherWay 通过实例id设置变量 该流程实例必须未执行完成
        runtimeService.setVariables("processInstanceId",variable);
    }
}
