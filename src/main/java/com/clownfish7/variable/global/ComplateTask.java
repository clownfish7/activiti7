package com.clownfish7.variable.global;

import com.clownfish7.variable.Holiday;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author You
 * @create 2020-04-19 18:27
 */
public class ComplateTask {
    public static void main(String[] args) {
        // 1. 创建 ProcessEngine 对象
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        // 2. 得到 TaskService 实例
        TaskService taskService = defaultProcessEngine.getTaskService();
        // 3. 设置 TaskService Global 变量
        Map<String, Object> variable = new HashMap<>();
        variable.put("num", 1);
        variable.put("num2", 2L);
        variable.put("num3", "3str");
        variable.put("holiday", new Holiday().id("1001").num(10));
        // 4. 完成任务并传递 variable
        taskService.complete("taskId", variable);
        // anotherWay 通过taskId设置变量 该任务id必须是当前待办任务id，ACT_RU_TASK中存在，如果已结束则报错
        taskService.setVariables("taskId",variable);
    }
}
