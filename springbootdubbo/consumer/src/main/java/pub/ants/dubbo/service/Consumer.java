package pub.ants.dubbo.service;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @Reference(url = "dubbo://localhost:20880")
    ServiceAPI serviceAPI;

    public void sendMsg(String msg){
        System.out.println(serviceAPI.sendMsg(msg));
    }
}
