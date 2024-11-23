package com.codigo.msregisterhexagonal.infraestructure.dao;

import com.codigo.msregisterhexagonal.infraestructure.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {
}
