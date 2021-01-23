package com.ttbmp.cinehub.core.datamapper;

import com.ttbmp.cinehub.core.dto.ShiftDto;
import com.ttbmp.cinehub.core.entity.Shift;
import com.ttbmp.cinehub.core.utilities.DataMapperHelper;

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
        if (shift.getEmployee().getRole().equals("maschera"))
            return new ShiftDto(
                    EmployeeDataMapper.mapToDto(shift.getEmployee()),
                    LocalDate.parse(shift.getDate()),
                    LocalTime.parse(shift.getStart()),
                    LocalTime.parse(shift.getEnd())
            );
        else
            return new ShiftDto(
                    EmployeeDataMapper.mapToDto(shift.getEmployee()),
                    LocalDate.parse(shift.getDate()),
                    LocalTime.parse(shift.getStart()),
                    LocalTime.parse(shift.getEnd()),
                    HallDataMapper.mapToDto(shift.getHall())
            );
    }

    public static Shift mapToEntity(ShiftDto shiftDto) {
        Objects.requireNonNull(shiftDto);
        if (shiftDto.getEmployee().getRole().equals("maschera"))
            return new Shift(
                    EmployeeDataMapper.matToEntity(shiftDto.getEmployee()),
                    shiftDto.getDate().toString(),
                    shiftDto.getStart().toString(),
                    shiftDto.getEnd().toString()
            );
        else
            return new Shift(
                    EmployeeDataMapper.matToEntity(shiftDto.getEmployee()),
                    shiftDto.getDate().toString(),
                    shiftDto.getStart().toString(),
                    shiftDto.getEnd().toString(),
                    HallDataMapper.matToEntity(shiftDto.getHall())
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
