package edu.scool21.chat.models.chat;

import java.util.List;
import java.util.Objects;

public class ChatRoom {
    private long id;
    private String chatName;
    private String chatOwner;
    private List<Message> messageList;

    public ChatRoom(long id, String chatName, String chatOwner, List<Message> messageList) {
        this.id = id;
        this.chatName = chatName;
        this.chatOwner = chatOwner;
        this.messageList = messageList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    public String getChatOwner() {
        return chatOwner;
    }

    public void setChatOwner(String chatOwner) {
        this.chatOwner = chatOwner;
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        ChatRoom chatRoom = (ChatRoom) o;
        return Objects.equals(id, chatRoom.id);
    }
    @Override
    public int hashCode(){
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ChatRoom{" +
                "id=" + id +
                ", chatName='" + chatName + '\'' +
                ", chatOwner='" + chatOwner + '\'' +
                ", messageList=" + messageList +
                '}';
    }
}
