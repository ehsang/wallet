package org.mapsa.wallet.converters;

import org.mapsa.wallet.models.dto.WalletDto;
import org.mapsa.wallet.models.entity.WalletEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WalletConverter extends BaseConverter<WalletEntity, WalletDto> {

}
