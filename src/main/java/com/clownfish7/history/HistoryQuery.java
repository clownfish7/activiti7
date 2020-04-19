package com.clownfish7.history;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricActivityInstanceQuery;

import java.util.List;

/**
 * @author You
 * @create 2020-04-19 0:29
 */
public class HistoryQuery {
    public static void main(String[] args) {
        // 1. 创建 ProcessEngine 对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 2. 得到 HistoryService 实例
        HistoryService historyService = processEngine.getHistoryService();
        // 3. 获取 HistoricActivityInstanceQuery 对象
        HistoricActivityInstanceQuery historicActivityInstanceQuery = historyService.createHistoricActivityInstanceQuery();
        // 4. 设置查询条件
        historicActivityInstanceQuery
                // 流程实例ID
                .processInstanceId("10001")
                // 流程定义ID
                .processDefinitionId("holiday:1:7504")
                // 按开始时间排序
                .orderByHistoricActivityInstanceStartTime()
                // 升序
                .asc()
        ;
        // 5. 执行查询
        List<HistoricActivityInstance> list = historicActivityInstanceQuery.list();
        // 6. 输出结果
        for (HistoricActivityInstance historicActivityInstance : list) {
            System.out.println("" + historicActivityInstance.getId());
            System.out.println("" + historicActivityInstance.getActivityId());
            System.out.println("" + historicActivityInstance.getActivityType());
            System.out.println("" + historicActivityInstance.getActivityName());
            System.out.println("" + historicActivityInstance.getProcessInstanceId());
            System.out.println("" + historicActivityInstance.getProcessDefinitionId());
            System.out.println("" + historicActivityInstance.getStartTime());
            System.out.println("" + historicActivityInstance.getEndTime());
            System.out.println("" + historicActivityInstance.getAssignee());
            System.out.println("--------------------------------------");
        }
    }
}
