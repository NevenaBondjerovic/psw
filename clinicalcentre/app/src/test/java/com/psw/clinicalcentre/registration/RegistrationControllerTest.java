package com.psw.clinicalcentre.registration;

import com.google.gson.Gson;
import com.psw.clinicalcentre.converters.UserConverter;
import com.psw.clinicalcentre.exceptions.BadRequestException;
import com.psw.clinicalcentre.exceptions.NotFoundException;
import com.psw.clinicalcentre.users.User;
import com.psw.clinicalcentre.users.UserDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RegistrationController.class)
public class RegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RegistrationServiceImpl registrationService;

    private Gson gson;

    private RegistrationDTO registrationDTO;
    private User user;
    private Set<User> unprocessedRequests;
    private AcceptRejectDTO acceptRejectDTO;

    @Before
    public void setUp() throws Exception {
        gson = new Gson();
        registrationDTO = RegistrationDTO.builder().username("email@email.com").password("pass").name("name")
                .surname("surname").address("address").city("city").state("state").phoneNumber("number")
                .insuranceNumber("insurance").activated(Boolean.FALSE).build();
        user = User.builder().id(null).username("email@email.com").password("pass").name("name")
                .surname("surname").address("address").city("city").state("state").phoneNumber("number")
                .insuranceNumber("insurance").activated(Boolean.FALSE).build();
        unprocessedRequests = new HashSet<>();
        unprocessedRequests.add(user);
        acceptRejectDTO = AcceptRejectDTO.builder().username("email@email.com")
                .approved(Boolean.TRUE).declineReason("").build();
    }

    @Test
    public void registerUser() throws Exception {
        doNothing().when(registrationService).registerUser(user);
        mockMvc.perform(post("/registration").contentType("application/json; charset=utf-8")
                .content(gson.getAdapter(RegistrationDTO.class).toJson(registrationDTO)))
                .andExpect(status().isOk());
    }

    @Test
    public void registerUser_badRequest_notEmail() throws Exception {
        registrationDTO.setUsername("not email");
        mockMvc.perform(post("/registration").contentType("application/json; charset=utf-8")
                .content(gson.getAdapter(RegistrationDTO.class).toJson(registrationDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void registerUser_badRequest_NameNotSent() throws Exception {
        registrationDTO.setName(null);
        mockMvc.perform(post("/registration").contentType("application/json; charset=utf-8")
                .content(gson.getAdapter(RegistrationDTO.class).toJson(registrationDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void findUnprocessedRequests() throws Exception {
        UserDTO dto = UserConverter.userToUserDto(user);
        Set<UserDTO> dtoList = new HashSet<>();
        dtoList.add(dto);
        UnprocessedRequestsDTO returnValue = new UnprocessedRequestsDTO(dtoList);
        when(registrationService.findUnprocessedRequests()).thenReturn(unprocessedRequests);
        mockMvc.perform(get("/registration"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(gson.getAdapter(UnprocessedRequestsDTO.class).toJson(returnValue)));
    }

    @Test
    public void acceptRegistrationRequest_acceptFlow() throws Exception {
        acceptRejectDTO.setApproved(Boolean.TRUE);
        doNothing().when(registrationService).acceptRegistrationRequest(acceptRejectDTO);
        mockMvc.perform(post("/registration/requests").contentType("application/json; charset=utf-8")
                .content(gson.getAdapter(AcceptRejectDTO.class).toJson(acceptRejectDTO)))
                .andExpect(status().isOk());
        verify(registrationService, times(1)).acceptRegistrationRequest(any());
        verify(registrationService, times(0)).rejectRegistrationRequest(any());
    }

    @Test
    public void acceptRegistrationRequest_acceptFlow_notFound() throws Exception {
        acceptRejectDTO.setApproved(Boolean.TRUE);
        doThrow(NotFoundException.class).when(registrationService).acceptRegistrationRequest(any());
        mockMvc.perform(post("/registration/requests").contentType("application/json; charset=utf-8")
                .content(gson.getAdapter(AcceptRejectDTO.class).toJson(acceptRejectDTO)))
                .andExpect(status().isNotFound());
        verify(registrationService, times(1)).acceptRegistrationRequest(any());
        verify(registrationService, times(0)).rejectRegistrationRequest(any());
    }

    @Test
    public void acceptRegistrationRequest_rejectFlow() throws Exception {
        acceptRejectDTO.setApproved(Boolean.FALSE);
        doNothing().when(registrationService).rejectRegistrationRequest(acceptRejectDTO);
        mockMvc.perform(post("/registration/requests").contentType("application/json; charset=utf-8")
                .content(gson.getAdapter(AcceptRejectDTO.class).toJson(acceptRejectDTO)))
                .andExpect(status().isOk());
        verify(registrationService, times(1)).rejectRegistrationRequest(any());
        verify(registrationService, times(0)).acceptRegistrationRequest(any());
    }

    @Test
    public void acceptRegistrationRequest_rejectFlow_badRequest_declineReasonEmpty() throws Exception {
        acceptRejectDTO.setApproved(Boolean.FALSE);
        doThrow(BadRequestException.class).when(registrationService).rejectRegistrationRequest(any());
        mockMvc.perform(post("/registration/requests").contentType("application/json; charset=utf-8")
                .content(gson.getAdapter(AcceptRejectDTO.class).toJson(acceptRejectDTO)))
                .andExpect(status().isBadRequest());
        verify(registrationService, times(1)).rejectRegistrationRequest(any());
        verify(registrationService, times(0)).acceptRegistrationRequest(any());
    }

    @Test
    public void acceptRegistrationRequest_rejectFlow_notFound() throws Exception {
        acceptRejectDTO.setApproved(Boolean.FALSE);
        doThrow(NotFoundException.class).when(registrationService).rejectRegistrationRequest(any());
        mockMvc.perform(post("/registration/requests").contentType("application/json; charset=utf-8")
                .content(gson.getAdapter(AcceptRejectDTO.class).toJson(acceptRejectDTO)))
                .andExpect(status().isNotFound());
        verify(registrationService, times(1)).rejectRegistrationRequest(any());
        verify(registrationService, times(0)).acceptRegistrationRequest(any());
    }

    @Test
    public void acceptRegistrationRequest_badRequest_approvedNull() throws Exception {
        acceptRejectDTO.setApproved(null);
        mockMvc.perform(post("/registration/requests").contentType("application/json; charset=utf-8")
                .content(gson.getAdapter(AcceptRejectDTO.class).toJson(acceptRejectDTO)))
                .andExpect(status().isBadRequest());
        verify(registrationService, times(0)).rejectRegistrationRequest(any());
        verify(registrationService, times(0)).acceptRegistrationRequest(any());
    }

    @Test
    public void acceptRegistrationRequest_badRequest_usernameBlank() throws Exception {
        acceptRejectDTO.setUsername("");
        mockMvc.perform(post("/registration/requests").contentType("application/json; charset=utf-8")
                .content(gson.getAdapter(AcceptRejectDTO.class).toJson(acceptRejectDTO)))
                .andExpect(status().isBadRequest());
        verify(registrationService, times(0)).rejectRegistrationRequest(any());
        verify(registrationService, times(0)).acceptRegistrationRequest(any());
    }
}