import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;

/**
 * @program: test
 * @description: 学习zookeeper curator http://curator.apache.org/getting-started.html
 * @author: Mr.WG
 * @create: 2019-04-18 17:21
 **/
public class zookeeperCURDDemo {
    private static final String ZK_ADDRESS = "192.168.1.100:2181";
    private static final String ZK_PATH = "/zktest";

    public static void main(String[] args) throws Exception {
        //1.connected
        CuratorFramework client = CuratorFrameworkFactory.newClient(
                ZK_ADDRESS,
                new RetryNTimes(10, 5000)
        );
        client.start();

        System.out.println("zk client start successfully!");

        //2.created
        String data = "hello";
        client.create().
                creatingParentsIfNeeded().
                forPath(ZK_PATH, data.getBytes());

        //3.modify
        client.setData().forPath(ZK_PATH, data.getBytes());

        //4.remove
        client.delete().forPath(ZK_PATH);
    }
}
