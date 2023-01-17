package tn.esprit.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ChatConversation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private Timestamp dateCreation ;

    @OneToOne
    @JoinColumn(name = "sender_id")
    private User sender;

    @OneToOne
    @JoinColumn(name = "receiver_id")
    private User receiver;
    @OneToMany(mappedBy = "conversation",cascade = CascadeType.ALL)
    private List<ChatMessage> listeMessages ;

}
