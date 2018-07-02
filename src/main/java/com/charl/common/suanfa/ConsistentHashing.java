package com.charl.common.suanfa;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Arrays;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @program: common
 * @description: 一致性hash:https://coderxing.gitbooks.io/architecture-evolution/di-san-pian-ff1a-bu-luo/631-yi-zhi-xing-ha-xi.html
 * @author: charl
 * @create: 2018-07-02 15:42
 **/
public class ConsistentHashing {

    //后端实例地址列表
    private static final List<String> hosts = Arrays.asList("192.168.0.1:120001", "192.168.0.2:120001", "192.168.0.3:120001");

    //虚拟节点
    private final SortedMap<Long, String> nodes = new TreeMap<Long, String>();

    //虚拟节点个数
    private final int VIRTUAL_NODE_COUNT = 3;

    /**
     * 初始化节点
     */
    public ConsistentHashing() {
        for (String host : hosts) {
            addNode(host);
        }
    }

    /**
     * 求hash
     * @param key
     * @return
     */
    private Long hash(String key) {
        //返回32位的md5编码值
        String md5key = DigestUtils.md5Hex(key);
        //截取md5编码的前15位，转换为10进制Long，然后跟2的23方取模
        return Long.parseLong(md5key.substring(0, 15), 16) % ((long) Math.pow(2, 32));
    }

    /**
     * 为每个实例创建{@code VIRTUAL_NODE_COUNT} 个节点
     * @param nodeKey
     */
    private void addNode(String nodeKey) {
        for (int i = 0; i < VIRTUAL_NODE_COUNT; i++) {
            Long hash = this.hash(nodeKey + i);
            nodes.put(hash, nodeKey);
        }
    }

    /**
     * 根据key算出hash,并顺时针取最靠近的值
     * @param key
     * @return
     */
    public String getServerAddr(String key) {
        Long hashCode = hash(key);
        SortedMap<Long, String> resultMap = nodes.tailMap(hashCode);
        Long keyId = resultMap.isEmpty() ? nodes.firstKey() : resultMap.firstKey();
        return nodes.get(keyId);
    }

    /**
     * 新增服务器
     * @param server
     */
    public void addServer(String server) {
        hosts.add(server);
        this.addNode(server);
    }

    public static void main(String[] args) {
        ConsistentHashing consistentHashing = new ConsistentHashing();
        String hello = consistentHashing.getServerAddr("kongkoong");
        System.out.println(hello);

        /**
         * guava工具类中也有一个一致性hash的实现
         */
        HashCode hashCode = Hashing.murmur3_32().newHasher().putString("youknow", Charsets.UTF_8).hash();
        /**
         *通过一致性哈希方式计算，缓存key对应的服务器主机是那一台，bucket 的范围在 0 ~ cacheServers.length -1
         *0：第一台实例
         *1：第二台实例
         *2：第三台实例
         */
        int bucket = Hashing.consistentHash(hashCode, hosts.size());
        System.out.println(bucket);
    }

}
