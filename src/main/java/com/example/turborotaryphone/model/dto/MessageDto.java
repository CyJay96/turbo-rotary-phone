package com.example.turborotaryphone.model.dto;

import com.example.turborotaryphone.model.Message;
import com.example.turborotaryphone.model.User;
import com.example.turborotaryphone.model.util.MessageHelper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {

    private Long id;

    private String text;

    private String tag;

    private String filename;

    private User user;

    private Long likes;

    private Boolean meLiked;

    public MessageDto(Message message, Long likes, Boolean meLiked) {
        this.id = message.getId();
        this.text = message.getText();
        this.tag = message.getTag();
        this.filename = message.getFilename();
        this.user = message.getUser();
        this.likes = likes;
        this.meLiked = meLiked;
    }

    public String getUserName() {
        return MessageHelper.getUserName(user);
    }

}
