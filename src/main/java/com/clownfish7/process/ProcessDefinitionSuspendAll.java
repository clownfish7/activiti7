package com.clownfish7.process;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;

import java.util.List;

/**
 * @author You
 * @create 2020-04-19 0:59
 */
public class ProcessDefinitionSuspendAll {
    public static void main(String[] args) {
        // 1. 创建 ProcessEngine 对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 2. 得到 RepositoryService 实例
        RepositoryService repositoryService = processEngine.getRepositoryService();
        // 3. 获取 ProcessDefinitionQuery 对象
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        // 4. 设置查询条件
        processDefinitionQuery.processDefinitionKey("holiday");
        // 5. 获取结果
        List<ProcessDefinition> processDefinitions = processDefinitionQuery.listPage(0, 1);
        // 6. 获取当前流程定义的实例是否为暂停状态
        boolean suspended = processDefinitions.get(0).isSuspended();
        // 7. 判断
        if (suspended) {
            // 说明是暂停状态，可以激活
            // param1 processDefinitionId（processDefinitionKey）:流程定义的id（key）
            // param2 activateProcessInstances：是否级联挂起该流程定义下的流程实例
            // param3 activationDate：设置挂起这个流程定义的时间，如果不填写，则立即挂起
            repositoryService.activateProcessDefinitionById(processDefinitions.get(0).getId(), true, null);
            System.out.println("流程实例:" + processDefinitions.get(0).getName() + " - 激活");
        } else {
            // 说明是激活状态，可以挂起
            // param1 processDefinitionId（processDefinitionKey）:流程定义的id（key）
            // param2 suspendProcessInstances：是否级联挂起该流程定义下的流程实例
            // param3 suspensionDate：设置挂起这个流程定义的时间，如果不填写，则立即挂起
            repositoryService.suspendProcessDefinitionById(processDefinitions.get(0).getId(), true, null);
            System.out.println("流程实例:" + processDefinitions.get(0).getName() + " - 挂起");
        }
    }
}
