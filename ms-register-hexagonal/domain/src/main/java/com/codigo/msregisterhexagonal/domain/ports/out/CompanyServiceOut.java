package com.codigo.msregisterhexagonal.domain.ports.out;

import com.codigo.msregisterhexagonal.domain.aggregates.dto.CompanyDto;

public interface CompanyServiceOut {

    CompanyDto createCompanyOut(String ruc);
}
