package com.creat.outnet.controller;

import com.creat.outnet.po.Message;
import com.creat.outnet.component.QueueGroup;
import com.creat.outnet.po.QueuePair;
import com.creat.outnet.po.User;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by whz on 2017/10/1.
 */
@RestController
@RequestMapping("/netout")
public class OutNetController {

    @Autowired
    private QueueGroup queueGroup;

    private Integer index = new Integer("0");

    private int getIndex(){
        synchronized (index){
            int result = index;
            if(index == 2){
                index = 0;
            }else {
                index++;
            }
            return result;
        }
    }

    @RequestMapping(value = "/login",method = {RequestMethod.POST},
                    produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public String login(User user, HttpServletRequest request){
        String error = null;
        if((error = verify(user)) != null){
            return error;
        }
        String sessionId = request.getSession().getId();
        user.setSessionId(sessionId);
        Message result = null;
        try {
            QueuePair queuePair = queueGroup.getQueuePairList().get(getIndex()%3);
            queuePair.inQueue.put(user);
            result = queuePair.outQueue.take();
            System.out.println(result.getSessionId());
            while(!result.getSessionId().equals(sessionId)){
                queuePair.outQueue.put(result);
                result = queuePair.outQueue.take();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return result.getMsg();
    }

    private String verify(User user){
        if(user.getUserName() == null || user.getUserName().length() != 8
                || user.getUserPassword() == null
                || user.getUserPassword().isEmpty()){
            Map<String, Object> errors = new HashMap<>();
            errors.put("Obj", 999);
            errors.put("IsSucceed", false);
            errors.put("Msg", "参数错误!");
            return JSONObject.fromObject(errors).toString();
        }
        return null;
    }

}
