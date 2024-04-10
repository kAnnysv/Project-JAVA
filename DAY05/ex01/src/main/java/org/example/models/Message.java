package org.example.models;

import java.sql.Time;
import java.util.Calendar;
import java.util.Objects;

public class Message {
    private long id;
    private User author;
    private ChatRoom chatRoom;
    private String messageText;
    private Time dateTime;

    public Message(long id, User author, ChatRoom chatRoom, String messageText, Time dateTime) {
        this.id = id;
        this.author = author;
        this.chatRoom = chatRoom;
        this.messageText = messageText;
        this.dateTime = dateTime;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public ChatRoom getChatRoom() {
        return chatRoom;
    }

    public void setChatRoom(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public Time getDateTime() {
        return dateTime;
    }

    public void setDateTime(Time dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(id, message.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", author=" + author +
                ", chatRoom=" + chatRoom +
                ", messageText='" + messageText + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}
