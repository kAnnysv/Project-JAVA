package edu.scool21.chat.models.chat;

import java.time.LocalDateTime;
import java.util.Objects;

public class Message {
    private long id;
    private String messageOwner;
    private ChatRoom chatRoom;
    private String messageText;
    private Calendar dateTime;

    public Message(long id, String messageOwner, ChatRoom chatRoom, String messageText, Calendar dateTime) {
        this.id = id;
        this.messageOwner = messageOwner;
        this.chatRoom = chatRoom;
        this.messageText = messageText;
        this.dateTime = dateTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessageOwner() {
        return messageOwner;
    }

    public void setMessageOwner(String messageOwner) {
        this.messageOwner = messageOwner;
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

    public Calendar getDateTime() {
        return dateTime;
    }

    public void setDateTime(Calendar dateTime) {
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
                ", messageOwner='" + messageOwner + '\'' +
                ", chatRoom=" + chatRoom +
                ", messageText='" + messageText + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}
