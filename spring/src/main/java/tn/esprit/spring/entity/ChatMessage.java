package tn.esprit.spring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Comparator;


@Entity
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private Timestamp dateCreation ;

    private String contenu ;

    private boolean isSeen ;


    @OneToOne(cascade = CascadeType.ALL)
    private User user ;

    @JsonIgnore
    @ManyToOne
    private ChatConversation conversation ;

    public static class DateCreationComparator implements Comparator<ChatMessage>
    {
        //    @Override
        public int compare(ChatMessage m1, ChatMessage m2) {
            return m1.getDateCreation().compareTo(m2.getDateCreation());
        }


    }

    public ChatMessage(Long id, Timestamp dateCreation, String contenu, boolean isSeen, User user, ChatConversation conversation) {
        this.id = id;
        this.dateCreation = dateCreation;
        this.contenu = contenu;
        this.isSeen = isSeen;
        this.user = user;
        this.conversation = conversation;
    }

    public ChatMessage() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Timestamp dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public boolean isSeen() {
        return isSeen;
    }

    public void setSeen(boolean seen) {
        isSeen = seen;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ChatConversation getConversation() {
        return conversation;
    }

    public void setConversation(ChatConversation conversation) {
        this.conversation = conversation;
    }
}
