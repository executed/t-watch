package com.devserbyn.twatch.controller.managment;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProfilingController implements ProfilingControllerMBean{

    private boolean enabled;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        log.info("Profiling {}", ((enabled) ? "enabled" : "disabled"));
        this.enabled = enabled;
    }
}
