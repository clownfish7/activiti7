package com.clownfish7.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;

/**
 * @author You
 * @create 2020-04-18 21:43
 * @desc 任务查询&完成
 * ACT_HI_ACTINST       已完成的活动信息
 * ACT_HI_IDENTITYLINK  参与者信息
 * ACT_HI_TASKINST      任务实例
 * ACT_RU_EXECUTION     执行表
 * ACT_RU_IDENTITYLINK  参与者信息
 * ACT_RU_TASK          任务
 */
public class ActivitiTaskQueryComplate {
    public static void main(String[] args) {
        // 1. 创建 ProcessEngine 对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 2. 得到 TaskService 实例
        TaskService taskService = processEngine.getTaskService();
        // 3. 根据流程定义的 key ，负责人 assignee 来实现当前用户的任务列表查询
        Task task = taskService.createTaskQuery()
                .processDefinitionKey("holiday")
//                .taskAssignee("张三")
                .singleResult();
        // 4. 任务输出
        System.out.println("流程实例ID：" + task.getProcessInstanceId());
        System.out.println("任务ID：" + task.getId());
        System.out.println("任务负责人：" + task.getAssignee());
        System.out.println("任务名称：" + task.getName());
        // 5. 任务完成
        taskService.complete(task.getId());
    }
}
