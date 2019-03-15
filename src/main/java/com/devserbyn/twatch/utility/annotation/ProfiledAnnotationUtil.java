package com.devserbyn.twatch.utility.annotation;

import com.devserbyn.twatch.controller.managment.ProfilingController;

import java.lang.reflect.Method;

public interface ProfiledAnnotationUtil {

    void registerMBean(ProfilingController controller) throws Exception;

    Object profileMethod(Object bean, Method method, Object[] args);
}
