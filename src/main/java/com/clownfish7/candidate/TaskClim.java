package com.clownfish7.candidate;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;

import java.util.List;

/**
 * @author You
 * @create 2020-04-19 20:32
 */
public class TaskClim {
    public static void main(String[] args) {
        // 1. 创建 ProcessEngine 对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 2. 得到 TaskService 实例
        TaskService taskService = processEngine.getTaskService();
        // 3. 根据流程定义的 key ，候选人 userId 来实现当前用户的任务列表查询
        List<Task> taskList = taskService.createTaskQuery()
                .processDefinitionKey("holiday")
                .taskCandidateUser("userId")
                .list();
        // 4. 任务列表输出
        for (Task task : taskList) {
            System.out.println("流程实例ID：" + task.getProcessInstanceId());
            System.out.println("任务ID：" + task.getId());
            System.out.println("任务负责人：" + task.getAssignee());
            System.out.println("任务名称：" + task.getName());
            // 拾取任务
            taskService.claim(task.getId(), "assignee");
        }

        // 添加候选人
        taskService.addCandidateUser("taskId","userId");
        // 添加候选组
        taskService.addCandidateGroup("taskId","groupId");
    }
}
