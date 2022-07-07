package ru.cft.shift.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.cft.shift.model.LoanEntity;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class LoanDTO {
    @NotNull
    private BigDecimal debt;

    @NotNull
    private Instant maturity;

    public static LoanDTO getFromEntity(LoanEntity loanEntity){
        if(loanEntity == null){
            return null;
        }

        return new LoanDTO()
                .setDebt(loanEntity.getDebt())
                .setMaturity(loanEntity.getMaturity());
    }
}
