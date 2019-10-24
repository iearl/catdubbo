package pub.ants.springdubbo;


import org.springframework.context.support.ClassPathXmlApplicationContext;
import pub.ants.springdubbo.service.ServiceAPI;

import java.util.Scanner;

public class ConsumerClient {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-hello-consumer.xml");
        //读取配置文件，创建IOC容器
        ctx.start();
        while(true){
            Scanner scanner = new Scanner(System.in);
            String msg = scanner.next();
            ServiceAPI serviceAPI = (ServiceAPI)ctx.getBean("serviceApi");

            System.out.println(serviceAPI.sendMsg(msg));
        }
    }
}
