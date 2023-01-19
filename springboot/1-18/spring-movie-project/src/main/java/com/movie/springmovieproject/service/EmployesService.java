package com.movie.springmovieproject.service;

import com.movie.springmovieproject.domain.Employe;

public interface EmployesService {

    Employe createEmployee(String empId, String fname, String sname);
}
