package com.example.turborotaryphone.service;

import com.example.turborotaryphone.model.Message;
import com.example.turborotaryphone.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class MessageService {

    @Value("${upload.path}")
    private String uploadPath;

    private final MessageRepo messageRepo;

    public MessageService(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    public Iterable<Message> findAll() {
        return messageRepo.findAll();
    }

    public Iterable<Message> findByTag(String filter) {
        return messageRepo.findByTag(filter);
    }

    public void save(Message message) {
        messageRepo.save(message);
    }

    public void saveFile(Message message, MultipartFile file) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            message.setFilename(resultFilename);
        }
    }

}
