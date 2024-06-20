package com.faceit.assignmentelibrary.domain.data.access.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Data
public class QueuedPatronBook {
    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patron_id")
    private Patron patron;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "queued_date")
    @CreationTimestamp
    private LocalDate queuedDate;
}
