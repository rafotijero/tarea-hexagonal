package com.codigo.msregisterhexagonal.infraestructure.mapper;

import com.codigo.msregisterhexagonal.domain.aggregates.dto.CompanyDto;
import com.codigo.msregisterhexagonal.infraestructure.entity.CompanyEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CompanyMapper {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public CompanyDto mapToDto(CompanyEntity companyEntity){
        return MODEL_MAPPER.map(companyEntity, CompanyDto.class);
    }

}
