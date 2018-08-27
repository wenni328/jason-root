package com.jason.config;

import com.alibaba.fastjson.JSONObject;
import com.jason.common.HttpContextUtils;
import com.jason.common.IPUtils;
import com.jason.common.aop.LogApi;
import com.jason.entity.Log;
import com.jason.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 切面处理
 *
 * @author xy
 */
@Aspect
@Configuration
@Slf4j
public class LogAspectConfig {

    @Autowired
    private LogService logService;

    @Pointcut("@annotation(com.jason.common.aop.LogApi)")
    public void logPointCut() {

    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        // 执行方法
        Object result = point.proceed();
        // 执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        // 保存日志
        saveLog(point, time);
        return result;
    }

    @AfterReturning(returning = "rvt", pointcut = "@annotation(com.jason.common.aop.LogApi)")
    public Object AfterExec(JoinPoint joinPoint, Object rvt) {
        //pointcut是对应的注解类   rvt就是方法运行完之后要返回的值
        System.out.println("AfterReturning增强：获取目标方法的返回值：" + rvt);
        log.info("请求方法：{}，返回值：{}", joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()",
                JSONObject.toJSON(rvt));
        return rvt;
    }

    private void saveLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Log a = new Log();
        LogApi logValue = method.getAnnotation(LogApi.class);
        if (logValue != null) {
            // 注解上的描述
            a.setOperation(logValue.value());
        }
        // 请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        a.setMethod(className + "." + methodName + "()");
        // 请求的参数
        Object[] args = joinPoint.getArgs();
        try {
            String params = JSONObject.toJSONString(args);
            a.setParams(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 获取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        // 设置IP地址
        a.setIp(IPUtils.getIpAddr(request));

        // 用户名
        // String username = ((SysUserEntity)
        // SecurityUtils.getSubject().getPrincipal()).getUsername();
        a.setUsername("admin");

        a.setTime(time);
        a.setCreateDate(new Date());
        log.info("请求路径：{}，请求参数：{}", className + "." + methodName + "()", JSONObject.toJSONString(args));
        // 保存系统日志
        logService.insert(a);
    }
}
