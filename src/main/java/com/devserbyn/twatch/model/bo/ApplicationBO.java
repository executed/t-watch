package com.devserbyn.twatch.model.bo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class ApplicationBO {

    private boolean developmentMode = false;
}
