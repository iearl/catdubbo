package pub.ants.springdubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProviderClient {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-hello-provider.xml");
        //读取配置文件，创建IOC容器
        ctx.start();

        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
