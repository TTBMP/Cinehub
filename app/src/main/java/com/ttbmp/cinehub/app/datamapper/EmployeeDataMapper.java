package com.ttbmp.cinehub.app.datamapper;

import com.ttbmp.cinehub.app.dto.EmployeeDto;
import com.ttbmp.cinehub.app.dto.ProjectionistDto;
import com.ttbmp.cinehub.app.dto.UsherDto;
import com.ttbmp.cinehub.domain.Employee;
import com.ttbmp.cinehub.domain.Projectionist;
import com.ttbmp.cinehub.domain.Usher;
import com.ttbmp.cinehub.app.utilities.DataMapperHelper;

import java.util.List;

/**
 * @author Massimo Mazzetti
 */
public class EmployeeDataMapper {

    private EmployeeDataMapper() {
    }

    public static EmployeeDto mapToDto(Employee employee) {
        if (employee instanceof Usher) {
            return new UsherDto(
                    employee.getName(),
                    employee.getSurname(),
                    CinemaDataMapper.mapToDto(employee.getCinema()));
        } else {
            return new ProjectionistDto(
                    employee.getName(),
                    employee.getSurname(),
                    CinemaDataMapper.mapToDto(employee.getCinema()));
        }
    }

    public static Employee matToEntity(EmployeeDto employeeDto) {
        if (employeeDto instanceof UsherDto) {
            return new Usher(employeeDto.getName(),
                    employeeDto.getSurname(),
                    CinemaDataMapper.mapToEntity(employeeDto.getCinema()));
        } else {
            return new Projectionist(employeeDto.getName(),
                    employeeDto.getSurname(),
                    CinemaDataMapper.mapToEntity(employeeDto.getCinema()));
        }

    }

    public static List<EmployeeDto> mapToDtoList(List<Employee> employeeList) {
        return DataMapperHelper.mapList(employeeList, EmployeeDataMapper::mapToDto);
    }

    public static List<Employee> mapToEntityList(List<EmployeeDto> employeeListDto) {
        return DataMapperHelper.mapList(employeeListDto, EmployeeDataMapper::matToEntity);
    }

}