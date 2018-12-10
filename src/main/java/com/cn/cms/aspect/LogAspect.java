package com.cn.cms.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;



/**
 * @Auther: Administrator
 * @Date: 2018/11/28 09:48
 * @Description:
 */
@Aspect
@Component
public class LogAspect {
    // 使用LogManager获取配置Logger
    private static Logger logger = LogManager.getLogger("fileLogger");

    @Pointcut("execution(public * com.cn.cms.*.*.*(..))")
    public void webLog(){}

    @Before("webLog()")
    public void deBefore(JoinPoint joinPoint) throws Throwable {

//        // 接收到请求，记录请求内容
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//        // 记录下请求内容
//        logger.info("URL : " + request.getRequestURL().toString());
//        logger.info("HTTP_METHOD : " + request.getMethod());
//        logger.info("IP : " + request.getRemoteAddr());
//        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
//        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));

    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
//        System.out.println("方法的返回值 : " + ret);
    }

    //后置异常通知
    @AfterThrowing("webLog()")
    public void throwss(JoinPoint joinPoint){
//        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//        String methodName = signature.getDeclaringTypeName() + "." + signature.getName();
//        logger.info(methodName+"============>>执行异常！！！！！！！！！异常信息："+signature.getExceptionTypes());
    }

    //后置最终通知,final增强，不管是抛出异常或者正常退出都会执行
    @After("webLog()")
    public void after(JoinPoint joinPoint){
//        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName()+"执行结束！");

    }

    //环绕通知,环绕增强，相当于MethodInterceptor
    @Around("webLog()")
    public Object arround(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getDeclaringTypeName() + "." + signature.getName();
        logger.info(methodName+"============>>开始！！！！！！！！！");
        long startTime = System.currentTimeMillis();
        try {
            Object o =  joinPoint.proceed();
            long endTime = System.currentTimeMillis();
            logger.info(methodName+"============>>结束！！！！！！！！！"+(endTime -startTime)+"ms");
            return o;
        } catch (Throwable e) {
            e.printStackTrace();
            logger.info(methodName+"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~>>异常！！！！！！！！！"+e.getMessage());
            return null;
        }

    }
}
