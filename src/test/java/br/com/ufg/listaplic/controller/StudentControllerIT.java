package br.com.ufg.listaplic.controller;

import br.com.six2six.fixturefactory.Fixture;
import br.com.ufg.listaplic.AbstractIT;
import br.com.ufg.listaplic.dto.EnrollmentDTO;
import br.com.ufg.listaplic.exception.ExceptionHandlerController;
import br.com.ufg.listaplic.model.Student;
import br.com.ufg.listaplic.template.EnrollmentDTOTemplate;
import br.com.ufg.listaplic.template.StudentTemplate;
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

import java.util.UUID;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class StudentControllerIT extends AbstractIT {

    private static final String BASE_PATH = "/students";

    @Autowired
    private StudentController studentController;

    private MockMvc mvc;

    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setup() {
        mvc = MockMvcBuilders.standaloneSetup(new ExceptionHandlerController(), studentController).build();

        JavaTimeModule module = new JavaTimeModule();
        mapper.registerModule(module);
    }

    @Test
    public void shouldFindAll() throws Exception {
        final Student student = Fixture.from(Student.class).gimme(StudentTemplate.TYPES.STUDENT_WITH_ID.name());

        mvc.perform(MockMvcRequestBuilders.get((BASE_PATH))
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.*.id", hasItem(is(student.getId().toString()))))
                .andExpect(jsonPath("$.*.name", hasItem(is(student.getName()))))
                .andExpect(jsonPath("$.*.email", hasItem(is(student.getEmail()))));
    }

    @Test
    public void shouldFindById() throws Exception {
        final Student student = Fixture.from(Student.class).gimme(StudentTemplate.TYPES.STUDENT_WITH_ID.name());

        mvc.perform(MockMvcRequestBuilders.get((BASE_PATH + "/" + student.getId()))
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id", is(student.getId().toString())))
                .andExpect(jsonPath("$.name", is(student.getName())))
                .andExpect(jsonPath("$.email", is(student.getEmail())));
    }

    @Test
    public void shouldSave() throws Exception {
        final Student student = Fixture.from(Student.class).gimme(StudentTemplate.TYPES.NEW_STUDENT.name());

        mvc.perform(MockMvcRequestBuilders.post((BASE_PATH))
                .content(mapper.writeValueAsString(student))
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.name", is(student.getName())))
                .andExpect(jsonPath("$.email", is(student.getEmail())));
    }

    @Test
    public void shouldUpdate() throws Exception {
        final Student student = Fixture.from(Student.class).gimme(StudentTemplate.TYPES.STUDENT_WITH_ID.name());
        student.setName("Nome Alterado");

        mvc.perform(MockMvcRequestBuilders.put((BASE_PATH + "/" + student.getId()))
                .content(mapper.writeValueAsString(student))
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id", is(student.getId().toString())))
                .andExpect(jsonPath("$.name", is(student.getName())))
                .andExpect(jsonPath("$.email", is(student.getEmail())));
    }

    @Test
    public void shouldDeleteById() throws Exception {
        final Student student = Fixture.from(Student.class).gimme(StudentTemplate.TYPES.STUDENT_WITH_ID.name());

        mvc.perform(MockMvcRequestBuilders.delete((BASE_PATH + "/" + student.getId()))
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful());

        mvc.perform(MockMvcRequestBuilders.get((BASE_PATH + "/" + student.getId()))
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.message", is("Student not found")));
    }

    @Test
    public void shouldEnrollmentStudentWithSuccess() throws Exception {
        final Student student = Fixture.from(Student.class).gimme(StudentTemplate.TYPES.STUDENT_WITH_ID.name());
        final EnrollmentDTO enrollmentDTO = Fixture.from(EnrollmentDTO.class).gimme(EnrollmentDTOTemplate.TYPES.ENROLLMENT.name());

        mvc.perform(MockMvcRequestBuilders.post((BASE_PATH + "/" + student.getId() + "/enrollment"))
                .content(mapper.writeValueAsString(enrollmentDTO))
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void shouldEnrollmentStudentWithFailedWhenStudentNotFound() throws Exception {
        final EnrollmentDTO enrollmentDTO = Fixture.from(EnrollmentDTO.class).gimme(EnrollmentDTOTemplate.TYPES.ENROLLMENT.name());

        mvc.perform(MockMvcRequestBuilders.post((BASE_PATH + "/" + UUID.randomUUID() + "/enrollment"))
                .content(mapper.writeValueAsString(enrollmentDTO))
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.message", is("Student not found")));
    }

    @Test
    public void shouldEnrollmentStudentWithFailedWhenClassroomNotFound() throws Exception {
        final Student student = Fixture.from(Student.class).gimme(StudentTemplate.TYPES.STUDENT_WITH_ID.name());
        final EnrollmentDTO enrollmentDTO = Fixture.from(EnrollmentDTO.class).gimme(EnrollmentDTOTemplate.TYPES.ENROLLMENT.name());
        enrollmentDTO.setCode("CODIGO_INVALIDO");

        mvc.perform(MockMvcRequestBuilders.post((BASE_PATH + "/" + student.getId() + "/enrollment"))
                .content(mapper.writeValueAsString(enrollmentDTO))
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.message", is("Classroom not found")));
    }

    @Test
    public void shouldEnrollmentStudentWithFailedWhenStudentIsAlreadyEnrolled() throws Exception {
        final Student student = Fixture.from(Student.class).gimme(StudentTemplate.TYPES.STUDENT_WITH_ID.name());
        final EnrollmentDTO enrollmentDTO = Fixture.from(EnrollmentDTO.class).gimme(EnrollmentDTOTemplate.TYPES.ENROLLMENT.name());
        enrollmentDTO.setCode("CWS4558");

        mvc.perform(MockMvcRequestBuilders.post((BASE_PATH + "/" + student.getId() + "/enrollment"))
                .content(mapper.writeValueAsString(enrollmentDTO))
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isConflict())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.message", is("Student is already enrolled")));
    }

}
