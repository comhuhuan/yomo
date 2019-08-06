package yomo.study.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * <p>Title:ConsistentHashing
 * <p>Description:一致性hash算法
 * https://blog.csdn.net/suifeng629/article/details/81567777  
 * 
 * <p>Modified History:
 * 一致性哈希算法解决了普通余数Hash算法伸缩性差的问题，可以保证在上线、下线服务器的情况下尽量有多的请求命中原来路由到的服务器。
 *
 * @author HH
 * @date 2019/8/6 11:50
 */
public class ConsistentHashing {


    /**
     * 普通余数Hash算法
     * <p>
     * m = hash(o) mod n ——式子1
     * <p>
     * 增加   m = hash(o) mod (n + 1) ——式子2
     * 减小  m = hash(o) mod (n - 1) ——式子3
     * <p>
     *     O(1) < O(log2N) < O(N) < O(N * log2N) < O(N2) < O(N3)  < O(N!)
     *
     * <p>
     * 我们以机器扩容的情况为例，说明简单的取模方法会导致什么问题。假设机器由3台变成4台，对象o1由式子1计算得到的m值为2，
     * 由式子2计算得到的m值却可能为0，1，2，3（一个 3t + 2的整数对4取模，其值可能为0，1，2，3，读者可以自行验证），
     * 大约有75%（3/4)的可能性出现缓存访问不命中的现象。随着机器集群规模的扩大，这个比例线性上升。当99台机器再加入1台机器时，
     * 不命中的概率是99%（99/100）。这样的结果显然是不能接受的，因为这会导致数据库访问的压力陡增，严重情况，还可能导致数据库宕机。
     * <p>
     * 来了一个 key，首先计算 hash 值，然后对节点数取模。然后打在不同的 master 节点上。一旦某一个 master 节点宕机，所有请求过来，
     * 都会基于最新的剩余 master 节点数去取模，尝试去取数据。这会导致大部分的请求过来，全部无法拿到有效的缓存，导致大量的流量涌入数据库。
     */


    static class ConsistentHashingWithoutVirtualNode {

        //待添加入Hash环的服务器列表
        private static String[] servers = {"192.168.0.0:111", "192.168.0.1:111",
                "192.168.0.2:111", "192.168.0.3:111", "192.168.0.4:111"};

        //key表示服务器的hash值，value表示服务器
        // TODO: 2019/8/6 学习了解treeMap HH
        private static SortedMap<Integer, String> sortedMap = new TreeMap<Integer, String>();

        //程序初始化，将所有的服务器放入sortedMap中
        static {
            for (int i = 0; i < servers.length; i++) {
                int hash = getHash(servers[i]);
                System.out.println("[" + servers[i] + "]加入集合中, 其Hash值为" + hash);
                sortedMap.put(hash, servers[i]);
            }
            System.out.println();
        }

        //得到应当路由到的结点
        private static String getServer(String key) {
            //得到该key的hash值
            int hash = getHash(key);
            //得到大于该Hash值的所有Map
            SortedMap<Integer, String> subMap = sortedMap.tailMap(hash);
            if (subMap.isEmpty()) {
                //如果没有比该key的hash值大的，则从第一个node开始
                Integer i = sortedMap.firstKey();
                //返回对应的服务器
                return sortedMap.get(i);
            } else {
                //第一个Key就是顺时针过去离node最近的那个结点
                Integer i = subMap.firstKey();
                //返回对应的服务器
                return subMap.get(i);
            }
        }

        //使用FNV1_32_HASH算法计算服务器的Hash值,这里不使用重写hashCode的方法，最终效果没区别
        private static int getHash(String str) {
            final int p = 16777619;
            int hash = (int) 2166136261L;
            for (int i = 0; i < str.length(); i++)
                hash = (hash ^ str.charAt(i)) * p;
            hash += hash << 13;
            hash ^= hash >> 7;
            hash += hash << 3;
            hash ^= hash >> 17;
            hash += hash << 5;

            // 如果算出来的值为负数则取其绝对值
            if (hash < 0)
                hash = Math.abs(hash);
            return hash;
        }

