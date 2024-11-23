package com.codigo.msregisterhexagonal.domain.ports.in;

import com.codigo.msregisterhexagonal.domain.aggregates.dto.CompanyDto;

public interface CompanyServiceIn {

    CompanyDto createCompanyIn(String ruc);
}
