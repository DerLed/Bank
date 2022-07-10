package ru.lebedev.bank.domain.helper;

public interface EntityMapper<D, E> {

    E toEntity(D dto);
    D toDTO(E entity);

}
