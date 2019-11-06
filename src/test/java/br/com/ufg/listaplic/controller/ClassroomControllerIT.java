package br.com.ufg.listaplic.controller;

import br.com.six2six.fixturefactory.Fixture;
import br.com.ufg.listaplic.AbstractIT;
import br.com.ufg.listaplic.dto.ClassroomDTO;
import br.com.ufg.listaplic.dto.EnrollmentDTO;
import br.com.ufg.listaplic.dto.InstructorDTO;
import br.com.ufg.listaplic.exception.ExceptionHandlerController;
import br.com.ufg.listaplic.model.Student;
import br.com.ufg.listaplic.template.ClassroomDTOTemplate;
import br.com.ufg.listaplic.template.EnrollmentDTOTemplate;
import br.com.ufg.listaplic.template.InstructorDTOTemplate;
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

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ClassroomControllerIT extends AbstractIT {

    private static final String BASE_PATH = "/classrooms";

    @Autowired
    private ClassroomController classroomController;

    private MockMvc mvc;

    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setup() {
        mvc = MockMvcBuilders.standaloneSetup(new ExceptionHandlerController(), classroomController).build();

        JavaTimeModule module = new JavaTimeModule();
        mapper.registerModule(module);
    }

    @Test
    public void shouldFindAll() throws Exception {
        final ClassroomDTO classroomDTO = Fixture.from(ClassroomDTO.class).gimme(ClassroomDTOTemplate.TYPES.CLASSROOM_WITH_ID.name());

        mvc.perform(MockMvcRequestBuilders.get((BASE_PATH))
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.*.id", hasItem(is(classroomDTO.getId().toString()))))
                .andExpect(jsonPath("$.*.name", hasItem(is(classroomDTO.getName()))))
                .andExpect(jsonPath("$.*.code", hasItem(is(classroomDTO.getCode()))))
                .andExpect(jsonPath("$.*.subjectCode", hasItem(is(classroomDTO.getSubjectCode()))))
                .andExpect(jsonPath("$.*.instructorId", hasItem(is(classroomDTO.getInstructorId()))));
    }

    @Test
    public void shouldFindById() throws Exception {
        final ClassroomDTO classroomDTO = Fixture.from(ClassroomDTO.class).gimme(ClassroomDTOTemplate.TYPES.CLASSROOM_WITH_ID.name());

        mvc.perform(MockMvcRequestBuilders.get((BASE_PATH + "/" + classroomDTO.getId()))
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id", is(classroomDTO.getId().toString())))
                .andExpect(jsonPath("$.name", is(classroomDTO.getName())))
                .andExpect(jsonPath("$.code", is(classroomDTO.getCode())))
                .andExpect(jsonPath("$.subjectCode", is(classroomDTO.getSubjectCode())))
                .andExpect(jsonPath("$.instructorId", is(classroomDTO.getInstructorId())));
    }

    @Test
    public void shouldFindByStudentId() throws Exception {
        final Student student = Fixture.from(Student.class).gimme(StudentTemplate.TYPES.STUDENT_WITH_ID.name());
        final ClassroomDTO classroomDTO = Fixture.from(ClassroomDTO.class).gimme(ClassroomDTOTemplate.TYPES.CLASSROOM_WITH_ID.name());

        mvc.perform(MockMvcRequestBuilders.get((BASE_PATH + "/student"))
                .param("studentId", student.getId().toString())
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.*.id", hasItem(is(classroomDTO.getId().toString()))))
                .andExpect(jsonPath("$.*.name", hasItem(is(classroomDTO.getName()))))
                .andExpect(jsonPath("$.*.code", hasItem(is(classroomDTO.getCode()))))
                .andExpect(jsonPath("$.*.subjectCode", hasItem(is(classroomDTO.getSubjectCode()))))
                .andExpect(jsonPath("$.*.instructorId", hasItem(is(classroomDTO.getInstructorId()))));
    }

    @Test
    public void shouldFindByInstructorId() throws Exception {
        final InstructorDTO instructorDTO = Fixture.from(InstructorDTO.class).gimme(InstructorDTOTemplate.TYPES.INSTRUCTOR.name());
        final ClassroomDTO classroomDTO = Fixture.from(ClassroomDTO.class).gimme(ClassroomDTOTemplate.TYPES.CLASSROOM_WITH_ID.name());

        mvc.perform(MockMvcRequestBuilders.get((BASE_PATH + "/instructor"))
                .param("instructorId", instructorDTO.getId())
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.*.id", hasItem(is(classroomDTO.getId().toString()))))
                .andExpect(jsonPath("$.*.name", hasItem(is(classroomDTO.getName()))))
                .andExpect(jsonPath("$.*.code", hasItem(is(classroomDTO.getCode()))))
                .andExpect(jsonPath("$.*.subjectCode", hasItem(is(classroomDTO.getSubjectCode()))))
                .andExpect(jsonPath("$.*.instructorId", hasItem(is(classroomDTO.getInstructorId()))));
    }

    @Test
    public void shouldSave() throws Exception {
        final ClassroomDTO classroomDTO = Fixture.from(ClassroomDTO.class).gimme(ClassroomDTOTemplate.TYPES.ANOTHER_CLASSROOM.name());

        mvc.perform(MockMvcRequestBuilders.post((BASE_PATH))
                .content(mapper.writeValueAsString(classroomDTO))
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.name", is(classroomDTO.getName())))
                .andExpect(jsonPath("$.code", notNullValue()))
                .andExpect(jsonPath("$.subjectCode", is(classroomDTO.getSubjectCode())))
                .andExpect(jsonPath("$.instructorId", is(classroomDTO.getInstructorId())));
    }

    @Test
    public void shouldUpdate() throws Exception {
        final ClassroomDTO classroomDTO = Fixture.from(ClassroomDTO.class).gimme(ClassroomDTOTemplate.TYPES.CLASSROOM_WITH_ID.name());
        classroomDTO.setName("Nome Alterado");

        mvc.perform(MockMvcRequestBuilders.put((BASE_PATH + "/" + classroomDTO.getId()))
                .content(mapper.writeValueAsString(classroomDTO))
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id", is(classroomDTO.getId().toString())))
                .andExpect(jsonPath("$.name", is(classroomDTO.getName())))
                .andExpect(jsonPath("$.code", notNullValue()))
                .andExpect(jsonPath("$.subjectCode", is(classroomDTO.getSubjectCode())))
                .andExpect(jsonPath("$.instructorId", is(classroomDTO.getInstructorId())));
    }

    @Test
    public void shouldDeleteById() throws Exception {
        final ClassroomDTO classroomDTO = Fixture.from(ClassroomDTO.class).gimme(ClassroomDTOTemplate.TYPES.CLASSROOM_WITH_ID.name());

        mvc.perform(MockMvcRequestBuilders.delete((BASE_PATH + "/" + classroomDTO.getId()))
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful());

        mvc.perform(MockMvcRequestBuilders.get((BASE_PATH + "/" + classroomDTO.getId()))
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.message", is("Classroom not found")));
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

}
