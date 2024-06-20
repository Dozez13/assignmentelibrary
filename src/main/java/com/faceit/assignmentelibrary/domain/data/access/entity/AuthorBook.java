package com.faceit.assignmentelibrary.domain.data.access.entity;

import com.faceit.assignmentelibrary.domain.data.access.entity.embeddable.AuthorBookID;
import com.faceit.assignmentelibrary.domain.data.access.entity.enums.AuthorRole;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class AuthorBook {

    @EmbeddedId
    private AuthorBookID authorBookID;

    @Enumerated(EnumType.STRING)
    @Column(name = "author_role")
    private AuthorRole authorRole;
}
