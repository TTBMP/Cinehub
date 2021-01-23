package com.ttbmp.cinehub.core.datamapper;

import com.ttbmp.cinehub.core.dto.EmployeeDto;
import com.ttbmp.cinehub.core.entity.Employee;
import com.ttbmp.cinehub.core.utilities.DataMapperHelper;

import java.util.List;

public class EmployeeDataMapper {
    public EmployeeDataMapper() {
    }

    public static EmployeeDto mapToDto(Employee employee) {
        return new EmployeeDto(
                employee.getName(),
                employee.getSurname(),
                employee.getRole(),
                employee.getHourRemain(),
                employee.getMinRemain(),
                CinemaDataMapper.mapToDto(employee.getCinema()));
    }

    public static Employee matToEntity(EmployeeDto employeeDto) {
        return new Employee(
                employeeDto.getName(),
                employeeDto.getSurname(),
                employeeDto.getRole(),
                employeeDto.getHourRemain(),
                employeeDto.getMinRemain(),
                CinemaDataMapper.matToEntity(employeeDto.getCinema()),
                0
        );
    }

    public static List<EmployeeDto> mapToDtoList(List<Employee> employeeList) {
        return DataMapperHelper.mapList(employeeList, EmployeeDataMapper::mapToDto);
    }

    public static List<Employee> mapToEntityList(List<EmployeeDto> employeeListDto) {
        return DataMapperHelper.mapList(employeeListDto, EmployeeDataMapper::matToEntity);
    }
}
