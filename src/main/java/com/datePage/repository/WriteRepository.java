package com.datePage.repository;

import com.datePage.request.domain.Write;
import org.springframework.data.jpa.repository.JpaRepository;


public interface WriteRepository extends JpaRepository<Write, Long> , WriteRepositoryCustom {

}
