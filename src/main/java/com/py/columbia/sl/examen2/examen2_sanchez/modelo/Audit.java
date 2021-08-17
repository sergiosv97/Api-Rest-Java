
package com.py.columbia.sl.examen2.examen2_sanchez.modelo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 *
 * @author Usuario
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
            value = {"createdAt", "updateAt"},
        allowGetters = true
)        
public abstract class Audit implements Serializable {
    @Temporal (TemporalType.TIMESTAMP)
    @Column(name = "created_at",nullable =false, updatable = false)
    @CreatedDate
    private Date createdAt;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at",nullable = false)
    @LastModifiedDate
    private Date updatedAt;
}
