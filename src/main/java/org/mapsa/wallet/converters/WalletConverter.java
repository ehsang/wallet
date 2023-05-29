package org.mapsa.wallet.converters;

import org.mapsa.wallet.models.dto.WalletDto;
import org.mapsa.wallet.models.entity.WalletEntity;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface WalletConverter {
    WalletEntity convertToEntity(WalletDto walletDto);

    WalletDto convertToDto(WalletEntity walletEntity);

    default List<WalletEntity> convertToEntity(List<WalletDto> walletDtos) {
        if (walletDtos != null) {
            return walletDtos.stream()
                    .map(i -> {
                        try {
                            return convertToEntity(i);
                        } catch (Exception x) {
                            return null;
                        }
                    }).collect(Collectors.toList());
        }
        return null;
    }

    default List<WalletDto> convertToDto (List<WalletEntity> walletEntities){
        if (walletEntities != null){
            return walletEntities.stream()
                    .map(this::convertToDto).collect(Collectors.toList());
        }
        return null;
    }


}