        public static void main(String[] args) {
            String[] keys = {"太阳", "月亮", "星星"};
            for (int i = 0; i < keys.length; i++)
                System.out.println("[" + keys[i] + "]的hash值为" + getHash(keys[i])
                        + ", 被路由到结点[" + getServer(keys[i]) + "]");
        }
    }




    static  class ConsistentHashingWithVirtualNode {

        //待添加入Hash环的服务器列表
        private static String[] servers = {"192.168.0.0:111", "192.168.0.1:111", "192.168.0.2:111",
                "192.168.0.3:111", "192.168.0.4:111"};

        //真实结点列表,考虑到服务器上线、下线的场景，即添加、删除的场景会比较频繁，这里使用LinkedList会更好
        private static List<String> realNodes = new LinkedList<String>();

        //虚拟节点，key表示虚拟节点的hash值，value表示虚拟节点的名称
        private static SortedMap<Integer, String> virtualNodes = new TreeMap<Integer, String>();

        //虚拟节点的数目，这里写死，为了演示需要，一个真实结点对应5个虚拟节点
        private static final int VIRTUAL_NODES = 5;

        static {
            //先把原始的服务器添加到真实结点列表中
            for (int i = 0; i < servers.length; i++)
            //                 realNodes.add(servers[i]);
            //
            //             //再添加虚拟节点，遍历LinkedList使用foreach循环效率会比较高
            for (String str : realNodes) {
                for (int j = 0; j < VIRTUAL_NODES; j++) {
                    String virtualNodeName = str + "&&VN" + String.valueOf(j);
                    int hash = getHash(virtualNodeName);
                    System.out.println("虚拟节点[" + virtualNodeName + "]被添加, hash值为" + hash);
                    virtualNodes.put(hash, virtualNodeName);
                }
            }
            System.out.println();
        }

        //使用FNV1_32_HASH算法计算服务器的Hash值,这里不使用重写hashCode的方法，最终效果没区别
        private static int getHash(String str) {
            final int p = 16777619;
            int hash = (int) 2166136261L;
            for (int i = 0; i < str.length(); i++)
                hash = (hash ^ str.charAt(i)) * p;
            hash += hash << 13;
            hash ^= hash >> 7;
            hash += hash << 3;
            hash ^= hash >> 17;
            hash += hash << 5;

            // 如果算出来的值为负数则取其绝对值
            if (hash < 0)
                hash = Math.abs(hash);
            return hash;
        }

        //得到应当路由到的结点
        private static String getServer(String key) {
            //得到该key的hash值
            int hash = getHash(key);
            // 得到大于该Hash值的所有Map
            SortedMap<Integer, String> subMap = virtualNodes.tailMap(hash);
            String virtualNode;
            if (subMap.isEmpty()) {
                //如果没有比该key的hash值大的，则从第一个node开始
                Integer i = virtualNodes.firstKey();
                //返回对应的服务器
                virtualNode = virtualNodes.get(i);
            } else {
                //第一个Key就是顺时针过去离node最近的那个结点
                Integer i = subMap.firstKey();
                //返回对应的服务器
                virtualNode = subMap.get(i);
            }
            //virtualNode虚拟节点名称要截取一下
            if (virtualNode!=null&& !"".equals(virtualNode)) {
                return virtualNode.substring(0, virtualNode.indexOf("&&"));
            }
            return null;
        }

        public static void main(String[] args) {
            String[] keys = {"太阳", "月亮", "星星"};
            for (int i = 0; i < keys.length; i++)
                System.out.println("[" + keys[i] + "]的hash值为" +
                        getHash(keys[i]) + ", 被路由到结点[" + getServer(keys[i]) + "]");
        }
    }
    }