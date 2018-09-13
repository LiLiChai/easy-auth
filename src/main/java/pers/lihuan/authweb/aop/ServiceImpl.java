package pers.lihuan.authweb.aop;

/**
 * 作用描述：
 *
 * @auther: Administrator
 * @date: 2018/9/10 0010 21:39
 */
public class ServiceImpl {

    public String doSomething(String str) {
        System.out.println("具体实现" + str);
        return "result";
    }

}
