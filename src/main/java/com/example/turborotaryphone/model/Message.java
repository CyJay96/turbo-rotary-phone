package com.example.turborotaryphone.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Please, fill the message")
    @Length(max = 2048, message = "Message too long (more than 2kB)")
    private String text;

    @Length(max = 255, message = "Message too long (more than 2kB)")
    private String tag;

    private String filename;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    public Message() {
    }

    public Message(String text, String tag, User user) {
        this.text = text;
        this.tag = tag;
        this.user = user;
    }

    public String getUserName() {
        return user != null ? user.getUsername() : "The author is unknown(";
    }

}
