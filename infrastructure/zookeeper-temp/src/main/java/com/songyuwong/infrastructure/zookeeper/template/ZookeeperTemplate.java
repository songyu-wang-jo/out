package com.songyuwong.infrastructure.zookeeper.template;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.admin.ZooKeeperAdmin;

import java.io.IOException;

/**
 * <p>
 * </p>
 *
 * @author SongYu
 * @since 2022/7/25
 */
public class ZookeeperTemplate {
    public static void main(String[] args) {
        String connectString = "local01:2181/wsy";
        try (
                ZooKeeperAdmin zooKeeperAdmin = new ZooKeeperAdmin(connectString, 2000, watchedEvent -> {
                    System.err.println(watchedEvent.getPath());
                    System.err.println(watchedEvent.getState());
                    System.err.println(watchedEvent.getWrapper());
                    System.err.println(watchedEvent.getType());
                })) {
            System.out.println(zooKeeperAdmin.getChildren("", false));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (KeeperException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

    }
}
