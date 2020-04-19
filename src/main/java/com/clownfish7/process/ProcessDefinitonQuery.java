package com.clownfish7.process;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;

import java.util.List;

/**
 * @author You
 * @create 2020-04-18 23:16
 */
public class ProcessDefinitonQuery {
    public static void main(String[] args) {
        // 1. 创建 ProcessEngine 对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 2. 得到 RepositoryService 实例
        RepositoryService repositoryService = processEngine.getRepositoryService();
        // 3. 得到 ProcessDefinitionQuery 对象，可以认为是一个查询器
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        // 4. 设置条件并查询出当前的所有流程定义，查询条件：流程定义的 key = "holiday"
        List<ProcessDefinition> list = processDefinitionQuery.processDefinitionKey("holiday")
                // 根据流程版本号进行排序
                .orderByProcessDefinitionVersion()
                .desc()
                .list();
        // 5. 输出流程定义信息
        list.forEach(processDefinition -> {
            System.out.println("流程定义ID ：" + processDefinition.getId());
            System.out.println("流程定义名称：" + processDefinition.getName());
            System.out.println("流程定义Key：" + processDefinition.getKey());
            System.out.println("流程部署ID ：" + processDefinition.getDeploymentId());
            System.out.println("流程定义版本号：" + processDefinition.getVersion());
            System.out.println("--------------------------------------------");
        });
    }
}
