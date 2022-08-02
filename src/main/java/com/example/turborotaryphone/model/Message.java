package com.example.turborotaryphone.model;

import com.example.turborotaryphone.model.util.MessageHelper;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
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

    @ManyToMany
    @JoinTable(name = "message_likes",
            joinColumns = { @JoinColumn(name = "message_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") })
    private Set<User> likes = new HashSet<>();

    public Message() {
    }

    public Message(String text, String tag, User user) {
        this.text = text;
        this.tag = tag;
        this.user = user;
    }

    public String getUserName() {
        return MessageHelper.getUserName(user);
    }

}
