package com.example.turborotaryphone.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String text;

    private String tag;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    private String filename;

    public Message() {
    }

    public Message(String text, String tag, User user) {
        this.text = text;
        this.tag = tag;
        this.user = user;
    }

    public String getUserName() {
        return user != null ? user.getUsername() : "<none>";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(id, message.id) && Objects.equals(text, message.text) && Objects.equals(tag, message.tag) && Objects.equals(user, message.user) && Objects.equals(filename, message.filename);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, tag, user, filename);
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", tag='" + tag + '\'' +
                ", user=" + user +
                ", filename='" + filename + '\'' +
                '}';
    }

}
