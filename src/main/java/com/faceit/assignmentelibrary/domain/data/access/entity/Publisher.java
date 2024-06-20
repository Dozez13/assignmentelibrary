package com.faceit.assignmentelibrary.domain.data.access.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "publisher", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookDetails> bookDetailsList;

    @Column(name = "title")
    private String title;
}
