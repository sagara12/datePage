package com.datePage.service;

import com.datePage.exception.WriteNotFound;
import com.datePage.repository.WriteRepository;
import com.datePage.request.WriteCreate;
import com.datePage.request.WriteEdit;
import com.datePage.request.WriteSearch;
import com.datePage.request.domain.Write;
import com.datePage.request.domain.WriteEditor;
import com.datePage.response.WriteResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return write.getWriteId();

    }

    public WriteService(WriteRepository writeRepository) {
        this.writeRepository = writeRepository;
    }

    public WriteResponse get(Long id) {
        Write write =   writeRepository.findById(id)
                .orElseThrow(() -> new WriteNotFound());

         WriteResponse writeResponse = WriteResponse.builder()
                .writeId(write.getWriteId())
                .title(write.getTitle())
                .content(write.getContent())
                .build();

        return writeResponse;
    }


    /*public List<WriteResponse> getList(Pageable pageable) {
        //Pageable pageable = PageRequest.of(page, 5, Sort.Direction.DESC, "writeId");

        //페이징 처리1
        *//* return writeRepository.findAll(pageable).stream()
                .map(write -> new WriteResponse(write)
                 )
                .collect(Collectors.toList());*//*
    }*/

    public List<WriteResponse> getList(WriteSearch writeSearch) {
        //Pageable pageable = PageRequest.of(page, 5, Sort.Direction.DESC, "writeId");

        List<WriteResponse> collect = writeRepository.getList(writeSearch).stream()
                .map(write -> new WriteResponse(write)
                )
                .collect(Collectors.toList());

        return collect;
    }

    @Transactional
    public void edit(Long id, WriteEdit writeEdit) {
        Write write = writeRepository.findById(id)
                .orElseThrow(() -> new WriteNotFound());

        WriteEditor.WriteEditorBuilder writeEditorBuilder = write.toEditor();

        WriteEditor writeEditor = writeEditorBuilder.title(writeEdit.getTitle())
                .content(writeEdit.getContent())
                .build();

        write.edit(writeEditor);
    }

    public void delete(Long writeId) {
        Write write = writeRepository.findById(writeId)
                .orElseThrow(() -> new WriteNotFound());

        //존재하는 경우
        writeRepository.delete(write);
    }
}
