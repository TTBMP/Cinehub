package com.ttbmp.cinehub.app.datamapper;

import com.ttbmp.cinehub.app.dto.ShiftDto;
import com.ttbmp.cinehub.app.dto.ShiftProjectionistDto;
import com.ttbmp.cinehub.app.dto.ShiftUsherDto;
import com.ttbmp.cinehub.app.dto.UsherDto;
import com.ttbmp.cinehub.app.utilities.DataMapperHelper;
import com.ttbmp.cinehub.domain.employee.Employee;
import com.ttbmp.cinehub.domain.employee.Usher;
import com.ttbmp.cinehub.domain.shift.ProjectionistShift;
import com.ttbmp.cinehub.domain.shift.Shift;
import com.ttbmp.cinehub.domain.shift.UsherShift;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

/**
 * @author Fabio Buracchi, Massimo Mazzetti
 */
public class ShiftDataMapper {

    private ShiftDataMapper() {

    }

    public static ShiftDto mapToDto(Shift shift) {
        Objects.requireNonNull(shift);
        var employee = shift.getEmployee();
        if (employee instanceof Usher) {
            return new ShiftUsherDto(
                    shift.getId(),
                    EmployeeDataMapper.mapToDto(employee),
                    LocalDate.parse(shift.getDate()),
                    LocalTime.parse(shift.getStart()),
                    LocalTime.parse(shift.getEnd())
            );
        } else {
            return new ShiftProjectionistDto( //TODO: troppo lenta
                    shift.getId(),
                    EmployeeDataMapper.mapToDto(employee),
                    LocalDate.parse(shift.getDate()),
                    LocalTime.parse(shift.getStart()),
                    LocalTime.parse(shift.getEnd()),
                    HallDataMapper.mapToDto(((ProjectionistShift) shift).getHall())
            );
        }
    }

    public static Shift mapToEntity(ShiftDto shiftDto) {
        Objects.requireNonNull(shiftDto);
        if (shiftDto.getEmployee() instanceof UsherDto)
            return new UsherShift(
                    shiftDto.getId(),
                    EmployeeDataMapper.matToEntity(shiftDto.getEmployee()),
                    shiftDto.getDate().toString(),
                    shiftDto.getStart().toString(),
                    shiftDto.getEnd().toString()
            );
        else
            return new ProjectionistShift(
                    shiftDto.getId(),
                    EmployeeDataMapper.matToEntity(shiftDto.getEmployee()),
                    shiftDto.getDate().toString(),
                    shiftDto.getStart().toString(),
                    shiftDto.getEnd().toString(),
                    HallDataMapper.mapToEntity(((ShiftProjectionistDto) shiftDto).getHallDto()),
                    null
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
