package com.boco.config;

import com.boco.Constant;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;

/**
 * 配置类watcher
 *
 * @author ChenLiang
 * @create 2019 01 30
 * zk.getData 监听节点的删除和数据变化事件
 */
@Slf4j
public class ConfigMonitor implements Watcher {
    private ZooKeeper zk;
    private String host;
    private int timeout;
    private ExtPropertyPlaceholderConfigurer configurer;

    public ConfigMonitor(String host, int timeout, ExtPropertyPlaceholderConfigurer configurer) {
        this.host = host;
        this.timeout = timeout;
        this.configurer = configurer;
        try {
            this.zk = new ZooKeeper(host, timeout, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        log.debug("trigger the WatcherEvent,the path[{}],the type[{}],the state[{}]",
                watchedEvent.getPath(), watchedEvent.getType(), watchedEvent.getState());
        if (watchedEvent.getType() == Event.EventType.None) {
            switch (watchedEvent.getState()) {
                case Expired:
                    reConnent();
                    break;
                case Disconnected:
                    //TODO 待查看资料，这个断开连接是否还会去请求服务器，如果最终还有一个Expired状态，那么这个状态不做任务处理，否者做和Expired状态相同的工作。
                    break;
                case SyncConnected:
                    break;
                default:
                    log.debug("The WatcherEvent state is [{}],No response required.", watchedEvent.getState());
                    break;
            }
        } else {
            switch (watchedEvent.getType()) {
                case NodeChildrenChanged:
                    log.debug("NodeChildrenChanged trigger the config map reload.");
                    getAllConfig();
                    break;
                case NodeCreated:
                case NodeDataChanged:
                    updateConfigAndWatcher(watchedEvent);
                    break;
                case NodeDeleted:
                    delConfig(watchedEvent);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * @param watchedEvent
     */
    private void updateConfigAndWatcher(WatchedEvent watchedEvent) {
        log.debug("updateConfigAndWatcher the event type [{}]", watchedEvent.getType());
        String pathKey = watchedEvent.getPath();
        try {
            String dataVal = new String(zk.getData(pathKey, this, null));
            pathKey = prefixFilter(pathKey);
            configurer.updateConfig(pathKey, dataVal);
            log.debug("the pathKey [{}],the val[{}]", pathKey, dataVal);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化zk配置类时需要获取全部配置,并创建监听
     */
    public void getAllConfig() {
        try {
            List<String> children = zk.getChildren(Constant.ROOT_SEPARATOR, this);
            for (String childrenPath : children) {
                byte[] data = zk.getData(addSeparatorPreFix(childrenPath), this, null);
                configurer.updateConfig(childrenPath, new String(data));
            }
            log.debug("configMap data is [{}]", configurer.getProperties());
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建节点，不存在则创建，存在则修改
     *
     * @param path
     * @param data
     */
    public void createNode(String path, String data) {
        path = addSeparatorPreFix(path);
        try {
            if (zk.exists(path, false) == null) {
                zk.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            } else {
                zk.setData(path, data.getBytes(), -1);
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param path
     * @return
     */
    public String getNodeData(String path) {
        path = addSeparatorPreFix(path);
        try {
            return new String(zk.getData(path, null, null));
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param watchedEvent
     */
    private void delConfig(WatchedEvent watchedEvent) {
        String delKey = prefixFilter(watchedEvent.getPath());
        log.debug("delConfig the event [{}],the deleted pathKey is[{}]",
                watchedEvent.getType(), delKey);
        configurer.removeConfig(delKey);
    }

    /**
     * 重新初始化zk
     */
    private void reConnent() {
        try {
            this.zk = new ZooKeeper(host, timeout, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 格式化前面带文件符的路径
     *
     * @param withPrefixPath
     * @return
     */
    private String prefixFilter(String withPrefixPath) {
        if (withPrefixPath.startsWith(Constant.ROOT_SEPARATOR)) {
            return withPrefixPath.substring(1);
        }
        return withPrefixPath;
    }

    /**
     * 对路径加上根节点
     *
     * @param path
     * @return
     */
    private String addSeparatorPreFix(String path) {
        if (path.startsWith(Constant.ROOT_SEPARATOR)) {
            return path;
        } else {
            return Constant.ROOT_SEPARATOR + path;
        }
    }
}
