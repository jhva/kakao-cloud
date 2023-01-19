package com.movie.springmovieproject.service;


import com.movie.springmovieproject.domain.Employe;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class EmployeServiceImpl implements EmployesService {
    @Override
    public Employe createEmployee(String empId, String fname, String sname) {
        Employe emp = Employe.builder()
                .emId(empId)
                .firstname(fname)
                .secondName(sname)
                .build();

        return emp;
    }
}
