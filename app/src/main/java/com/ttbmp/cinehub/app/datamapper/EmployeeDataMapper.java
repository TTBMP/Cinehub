package com.ttbmp.cinehub.app.datamapper;

import com.ttbmp.cinehub.app.dto.EmployeeDto;
import com.ttbmp.cinehub.app.dto.ProjectionistDto;
import com.ttbmp.cinehub.app.dto.UsherDto;
import com.ttbmp.cinehub.app.utilities.DataMapperHelper;
import com.ttbmp.cinehub.domain.employee.Employee;
import com.ttbmp.cinehub.domain.employee.Usher;

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
                    employee.getId(),
                    employee.getName(),
                    employee.getSurname(),
                    CinemaDataMapper.mapToDto(employee.getCinema())
            );
        } else {
            return new ProjectionistDto(
                    employee.getId(),
                    employee.getName(),
                    employee.getSurname(),
                    CinemaDataMapper.mapToDto(employee.getCinema())
            );
        }
    }

    public static List<EmployeeDto> mapToDtoList(List<Employee> employeeList) {
        return DataMapperHelper.mapList(employeeList, EmployeeDataMapper::mapToDto);
    }

}
