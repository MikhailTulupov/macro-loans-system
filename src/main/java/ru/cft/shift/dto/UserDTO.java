package ru.cft.shift.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.cft.shift.model.UserEntity;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @NotNull
    private Long id;

    @NotNull
    private String surname;

    @NotNull
    private String name;

    @NotNull
    private String patronymic;

    @NotNull
    private BigDecimal funds;

    public static UserDTO getFromEntity(UserEntity user){
        if(user == null){
            return null;
        }

        return new UserDTO()
                .setId(user.getId())
                .setSurname(user.getSurname())
                .setName(user.getName())
                .setPatronymic(user.getPatronymic())
                .setFunds(user.getFunds());
    }
}
