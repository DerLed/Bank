package ru.lebedev.bank.domain.client;

import lombok.*;
import org.hibernate.Hibernate;
import ru.lebedev.bank.domain.user.User;

import javax.persistence.*;
import java.util.Objects;

@Entity

@Setter
@ToString
@Getter
@RequiredArgsConstructor
@Table(name = "client")
public class Client{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_gen")
    @SequenceGenerator(name = "client_gen", sequenceName = "client_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToOne(
    fetch = FetchType.LAZY,
    cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Client client = (Client) o;
        return id != null && Objects.equals(id, client.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
