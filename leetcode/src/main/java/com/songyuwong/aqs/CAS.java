package com.songyuwong.aqs;

import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 * </p>
 *
 * @author SongYu
 * @since 2022/7/25
 */
public class CAS {

    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.lock();
        reentrantLock.getHoldCount();
    }

}
