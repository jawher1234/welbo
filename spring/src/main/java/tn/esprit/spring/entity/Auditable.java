package tn.esprit.spring.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

@MappedSuperclass
@Data
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable<U> {

    @CreatedBy
    @ManyToOne
    private User users;
    @CreatedDate
    @Temporal(TIMESTAMP)
    protected Date createdDate;
    @LastModifiedDate
    @Temporal(TIMESTAMP)
    protected Date lastModifiedDate;

}
