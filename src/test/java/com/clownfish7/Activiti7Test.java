package com.clownfish7;

import org.activiti.engine.*;
import org.junit.Test;

/**
 * @author You
 * @create 2020-04-18 15:35
 * @desc 生成activiti对应25张表
 */
public class Activiti7Test {

    @Test
    public void testGenTable() {
        // 1. 创建 ProcessEngineConfiguration
        ProcessEngineConfiguration configuration =
                ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
            //  ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml","beanName");
        // 2. 通过 ProcessEngineConfiguration 创建的 ProcessEngine ,此时会创建数据库
        ProcessEngine processEngine = configuration.buildProcessEngine();
        // 3. 输出 processEngine 对象
        System.out.println(processEngine);

        // ACT_RE_* RE -> repository    包含流程定义和流程静态资源 图片、规则等等
        // ACT_RU_* RU -> runtime       运行时的表，包含流程实例、任务、变量、异步任务等运行中数据 exist with running / delete with end
        // ACT_HI_* HI -> history       包含历史数据，历史流程实例、变量、任务等等
        // ACT_GE_* GE -> general       通用数据，用于不同场景下

        // RepositoryService    activiti 的资源管理类
        RepositoryService repositoryService = processEngine.getRepositoryService();
        // RuntimeService       activiti 的流程运行管理类
        RuntimeService runtimeService = processEngine.getRuntimeService();
        // TaskService          activiti 的任务管理类
        TaskService taskService = processEngine.getTaskService();
        // HistoryService       activiti 的历史管理类
        HistoryService historyService = processEngine.getHistoryService();
        // ManagerService       activiti 的引擎管理类
        ManagementService managementService = processEngine.getManagementService();

    }

    @Test
    public void testGenTableAnother() {
        // must default configFileName = activiti.cfg.xml  beanName = processEngineConfiguration
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
    }

}
