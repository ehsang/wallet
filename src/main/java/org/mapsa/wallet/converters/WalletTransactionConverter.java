package org.mapsa.wallet.converters;

import org.mapsa.wallet.models.dto.WalletDto;
import org.mapsa.wallet.models.dto.WalletTransactionDto;
import org.mapsa.wallet.models.entity.WalletEntity;
import org.mapsa.wallet.models.entity.WalletTransactionEntity;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface WalletTransactionConverter extends BaseConverter<WalletTransactionEntity, WalletTransactionDto> {

}
