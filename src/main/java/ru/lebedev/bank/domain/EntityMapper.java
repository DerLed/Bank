package ru.lebedev.bank.domain;

public interface EntityMapper<D, E> {

    E toEntity(D dto);
    D toDTO(E entity);

}
