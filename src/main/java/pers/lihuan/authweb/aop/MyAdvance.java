package pers.lihuan.authweb.aop;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 作用描述：
 *
 * @auther: Administrator
 * @date: 2018/9/10 0010 21:40
 */
public class MyAdvance {

    public boolean beforSay(){
        System.out.println("前置通知");
//        System.out.println(point.getArgs().length);
//        Object[] objects = point.getArgs();
//        Object obj = point.getTarget();
//        Object returnValue = point.proceed(objects);
//        System.out.println(returnValue); ProceedingJoinPoint point
        return true;

    }

    public void afterSay() {
        System.out.println("后置通知");
    }

}
