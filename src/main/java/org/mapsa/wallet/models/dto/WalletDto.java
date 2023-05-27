package org.mapsa.wallet.models.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
public class WalletDto extends AbstractDto {

    private String userName;

    private Long balance;

    private List<WalletTransactionDto> transactions;
}
