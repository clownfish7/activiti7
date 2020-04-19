package com.clownfish7.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;

/**
 * @author You
 * @create 2020-04-18 21:06
 * @desc 启动流程实例
 * ACT_HI_ACTINST       已完成的活动信息
 * ACT_HI_IDENTITYLINK  参与者信息
 * ACT_HI_PROCINST      流程实例
 * ACT_HI_TASKINST      任务实例
 * ACT_RU_EXECUTION     执行表
 * ACT_RU_IDENTITYLINK  参与者信息
 * ACT_RU_TASK          任务
 */
public class ActivitiStartInstence {
    public static void main(String[] args) {
        // 1. 创建 ProcessEngine 对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 2. 得到 RuntimeService 实例
        RuntimeService runtimeService = processEngine.getRuntimeService();
        // 3. 创建流程实例，需要指定流程定义的
        // param1 : ProcessInstanceKey
        // param2 : BusinessKey
        ProcessInstance instance = runtimeService.startProcessInstanceByKey("holiday","2020");
        // 4. 输出实例的相关信息
        System.out.println("流程部署ID：" + instance.getDeploymentId());
        System.out.println("流程定义ID：" + instance.getProcessDefinitionId());
        System.out.println("活动实例ID：" + instance.getId());
        System.out.println("活动ID：" + instance.getActivityId());
    }
}
