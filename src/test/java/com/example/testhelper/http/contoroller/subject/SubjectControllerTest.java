package com.example.testhelper.http.contoroller.subject;

import com.example.testhelper.Application;
import com.example.testhelper.service.user.AuthService;
import com.example.testhelper.util.JsonUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static java.nio.file.Paths.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@MockBean(JpaMetamodelMappingContext.class)
@ContextConfiguration(classes = Application.class)
@AutoConfigureMockMvc
public class SubjectControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    AuthService authService;

    @Autowired
    JsonUtil jsonUtil;

    @Test
    void getRegisterList() throws Exception{

    String token = authService.getTestTokenById(1);
    String url = "/api/v1/subject/registerList";

        mvc.perform(MockMvcRequestBuilders.get(url)
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk()).andDo(print());
    }
}
