package com.devserbyn.twatch.annotation.processor;

import com.devserbyn.twatch.annotation.Profiled;
import com.devserbyn.twatch.controller.managment.ProfilingController;
import com.devserbyn.twatch.utility.annotation.ProfiledAnnotationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class ProfiledAnnotationBeanPostProcessor implements BeanPostProcessor {

    private final ProfiledAnnotationUtil util;
    private final ProfilingController profilingController;

    private Map<String, Class> map = new HashMap<>();

    @Autowired
    public ProfiledAnnotationBeanPostProcessor(ProfiledAnnotationUtil util,
                                               ProfilingController controller) throws Exception {
        this.util = util;
        this.profilingController = controller;
        util.registerMBean(profilingController);
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        // Collecting classes with annotation present
        Class<?> beanClass = bean.getClass();
        if (beanClass.isAnnotationPresent(Profiled.class)) {
            map.put(beanName, beanClass);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        Class beanClass = map.get(beanName);
        if (beanClass != null) {
            return Proxy.newProxyInstance
                    (beanClass.getClassLoader(), beanClass.getInterfaces(),
                     (proxy, method, args) -> {
                        if (profilingController.isEnabled()) {
                            return util.profileMethod(bean, method, args);
                        } else {
                            return method.invoke(bean, args);
                        }
                    });
        }
        return bean;
    }
}
