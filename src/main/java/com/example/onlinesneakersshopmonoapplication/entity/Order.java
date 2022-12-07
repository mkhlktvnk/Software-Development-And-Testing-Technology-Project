package com.example.onlinesneakersshopmonoapplication.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name = "_order")
@NoArgsConstructor
public class Order extends AbstractEntity {
    @Column(updatable = false)
    @CreationTimestamp
    private Timestamp creationDate;

    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
