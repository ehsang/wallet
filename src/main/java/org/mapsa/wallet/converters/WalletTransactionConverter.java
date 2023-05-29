package org.mapsa.wallet.converters;

import org.mapsa.wallet.models.dto.WalletDto;
import org.mapsa.wallet.models.dto.WalletTransactionDto;
import org.mapsa.wallet.models.entity.WalletEntity;
import org.mapsa.wallet.models.entity.WalletTransactionEntity;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface WalletTransactionConverter {
    WalletTransactionEntity convertToEntity(WalletTransactionDto walletTransactionDto);

    WalletTransactionDto convertToDto(WalletTransactionEntity walletTransactionEntity);

    default List<WalletTransactionEntity> convertToEntity(List<WalletTransactionDto> walletTransactionDtos) {
        if (walletTransactionDtos != null) {
            return walletTransactionDtos.stream()
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

    default List<WalletTransactionDto> convertToDto(List<WalletTransactionEntity> walletTransactionEntities) {
        if (walletTransactionEntities != null) {
            return walletTransactionEntities.stream()
                    .map(this::convertToDto).collect(Collectors.toList());
        }
        return null;
    }

}
