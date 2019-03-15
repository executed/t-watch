package com.devserbyn.twatch.service;

import com.devserbyn.twatch.model.EmailMessage;

import java.io.File;
import java.util.Optional;

public interface EmailService {

    void sendEmail(EmailMessage email);

    <T> Optional<File> generateAttachmentWithData(T data);
}
