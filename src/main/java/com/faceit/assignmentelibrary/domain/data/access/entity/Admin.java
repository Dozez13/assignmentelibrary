package com.faceit.assignmentelibrary.domain.data.access.entity;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
public class Admin extends User {
}
