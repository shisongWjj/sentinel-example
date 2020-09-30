package com.ss.sentinel.example;

import com.alibaba.csp.sentinel.cluster.client.config.ClusterClientAssignConfig;
import com.alibaba.csp.sentinel.cluster.client.config.ClusterClientConfig;
import com.alibaba.csp.sentinel.cluster.client.config.ClusterClientConfigManager;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.alibaba.csp.sentinel.init.InitFunc;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.ArrayList;
import java.util.List;

/**
 * FlowRuleInitFunc
 *
 * @author shisong
 * @date 2020/9/25
 */
public class FlowRuleInitFunc implements InitFunc {

    /**
     * nacos地址 standalone
     */
    private final String serverAddr = "192.168.137.1:8848";
    private final String groupId = "SENTINEL_GROUP";
    private final String dataId = "-flow-rule";
    private final String appName="App-Test";

    @Override
    public void init() throws Exception {
        //initFlowRules();
        loadClusterConfig();
        registerFlowRule();
    }

    private void initFlowRules(){
        List<FlowRule> flowRules = new ArrayList<>();
        FlowRule flowRule = new FlowRule();
        flowRule.setCount(2);
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        flowRule.setResource("doTest");
        flowRules.add(flowRule);
        FlowRuleManager.loadRules(flowRules);
    }

    private void loadClusterConfig(){
        ClusterClientAssignConfig assignConfig = new ClusterClientAssignConfig();
        //放置到配置中心
        assignConfig.setServerHost("localhost");
        assignConfig.setServerPort(9999);
        ClusterClientConfigManager.applyNewAssignConfig(assignConfig);
        //放置到配置中心
        ClusterClientConfig clusterClientConfig = new ClusterClientConfig();
        clusterClientConfig.setRequestTimeout(2000);
        ClusterClientConfigManager.applyNewConfig(clusterClientConfig);
    }

    private void registerFlowRule(){
        ReadableDataSource<String,List<FlowRule>> nacosDataSource = new NacosDataSource<List<FlowRule>>(serverAddr, groupId, appName+dataId, new Converter<String, List<FlowRule>>() {
            @Override
            public List<FlowRule> convert(String source) {
                return JSON.parseObject(source,new TypeReference<List<FlowRule>>(){});
            }
        });
        FlowRuleManager.register2Property(nacosDataSource.getProperty());
    }
}
