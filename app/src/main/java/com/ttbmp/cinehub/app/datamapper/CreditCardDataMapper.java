package com.ttbmp.cinehub.app.datamapper;

import com.ttbmp.cinehub.app.dto.CreditCardDto;
import com.ttbmp.cinehub.app.utilities.DataMapperHelper;
import com.ttbmp.cinehub.domain.CreditCard;

import java.util.List;

public class CreditCardDataMapper {
    private CreditCardDataMapper() {
    }

    public static CreditCardDto mapToDto(CreditCard creditCard) {
        return new CreditCardDto(
                creditCard.getId(),
                creditCard.getNumber(),
                creditCard.getCvv(),
                creditCard.getExpirationDate()
        );
    }

    public static CreditCard mapToEntity(CreditCardDto creditCardDto) {
        return new CreditCard(
                creditCardDto.getId(),
                creditCardDto.getNumber(),
                creditCardDto.getCvv(),
                creditCardDto.getExpirationDate()
        );
    }

    public static List<CreditCardDto> mapToDtoList(List<CreditCard> creditCard) {
        return DataMapperHelper.mapList(creditCard, CreditCardDataMapper::mapToDto);
    }

    public static List<CreditCard> mapToEntityList(List<CreditCardDto> creditCardDto) {
        return DataMapperHelper.mapList(creditCardDto, CreditCardDataMapper::mapToEntity);
    }

}
