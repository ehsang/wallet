package org.mapsa.wallet.converters;

import java.util.List;
import java.util.stream.Collectors;

public interface BaseConverter<E, D> {
    E convertToEntity(D d);

    D convertToDto(E e);

    default List<E> convertToEntity(List<D> dtoList) {
        if (dtoList != null) {
            return dtoList.stream()
                    .map(i -> {
                                try {
                                    return convertToEntity(i);
                                } catch (Exception ex) {
                                    return null;
                                }
                            }
                    ).collect(Collectors.toList());
        }
        return null;
    }

    default List<D> convertToDto(List<E> eList) {
        if (eList != null) {
            eList.stream()
                    .map(this::convertToDto).collect(Collectors.toList());
        }
        return null;
    }

}
