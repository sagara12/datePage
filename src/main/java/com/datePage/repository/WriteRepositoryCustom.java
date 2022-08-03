package com.datePage.repository;

import com.datePage.request.WriteSearch;
import com.datePage.request.domain.Write;

import java.util.List;

public interface WriteRepositoryCustom {

    List<Write> getList(WriteSearch writeSearch);
}
