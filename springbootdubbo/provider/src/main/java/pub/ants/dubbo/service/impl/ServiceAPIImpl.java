package pub.ants.dubbo.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;
import pub.ants.dubbo.service.ServiceAPI;

@Component
@Service(interfaceClass = ServiceAPI.class)
public class ServiceAPIImpl implements ServiceAPI {

    @Override
    public String sendMsg(String msg) {
        return "spring boot provider msg:"+msg;
    }
}
