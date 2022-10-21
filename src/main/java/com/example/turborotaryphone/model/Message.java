package com.example.turborotaryphone.model;

import com.example.turborotaryphone.model.util.MessageHelper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    public Message(String text, String tag, User user) {
        this.text = text;
        this.tag = tag;
        this.user = user;
    }

    public String getUserName() {
        return MessageHelper.getUserName(user);
    }

}
