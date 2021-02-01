package com.ttbmp.cinehub.core.datamapper;

import com.ttbmp.cinehub.core.dto.EmployeeDto;
import com.ttbmp.cinehub.core.entity.Employee;
import com.ttbmp.cinehub.core.entity.Projectionist;
import com.ttbmp.cinehub.core.entity.Usher;
import com.ttbmp.cinehub.core.utilities.DataMapperHelper;

import java.util.List;

/**
 * @author Massimo Mazzetti
 */

public class EmployeeDataMapper {
    private EmployeeDataMapper() {
    }

    public static EmployeeDto mapToDto(Employee employee) {
        return new EmployeeDto(
                employee.getName(),
                employee.getSurname(),
                employee.getRole(),
                CinemaDataMapper.mapToDto(employee.getCinema()));
    }

    public static Employee matToEntity(EmployeeDto employeeDto) {
        if (employeeDto.getRole().equals("maschera")) {
            return new Usher(employeeDto.getName(),
                    employeeDto.getSurname(),
                    employeeDto.getRole(),
                    CinemaDataMapper.matToEntity(employeeDto.getCinema()),
                    0);
        } else {
            return new Projectionist(employeeDto.getName(),
                    employeeDto.getSurname(),
                    employeeDto.getRole(),
                    CinemaDataMapper.matToEntity(employeeDto.getCinema()),
                    0);
        }

    }

    public static List<EmployeeDto> mapToDtoList(List<Employee> employeeList) {
        return DataMapperHelper.mapList(employeeList, EmployeeDataMapper::mapToDto);
    }

    public static List<Employee> mapToEntityList(List<EmployeeDto> employeeListDto) {
        return DataMapperHelper.mapList(employeeListDto, EmployeeDataMapper::matToEntity);
    }
}
