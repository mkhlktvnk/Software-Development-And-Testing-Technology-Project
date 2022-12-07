package com.example.onlinesneakersshopmonoapplication.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table
@NoArgsConstructor
public class Product extends AbstractEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Integer size;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SeasonType seasonType;

    @ManyToOne(fetch = FetchType.LAZY)
    private Brand brand;
}
