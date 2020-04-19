package com.clownfish7.process;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

/**
 * @author You
 * @create 2020-04-18 23:42
 * @desc 需求：
 * 1.   从 Activiti 的 ACT_GE_BYTEARRAY 表中读取两个资源文件
 * 2.   将两个资源文件保存到路径 D:\
 * <p>
 * 技术方案：
 * 1.   第一种方案使用 activiti 的 api 实现
 * 2.   第二种方案其实就是原理层，使用 jdbc 的对 blob，clob 类型数据的读取并保存，IO 流转换，commons-io.jar 包可以轻松解决 IO 操作
 * <p>
 * 真实场景：用户想查看这个请假流程具体有哪些步骤要走？
 */
public class BpmnFileQuery {
    public static void main(String[] args) throws IOException {
        // 1. 创建 ProcessEngine 对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 2. 得到 RepositoryService 实例
        RepositoryService repositoryService = processEngine.getRepositoryService();
        // 3. 得到 ProcessDefinitionQuery 对象
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        // 4. 设置查询条件
        processDefinitionQuery.processDefinitionKey("holiday")
                .orderByProcessDefinitionVersion()
                .desc();
        // 5. 执行查询操作，查询出想要的流程定义
        List<ProcessDefinition> list = processDefinitionQuery.list();
        // 6. 通过流程定义信息，得到部署 id
        String deploymentId = list.get(0).getDeploymentId();
        // 7. 通过 RepositoryService 的方法，实现读取资源文件信息（输入流）
        //    param1 : deploymentId
        //    param2 : fileName
        //    getResourceName           : bpmnName
        //    getDiagramResourceName    : imageName
        InputStream bpmnIs = repositoryService.getResourceAsStream(deploymentId, list.get(0).getResourceName());
        InputStream imgIs = repositoryService.getResourceAsStream(deploymentId, list.get(0).getDiagramResourceName());
        // 8. 构建输出流 OutPutStream
//        OutputStream bpmnFos = new FileOutputStream("D:\\" + list.get(0).getResourceName());
//        OutputStream imgFos = new FileOutputStream("D:\\" + list.get(0).getDiagramResourceName());
        // 9. 输入流 输出流转换
//        IOUtils.copy(bpmnIs, bpmnFos);
        Files.copy(bpmnIs, Paths.get("D:\\" + list.get(0).getResourceName()), StandardCopyOption.REPLACE_EXISTING);
        Files.copy(imgIs, Paths.get("D:\\" + list.get(0).getDiagramResourceName()), StandardCopyOption.REPLACE_EXISTING);
        // 关闭流
        bpmnIs.close();
        imgIs.close();
    }
}
