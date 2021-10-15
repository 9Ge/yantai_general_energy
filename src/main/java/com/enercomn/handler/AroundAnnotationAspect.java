package com.enercomn.handler;

import com.enercomn.web.bean.TbEnergyRequestLog;
import com.enercomn.exception.RequestException;
import com.enercomn.web.mapper.TbEnergyRequestLogMapper;
import com.enercomn.util.ObjectMapperUtil;
import com.enercomn.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Enercomn
 */
@Slf4j
@Aspect
@Component
public class AroundAnnotationAspect {

    @Autowired
    private TbEnergyRequestLogMapper tbEnergyRequestLogMapper;

    @Pointcut(value = "@annotation(com.enercomn.handler.anno.RequestLog)")
    public void operationLog() {
    }

    //统计请求的处理时间
    ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Before("operationLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        startTime.set(System.currentTimeMillis());
    }

    /**
     * 后置通知(在方法执行之后并返回数据) 用于拦截Controller层无异常的操作
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "operationLog()", returning = "result")
    public void after(JoinPoint joinPoint, Object result) {
        createLog(joinPoint, null, ObjectMapperUtil.writeValueAsString(result));
    }

    @AfterThrowing(pointcut = "operationLog()", throwing = "e")
    public void saveExceptionLog(JoinPoint joinPoint, Throwable e) {
        try {
            createLog(joinPoint, e.getMessage(), null);
        } catch (RequestException ex) {
            log.error("请求接口失败");
        }
    }

    public void createLog(JoinPoint joinPoint, String errorMessage, String responseInfo) throws RequestException {
        TbEnergyRequestLog log = new TbEnergyRequestLog();
        log.setId(StringUtils.getUUID());
        log.setRequestTime(new Date());
        log.setRequestInfoDecr(ObjectMapperUtil.writeValueAsString(joinPoint.getArgs()[0]));
        log.setRequestInfo(ObjectMapperUtil.writeValueAsString(joinPoint.getArgs()[1]));
        log.setRequestLongTime(System.currentTimeMillis() - startTime.get());
        log.setRequestUrl(ObjectMapperUtil.writeValueAsString(joinPoint.getArgs()[2]));
        log.setResponseInfo(responseInfo);
        if (StringUtils.isEmpty(errorMessage)) {
            log.setResultFlag("请求成功");
        }else{
            log.setFailedMessage(errorMessage);
            log.setResultFlag("请求失败");
        }
        tbEnergyRequestLogMapper.insert(log);
    }
}