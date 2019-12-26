package com.psw.clinicalcentre.clinics;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class ClinicsServiceImplTest {

    @InjectMocks
    private ClinicsServiceImpl clinicsService;

    @Mock
    private ClinicsRepository clinicsRepository;

    @Mock
    private Set<Clinic> clinics;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void findAll() {
        when(clinicsRepository.findAll()).thenReturn(clinics);
        Set<Clinic> returnValue = clinicsService.findAll();
        verify(clinicsRepository, times(1)).findAll();
        assertEquals(clinics, returnValue);
    }
}