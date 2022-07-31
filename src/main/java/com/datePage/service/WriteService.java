package com.datePage.service;

import com.datePage.repository.WriteRepository;
import com.datePage.request.WriteCreate;
import com.datePage.request.domain.Write;
import com.datePage.response.WriteResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class WriteService {

    private final WriteRepository writeRepository;

    public Long write(WriteCreate writeCreate) {

        Write write = Write.builder()
                .title(writeCreate.getTitle())
                .content(writeCreate.getContent())
                .build();

        writeRepository.save(write);
        return write.getWrite_id();

    }

    public WriteService(WriteRepository writeRepository) {
        this.writeRepository = writeRepository;
    }

    public WriteResponse get(Long id) {
        Write write =   writeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 글립니다"));

         WriteResponse writeResponse = WriteResponse.builder()
                .write_id(write.getWrite_id())
                .title(write.getTitle())
                .content(write.getContent())
                .build();

        return writeResponse;
    }


    public List<WriteResponse> getList() {
        return writeRepository.findAll().stream()
                .map(write -> new WriteResponse(write)
                 )
                .collect(Collectors.toList());
    }
}