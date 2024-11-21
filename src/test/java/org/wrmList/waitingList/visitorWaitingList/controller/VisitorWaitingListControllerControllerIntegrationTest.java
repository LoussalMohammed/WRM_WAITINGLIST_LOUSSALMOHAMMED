package org.wrmList.waitingList.visitorWaitingList.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.wrmList.waitingList.visitorWaitingList.repository.VisitorWaitingListRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.wrmList.waitingList.shared.dto.request.PaginationRequest;
import org.springframework.test.context.ActiveProfiles;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class VisitorWaitingListControllerControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowireddd
    private VisitorWaitingListRepository visitorWaitingListRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {

    }

    @Test
    void shouldReturnFirstPageWithDefaultSize() throws Exception {
        // Given
        PaginationRequest request = PaginationRequest.of(0, 10, "arrivalTime", "asc");

        // When
        ResultActions result = mockMvc.perform(get("/api/v1/visitorWaitingList")
                .param("page", String.valueOf(request.page()))
                .param("size", String.valueOf(request.size()))
                .param("sortBy", request.sortBy())
                .param("sortDirection", request.sortDirection()));

        // Then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.items").isArray())
                .andExpect(jsonPath("$.page").value(0))
                .andExpect(jsonPath("$.size").value(10))
                .andExpect(jsonPath("$.totalElements").isNumber())
                .andExpect(jsonPath("$.totalPages").isNumber());
    }

    @Test
    void shouldReturnBadRequestForInvalidPageSize() throws Exception {
        mockMvc.perform(get("/api/v1/visitorWaitingList")
                .param("page", "0")
                .param("size", "101")
                .param("sortBy", "arrivalTime")
                .param("sortDirection", "asc"))
                .andExpect(status().isBadRequest());
    }
} 