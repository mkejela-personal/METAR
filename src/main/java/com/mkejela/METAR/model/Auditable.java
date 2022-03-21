package com.mkejela.METAR.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable {

    @Column(name = "CREATED_BY")
    @CreatedBy
    public String createdBy;

    @Column(name = "CREATED_DATE")
    @CreatedDate
    public LocalDateTime createdDate;

    @Column(name = "UPDATED_BY")
    @LastModifiedBy
    public String updatedBy;

    @Column(name = "UPDATED_DATE")
    @LastModifiedDate
    public LocalDateTime updatedDate;
}
