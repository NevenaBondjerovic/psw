package com.psw.clinicalcentre.clinics;

import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ClinicController.class)
public class ClinicControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClinicsServiceImpl clinicsService;

    @Mock
    private Set<Clinic> clinics;

    private Gson gson;

    @Before
    public void setUp() throws Exception {
        gson = new Gson();
    }

    @Test
    public void findAll() throws Exception {
        when(clinicsService.findAll()).thenReturn(clinics);
        mockMvc.perform(get("/clinics"))
                .andExpect(status().isOk());
    }
}