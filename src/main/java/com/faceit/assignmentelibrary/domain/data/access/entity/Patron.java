package com.faceit.assignmentelibrary.domain.data.access.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
public class Patron extends User {

    @OneToMany(mappedBy = "patronBookID.patron", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PatronBook> patronBooks;
}
