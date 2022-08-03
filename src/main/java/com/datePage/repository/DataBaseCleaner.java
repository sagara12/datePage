package com.datePage.repository;

import com.datePage.request.WriteSearch;
import com.datePage.request.domain.QWrite;
import com.datePage.request.domain.Write;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional
public class DataBaseCleaner {

    private final JPAQueryFactory jpaQueryFactory;

    public void cleanWriteDBDataBase() {
        jpaQueryFactory.delete(QWrite.write).execute();
    }
}
