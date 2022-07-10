package ru.lebedev.bank.domain.statistics;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Table(name = "transaction_statistics")
@EntityListeners(AuditingEntityListener.class)
@Builder
@AllArgsConstructor
@Getter
@Setter
public class TransactionStatistics {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_statistics_gen")
    @SequenceGenerator(name = "transaction_statistics_gen", sequenceName = "transaction_statistics_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "date")
    @CreatedDate
    private LocalDateTime date;

    @Column(name = "type")
    @Enumerated(value = EnumType.STRING)
    private TypeOperation type;

}
