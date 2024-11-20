package org.wrmList.waitingList.visitor.Impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.wrmList.waitingList.visitor.dto.CreateVisitorDTO;
import org.wrmList.waitingList.visitor.dto.ResponseVisitorDTO;
import org.wrmList.waitingList.visitor.entity.Visitor;
import org.wrmList.waitingList.visitor.mapper.CreateVisitorMapper;
import org.wrmList.waitingList.visitor.mapper.ResponseVisitorMapper;
import org.wrmList.waitingList.visitor.repository.VisitorRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VisitorServiceImplTest {

    @Mock
    CreateVisitorMapper createMapper;
    @Mock
    ResponseVisitorMapper responseMapper;
    @Mock
    VisitorRepository visitorRepository;
    @Mock
    ResponseVisitorDTO responseVisitorDTO;
    @InjectMocks
    VisitorServiceImpl visitorService;

    @Test
    void create1() {
        CreateVisitorDTO createVisitorDTO = new CreateVisitorDTO("mostafa", new ArrayList<>());
        Visitor visitor = new Visitor();
        ResponseVisitorDTO responseVisitorDTO1 = new ResponseVisitorDTO(1L, "mostafa", new ArrayList<>());

        when(createMapper.toE(createVisitorDTO)).thenReturn(visitor);
        when(visitorRepository.save(visitor)).thenReturn(visitor);
        when(responseMapper.toOT(visitor)).thenReturn(responseVisitorDTO1);

        ResponseVisitorDTO result = visitorService.create(createVisitorDTO);

        assertNotNull(result);
        assertEquals(1L, result.id());
        assertEquals("mostafa", result.name());
        verify(visitorRepository).save(visitor);

    }

    @Test
    void create2() {
        CreateVisitorDTO createVisitorDTO = new CreateVisitorDTO("mostafa", new ArrayList<>());
        Visitor visitor = new Visitor();
        ResponseVisitorDTO responseVisitorDTO1 = new ResponseVisitorDTO(1L, "mostafa", List.of());

        when(createMapper.toE(createVisitorDTO)).thenReturn(visitor);
        when(visitorRepository.save(visitor)).thenReturn(visitor);
        when(responseMapper.toOT(visitor)).thenReturn(responseVisitorDTO1);

        ResponseVisitorDTO result = visitorService.create(createVisitorDTO);

        assertNotNull(result);
        assertEquals(1L, result.id());
        assertEquals("mostafa", result.name());
        verify(visitorRepository).save(visitor);

    }

    @Test
    void findAll() {
    }

    @Test
    void findById() {
    }

    @Test
    void updateById() {
    }

    @Test
    void deleteById() {
    }
}