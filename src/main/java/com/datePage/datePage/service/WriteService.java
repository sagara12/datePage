package com.datePage.datePage.service;

import com.datePage.datePage.repository.PostRepository;
import com.datePage.datePage.repository.WriteRepository;
import com.datePage.datePage.request.PostCreate;
import com.datePage.datePage.request.WriteCreate;
import com.datePage.datePage.request.domain.Post;
import com.datePage.datePage.request.domain.Write;
import com.datePage.datePage.response.WriteResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

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




}
