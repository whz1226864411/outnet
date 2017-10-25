package com.creat.outnet.component;

import com.creat.outnet.po.Message;
import com.creat.outnet.po.QueuePair;
import com.creat.outnet.po.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by whz on 2017/10/1.
 */
@Component
@Scope("singleton")
public class QueueGroup {

    private static final List<QueuePair> queuePairList = new ArrayList<>();
    private static int groupSize = 0;

    public QueueGroup(){
        for(int i = 0; i < 3; i++){
            getQueuePairList().add(new QueuePair());
        }
    }
    public List<QueuePair> getQueuePairList() {
        return queuePairList;
    }

    public synchronized int getGroupSize(){
        return this.getGroupSize();
    }

    public synchronized void addQueuePair(QueuePair queuePair){
        this.getQueuePairList().add(queuePair);
        groupSize++;
    }

}
