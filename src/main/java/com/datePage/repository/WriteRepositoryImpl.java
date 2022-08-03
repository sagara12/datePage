package com.datePage.repository;

import com.datePage.request.WriteSearch;
import com.datePage.request.domain.QWrite;
import com.datePage.request.domain.Write;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class WriteRepositoryImpl implements WriteRepositoryCustom {

    private  final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Write> getList(WriteSearch writeSearch) {
        return jpaQueryFactory.selectFrom(QWrite.write)
                .limit(writeSearch.getSize())
                .offset(writeSearch.getOffset())
                .orderBy(QWrite.write.writeId.desc())
                .fetch();
    }
}
