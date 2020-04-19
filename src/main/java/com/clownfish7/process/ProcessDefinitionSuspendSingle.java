package com.clownfish7.process;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;

/**
 * @author You
 * @create 2020-04-19 0:59
 */
public class ProcessDefinitionSuspendSingle {
    public static void main(String[] args) {
        // 1. 创建 ProcessEngine 对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 2. 得到 RepositoryService 实例
        RuntimeService runtimeService = processEngine.getRuntimeService();
        // 3. 获取 ProcessDefinitionQuery 对象
        ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery();
        // 4. 设置查询条件
        processInstanceQuery.processInstanceBusinessKey("2020");
        // 5. 获取结果
        ProcessInstance processInstance = processInstanceQuery.singleResult();
        // 6. 获取当前流程定义的实例是否为暂停状态
        boolean suspended = processInstance.isSuspended();
        // 7. 判断
        if (suspended) {
            // 说明是暂停状态，可以激活
            // param1 processInstanceId : 流程定义的id
            runtimeService.activateProcessInstanceById(processInstance.getId());
            System.out.println("流程实例:" + processInstance.getId() + " - 激活");
        } else {
            // 说明是激活状态，可以挂起
            // param1 processInstanceId : 流程定义的id
            runtimeService.suspendProcessInstanceById(processInstance.getId());
            System.out.println("流程实例:" + processInstance.getId() + " - 挂起");
        }
    }
}
