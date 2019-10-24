package pub.ants.springdubbo.service.impl;

import pub.ants.springdubbo.service.ServiceAPI;

public class ServiceAPIImpl implements ServiceAPI {
    @Override
    public String sendMsg(String msg) {
        return "the provider send msg="+msg;
    }
}
