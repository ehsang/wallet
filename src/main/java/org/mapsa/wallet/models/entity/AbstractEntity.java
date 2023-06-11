package org.mapsa.wallet.models.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;

//The @MappedSuperclass annotation is a JPA (Java Persistence API) annotation that is used to mark a class as a superclass for entity classes. When applied at the class level, the @MappedSuperclass annotation indicates that
// the annotated class is not an entity itself but provides common mappings or fields that can be inherited by its subclasses.
//The @MappedSuperclass annotation is specifically designed for use with entity classes in the JPA (Java Persistence API) context.
// It cannot be used on other layers such as services or controllers.
@MappedSuperclass
@Getter
@Setter
//The @EntityListeners annotation is used in JPA (Java Persistence API) to specify one or more entity listeners for an entity class.
// An entity listener is a class that contains callback methods that are executed in response to specific lifecycle events of an entity, such as before persisting or updating an entity.
//In this example, the AbstractEntity class is annotated with @EntityListeners(AuditingEntityListener.class) at the @MappedSuperclass level.
// This ensures that any entity inheriting from BaseEntity will have the AuditingEntityListener associated by default.
@EntityListeners(AuditingEntityListener.class)
public class AbstractEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    //The @Version annotation is used in JPA (Java Persistence API) to mark a field as the optimistic locking version attribute of an entity.
    //It is typically used to manage concurrent access and prevent conflicting updates in a multi-user environment.
    //By using @Version, you can implement optimistic locking in your application to handle concurrent updates and ensure data consistency.
    // It helps prevent conflicting modifications and provides a mechanism for detecting and resolving concurrent access issues.
    //When an anomaly is detected during the optimistic locking check on an entity field annotated with @Version,
    // the behavior of how the changes are rolled back depends on the specific implementation and configuration of your application.
    @Version
    private Long version;

    // related to @EntityListeners(AuditingEntityListener.class)
    @CreatedDate
    private Date insertTimeStamp;

    // related to @EntityListeners(AuditingEntityListener.class)
    @LastModifiedDate
    private Date lastUpdatedTimeStamp;


}
