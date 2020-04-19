package com.clownfish7.process;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;

/**
 * @author You
 * @create 2020-04-18 23:16
 * @desc 删除相关流程
 * ACT_RE_DEPLOYMENT    部署信息
 * ACT_RE_PROCDEF       流程定义的一些信息
 * ACT_GE_BYTEARRAY     流程定义的bpmn文件和png文件
 * 注意事项：
 * 1.   当我们正在执行的这一套流程没有完全审批结束的时候，此时要删除定义的流程就会失败
 * 2.   如果要强制删除可以使用 repositoryService.deleteDeployment("", true);
 * 3.   true 代表级联删除，此时会先删除没有完成的流程节点，最后删除流程定义信息 默认值 false
 */
public class ProcessDefinitonDelete {
    public static void main(String[] args) {
        // 1. 创建 ProcessEngine 对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 2. 得到 RepositoryService 实例
        RepositoryService repositoryService = processEngine.getRepositoryService();
        // 3. 执行删除流程定义，参数代表流程定义id
        repositoryService.deleteDeployment("", true);
    }
}
