package com.creat.outnet.service;

import com.creat.outnet.po.Message;
import com.creat.outnet.po.User;

/**
 * Created by Administrator on 2017/10/1.
 */
public interface ProduceService {
    User consume(int index);
    void produce(Message message,int index);
}
