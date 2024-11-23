package com.codigo.msregisterhexagonal.infraestructure.adapters;

import com.codigo.msregisterhexagonal.domain.aggregates.constants.Constants;
import com.codigo.msregisterhexagonal.domain.aggregates.dto.CompanyDto;
import com.codigo.msregisterhexagonal.domain.aggregates.response.ResponseSUNAT;
import com.codigo.msregisterhexagonal.domain.ports.out.CompanyServiceOut;
import com.codigo.msregisterhexagonal.infraestructure.dao.CompanyRepository;
import com.codigo.msregisterhexagonal.infraestructure.entity.CompanyEntity;
import com.codigo.msregisterhexagonal.infraestructure.mapper.CompanyMapper;
import com.codigo.msregisterhexagonal.infraestructure.rest.SunatClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Log4j2
public class CompanyAdapter implements CompanyServiceOut {

    private final CompanyRepository companyRepository;
    private final SunatClient sunatClient;
    private final CompanyMapper companyMapper;

    @Value("${token.api}")
    private String token;

    private CompanyEntity getEntity(String ruc){
        CompanyEntity company = CompanyEntity.builder().build();
        ResponseSUNAT responseSUNAT = execute(ruc);
        if(Objects.nonNull(responseSUNAT)){
            BeanUtils.copyProperties(responseSUNAT, company);
            company.setEstado_crea(Constants.ACTIVE);
            company.setUsua_crea(Constants.USU_ADMIN);
            company.setDate_crea(new Timestamp(System.currentTimeMillis()));
        }
        return company;
    }


    private ResponseSUNAT execute(String ruc){
        String head = "Bearer "+ token;
        return sunatClient.getDataSUNAT(ruc,head);
    }

    @Override
    public CompanyDto createCompanyOut(String ruc) {
        CompanyEntity company = getEntity(ruc);
        CompanyEntity dato = companyRepository.save(company);
        CompanyDto dto = companyMapper.mapToDto(dato);
        log.info("dato del dto: " + dto.toString() );
        return dto;
    }
}
