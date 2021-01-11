package com.ttbmp.cinehub.app.datamapper;

import com.ttbmp.cinehub.app.dto.ShiftDto;
import com.ttbmp.cinehub.app.utilities.DataMapperHelper;
import com.ttbmp.cinehub.core.entity.Employee;
import com.ttbmp.cinehub.core.entity.Shift;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

/**
 * @author Fabio Buracchi
 */
public class ShiftDataMapper {

    private ShiftDataMapper() {

    }

    public static ShiftDto mapToDto(Shift shift) {
        Objects.requireNonNull(shift);
        return new ShiftDto(
                shift.getEmployee().getName(),
                LocalDate.parse(shift.getDate()),
                LocalTime.parse(shift.getStart()),
                LocalTime.parse(shift.getEnd())
        );
    }

    public static Shift mapToEntity(ShiftDto shiftDto) {
        Objects.requireNonNull(shiftDto);
        return new Shift(
                new Employee(),
                shiftDto.getDate().toString(),
                shiftDto.getStart().toString(),
                shiftDto.getEnd().toString()
        );
    }

    public static List<ShiftDto> mapToDtoList(List<Shift> shiftList) {
        Objects.requireNonNull(shiftList);
        return DataMapperHelper.mapList(shiftList, ShiftDataMapper::mapToDto);
    }

    public static List<Shift> mapToEntityList(List<ShiftDto> shiftDtoList) {
        Objects.requireNonNull(shiftDtoList);
        return DataMapperHelper.mapList(shiftDtoList, ShiftDataMapper::mapToEntity);
    }

}
