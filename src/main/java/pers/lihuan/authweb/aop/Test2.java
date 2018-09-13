package pers.lihuan.authweb.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 作用描述：
 *
 * @auther: Administrator
 * @date: 2018/9/10 0010 21:49
 */
public class Test2 {
    //https://www.cnblogs.com/niceyoo/p/8732891.html
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:/spring/test.xml");
        ServiceImpl br = (ServiceImpl) ac.getBean("service");
        br.doSomething("hello aop");
    }

}
