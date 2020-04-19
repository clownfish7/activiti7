package com.clownfish7.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;

import java.io.InputStream;
import java.util.zip.ZipInputStream;

/**
 * @author You
 * @create 2020-04-18 20:17
 * @desc 部署相关流程
 * ACT_RE_DEPLOYMENT    部署信息
 * ACT_RE_PROCDEF       流程定义的一些信息
 * ACT_GE_BYTEARRAY     流程定义的bpmn文件和png文件
 */
public class ActivitiDeployment {
    public static void main(String[] args) {
        // 1. 创建 ProcessEngine 对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 2. 得到 RepositoryService 实例
        RepositoryService repositoryService = processEngine.getRepositoryService();
        // 3. 进行部署
//        Deployment deployment = repositoryService.createDeployment()
//                .addClasspathResource("diagram/holiday.bpmn")
//                .addClasspathResource("diagram/holiday.png")
//                .name("请假申请流程")
//                .deploy();
//        // 4. 输出部署的一些信息
//        System.out.println(deployment.getId());
//        System.out.println(deployment.getName());
//        System.out.println(deployment.getDeploymentTime());
//        System.out.println(deployment.getCategory());
//        System.out.println(deployment.getTenantId());
//        System.out.println(deployment.getKey());

        // zip 压缩包方式
        InputStream inputStream = ActivitiDeployment.class
                        .getClassLoader()
                        .getResourceAsStream("diagram/holidayBPMN.zip");
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);
        Deployment deployment = repositoryService.createDeployment()
                .addZipInputStream(zipInputStream)
                .name("请假申请流程")
                .deploy();
        // x. 输出部署的一些信息
        System.out.println(deployment.getId());
        System.out.println(deployment.getName());
        System.out.println(deployment.getDeploymentTime());
        System.out.println(deployment.getCategory());
        System.out.println(deployment.getTenantId());
        System.out.println(deployment.getKey());
    }
}
