package io.kimmking.rpcfx.client;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import io.kimmking.rpcfx.api.*;
import jdk.nashorn.internal.runtime.GlobalConstants;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public final class Rpcfx {

    private static CuratorFramework client = CuratorFrameworkFactory.newClient("127.0.0.1:2181",
            new RetryNTimes(10, 5000));

    static {
        ParserConfig.getGlobalInstance().addAccept("io.kimmking");
    }

    public static <T, filters> T createFromRegistry(final Class<T> serviceClass, final String zkUrl, Router router, LoadBalancer loadBalance, Filter filter) {

        // 加filte之一

        // curator Provider list from zk
        List<String> invokers = new ArrayList<>();
        // 1. 简单：从zk拿到服务提供的列表
        // 2. 挑战：监听zk的临时节点，根据事件更新这个list（注意，需要做个全局map保持每个服务的提供者List）

        try {
            client.start();
            List<String> children = client.getChildren().usingWatcher(new CuratorWatcher()
            {
                @Override
                public void process(WatchedEvent event) throws Exception {
                    System.out.println("监控：" + event);
                    updateServers();
                }
            }).forPath("/rpcfx");

            List<String> leaf = client.getChildren().forPath("/rpcfx/" + serviceClass.getName());
            if (!CollectionUtils.isEmpty(leaf)) {
                invokers.addAll(leaf);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        List<String> urls = router.route(invokers);

        String url = loadBalance.select(urls); // router, loadbalance

        return (T) create(serviceClass, url, filter);

    }

    public static <T> T create(final Class<T> serviceClass, final String url, Filter... filters) {

        // 0. 替换动态代理 -> AOP
        // return (T) Proxy.newProxyInstance(Rpcfx.class.getClassLoader(), new Class[]{serviceClass}, new RpcfxInvocationHandler(serviceClass, url, filters));

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(serviceClass);
        enhancer.setCallback(new CgLibRpcfxInvocationHandler(serviceClass, url, filters));
        return (T) enhancer.create();

    }

    public static void updateServers() {
        // TODO
    }
}
