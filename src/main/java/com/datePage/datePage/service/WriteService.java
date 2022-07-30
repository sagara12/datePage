package com.datePage.datePage.service;

import com.datePage.datePage.repository.PostRepository;
import com.datePage.datePage.repository.WriteRepository;
import com.datePage.datePage.request.PostCreate;
import com.datePage.datePage.request.WriteCreate;
import com.datePage.datePage.request.domain.Post;
import com.datePage.datePage.request.domain.Write;
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

    public Write get(Long id) {
        Write write =   writeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 글립니다"));

        /*//값이 있으면
        if (write.isPresent()) {
            return write.get();
        }*/

        return write;
    }
}
