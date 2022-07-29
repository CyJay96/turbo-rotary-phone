package com.example.turborotaryphone.service;

import com.example.turborotaryphone.model.Message;
import com.example.turborotaryphone.repos.MessageRepo;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

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

}
