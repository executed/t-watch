package com.devserbyn.twatch.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.File;

@Getter
@Setter
@Builder
public class EmailMessage {

    private String target;

    private String title;

    private String content;

    private File attachment;

    private String comment;

    @Override
    public String toString() {
        return "EmailMessage{" +
                "target='" + target + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", comment='" + comment + '\'' +
                ((attachment != null) ? ", attachment='true'" : "") +
                '}';
    }
}
