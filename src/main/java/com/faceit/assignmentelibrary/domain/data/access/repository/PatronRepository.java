package com.faceit.assignmentelibrary.domain.data.access.repository;

import com.faceit.assignmentelibrary.domain.data.access.entity.Patron;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatronRepository extends JpaRepository<Patron, Long> {
}
