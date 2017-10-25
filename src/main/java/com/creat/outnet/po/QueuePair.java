package com.creat.outnet.po;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Administrator on 2017/10/2.
 */
public class QueuePair {
    public BlockingQueue<User> inQueue = new LinkedBlockingQueue<>();
    public BlockingQueue<Message> outQueue = new LinkedBlockingQueue<>();
}
