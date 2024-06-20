package com.faceit.assignmentelibrary.config;


import com.faceit.assignmentelibrary.core.service.BookService;
import com.faceit.assignmentelibrary.core.service.PatronService;
import com.faceit.assignmentelibrary.web.controller.BookController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = BookController.class)
@Import({GeneralTestConfig.class})
@ActiveProfiles("test")
public abstract class AbstractUnitWebTest {
    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;
    @MockBean
    protected BookService bookService;

}
