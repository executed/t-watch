package com.devserbyn.twatch.utility.annotation;

import com.devserbyn.twatch.annotation.Profiled;
import com.devserbyn.twatch.controller.managment.ProfilingController;
import com.devserbyn.twatch.utility.PropertyUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.management.*;
import java.lang.management.ManagementFactory;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProfiledAnnotationUtilImpl implements ProfiledAnnotationUtil{

    private final PropertyUtil propertyUtil;

    @Override
    public void registerMBean(ProfilingController controller) {
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        try {
            mBeanServer.registerMBean(controller, propertyUtil.getJmxObjectName(Profiled.class));
        } catch (Exception e) {
            log.error("Something went wrong while registering JMX ProfilingController");
        }
    }

    @Override
    public Object profileMethod(Object bean, Method method, Object[] args) {
        Object returnValue = null;
        try {
            String methodFullName = String.format("%s.%s()", bean.getClass().getSimpleName(),
                                                  method.getName());
            log.debug("Profiling of method {} started", methodFullName);

            returnValue = profileMethodExecutionTime(bean, method, args);
            // Here can be another profiling methods

            log.debug("Profiling of method {} succeeded", methodFullName);
        } catch (Exception e) {
            log.error("Something went wrong while profiling", e);
        }
        return returnValue;
    }

    private Object profileMethodExecutionTime(Object bean, Method method, Object[] args) throws Exception {
        long beforeTime = System.currentTimeMillis();
        Object returnValue = method.invoke(bean, args);
        long afterTime = (System.currentTimeMillis() - beforeTime);
        double executionTime = TimeUnit.NANOSECONDS.toSeconds(afterTime);
        log.debug("Method executionTime: {} seconds", executionTime);
        return returnValue;
    }
}
