package com.creat.outnet.service.impl;

import com.creat.outnet.component.QueueGroup;
import com.creat.outnet.po.Message;
import com.creat.outnet.po.QueuePair;
import com.creat.outnet.po.User;
import com.creat.outnet.service.ProduceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Created by whz on 2017/10/1.
 */
@Service
public class ProduceServiceImpl implements ProduceService{

    @Autowired
    private QueueGroup queueGroup;

    @Override
    public User consume(int index) {
        User user = null;
        try {
            QueuePair queuePair = queueGroup.getQueuePairList().get(index);
            user = queuePair.inQueue.poll(10,TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return user;
    }

    @Override
    public void produce(Message message, int index) {
        try {
            QueuePair queuePair = queueGroup.getQueuePairList().get(index);
            queuePair.outQueue.put(message);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
