package com.clownfish7.lintener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

/**
 * @author You
 * @create 2020-04-19 2:00
 * <p>
 * bpmn add taskListener
 * Creat        任务创建后触发
 * Assignment   任务分配后触发
 * Delete       任务完成后触发
 * ALl          所有事件发生都触发
 * <p>
 * UEL
 * UEL-value    ${assignee}
 * UEL-value    ${user.assingee}    表示调用 user.getAssignee();
 * UEL-method   ${holodayBean.getHolidayId()}   userBean是spring容器中的一个bean，表示调用该bean的方法
 * UEL-method   ${IdapService.findManager4Employee(emp)}    表示spring容器中的一个bean，并调用该方法，emp为activiti流程变量，作为参数传递到该方法中
 * other        ${order.price > 100 && order.price < 250}   表达式持支解析基础类型、bean、list、array和map也可作为条件判断
 * 注意事项：
 * 1. 如果UEL表达式中的流程变量名不存在则报错
 * 2. 如果UEL表达式中的流程变量值为空null，流程不按UEL表达式去执行，而流程结束
 * 3. 如果UEL表达式都不符合条件，流程结束
 * 4. 如果连线不设置条件，会走flow序号小的那条线
 */
public class MyListener implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        delegateTask.setAssignee("clownfish7");
    }
}
