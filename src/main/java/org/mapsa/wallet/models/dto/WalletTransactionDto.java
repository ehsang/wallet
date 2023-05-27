package org.mapsa.wallet.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WalletTransactionDto extends AbstractDto {
    private Date date;
    private Long amount;
    private WalletDto walletDto;
    //private String trackingId;
}
