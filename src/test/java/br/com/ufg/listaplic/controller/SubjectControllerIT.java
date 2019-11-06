package br.com.ufg.listaplic.controller;

import br.com.six2six.fixturefactory.Fixture;
import br.com.ufg.listaplic.AbstractIT;
import br.com.ufg.listaplic.dto.SubjectDTO;
import br.com.ufg.listaplic.exception.ExceptionHandlerController;
import br.com.ufg.listaplic.template.SubjectDTOTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SubjectControllerIT extends AbstractIT {

    private static final String BASE_PATH = "/subjects";

    @Autowired
    private SubjectController subjectController;

    private MockMvc mvc;

    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setup() {
        mvc = MockMvcBuilders.standaloneSetup(new ExceptionHandlerController(), subjectController).build();

        JavaTimeModule module = new JavaTimeModule();
        mapper.registerModule(module);
    }

    @Test
    public void shouldFindAll() throws Exception {
        final SubjectDTO subjectDTO = Fixture.from(SubjectDTO.class).gimme(SubjectDTOTemplate.TYPES.SUBJECT_INF0233.name());

        mvc.perform(MockMvcRequestBuilders.get((BASE_PATH))
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$", hasSize(6)))
                .andExpect(jsonPath("$.*.name", hasItem(is(subjectDTO.getName()))))
                .andExpect(jsonPath("$.*.code", hasItem(is(subjectDTO.getCode()))));
    }

}
