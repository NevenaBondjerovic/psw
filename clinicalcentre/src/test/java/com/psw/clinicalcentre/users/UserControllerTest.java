package com.psw.clinicalcentre.users;

import com.google.gson.Gson;
import com.psw.clinicalcentre.exceptions.BadRequestException;
import com.psw.clinicalcentre.exceptions.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServiceImpl userService;

    private Gson gson;

    @Mock
    private LoginDTO loginDTO;

    private ActivationDataDTO activationRequest;
    private User user;
    private UserDTO userDTO;
    private String username;
    private String password;
    private Integer id;

    @Before
    public void setUp() throws Exception {
        gson = new Gson();
        username = "username@username.com";
        password = "some pass";
        id = 56789;
        user = new User(id, username, password, "name", "surname", "address", "city",
                "state", "phoneNumber", "insurance", Boolean.TRUE);
        userDTO = new UserDTO(id, username, password, "name", "surname", "address", "city",
                "state", "phoneNumber", "insurance");
        activationRequest = new ActivationDataDTO(id, username, password);
    }

    @Test
    public void loginUser() throws Exception {
        when(loginDTO.getUsername()).thenReturn(username);
        when(loginDTO.getPassword()).thenReturn(password);
        doNothing().when(userService).findUser(username, password);
        mockMvc.perform(post("/users/login").contentType("application/json; charset=utf-8")
                .content(gson.getAdapter(LoginDTO.class).toJson(new LoginDTO(username,password))))
                .andExpect(status().isOk());
    }

    @Test
    public void loginUser_notFound() throws Exception {
        when(loginDTO.getUsername()).thenReturn(username);
        when(loginDTO.getPassword()).thenReturn(password);
        doThrow(NotFoundException.class).when(userService).findUser(username, password);
        mockMvc.perform(post("/users/login").contentType("application/json; charset=utf-8")
                .content(gson.getAdapter(LoginDTO.class).toJson(new LoginDTO(username,password))))
                .andExpect(status().isNotFound());
    }

    @Test
    public void loginUser_badRequest_usernameIsNull() throws Exception {
        mockMvc.perform(post("/users/login").contentType("application/json; charset=utf-8")
                .content(gson.getAdapter(LoginDTO.class).toJson(new LoginDTO(null, password))))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void loginUser_badRequest_passwordIsNull() throws Exception {
        mockMvc.perform(post("/users/login").contentType("application/json; charset=utf-8")
                .content(gson.getAdapter(LoginDTO.class).toJson(new LoginDTO(null, password))))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void findUserById() throws Exception {
        doNothing().when(userService).findById(id);
        mockMvc.perform(get("/users/{id}", id))
                .andExpect(status().isOk());
    }

    @Test
    public void findUserById_notFound() throws Exception {
        doThrow(NotFoundException.class).when(userService).findById(id);
        mockMvc.perform(get("/users/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    public void findUserById_badRequest() throws Exception {
        doThrow(BadRequestException.class).when(userService).findById(id);
        mockMvc.perform(get("/users/{id}", id))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void findUserByUsername() throws Exception {
        when(userService.findByUsername(username)).thenReturn(user);
        mockMvc.perform(get("/users/myprofile/{username}", username))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(gson.getAdapter(UserDTO.class).toJson(userDTO)));
    }

    @Test
    public void findUserByUsername_notFound() throws Exception {
        when(userService.findByUsername(username)).thenThrow(NotFoundException.class);
        mockMvc.perform(get("/users/myprofile/{username}", username))
                .andExpect(status().isNotFound());
    }

    @Test
    public void activateAccount() throws Exception {
        doNothing().when(userService).activateAccount(id, username, password);
        mockMvc.perform(post("/users/activate").contentType("application/json; charset=utf-8")
                .content(gson.getAdapter(ActivationDataDTO.class).toJson(activationRequest)))
                .andExpect(status().isOk());
    }

    @Test
    public void activateAccount_notFound() throws Exception {
        doThrow(NotFoundException.class).when(userService).activateAccount(id, username, password);
        mockMvc.perform(post("/users/activate").contentType("application/json; charset=utf-8")
                .content(gson.getAdapter(ActivationDataDTO.class).toJson(activationRequest)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void activateAccount_badRequest_fromService() throws Exception {
        doThrow(BadRequestException.class).when(userService).activateAccount(id, username, password);
        mockMvc.perform(post("/users/activate").contentType("application/json; charset=utf-8")
                .content(gson.getAdapter(ActivationDataDTO.class).toJson(activationRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void activateAccount_badRequest_idIsNull() throws Exception {
        mockMvc.perform(post("/users/activate").contentType("application/json; charset=utf-8")
                .content(gson.getAdapter(ActivationDataDTO.class).toJson(new ActivationDataDTO(null, username, password))))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void activateAccount_badRequest_usernameIsNull() throws Exception {
        mockMvc.perform(post("/users/activate").contentType("application/json; charset=utf-8")
                .content(gson.getAdapter(ActivationDataDTO.class).toJson(new ActivationDataDTO(id, null, password))))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void activateAccount_badRequest_passwordIsBlank() throws Exception {
        mockMvc.perform(post("/users/activate").contentType("application/json; charset=utf-8")
                .content(gson.getAdapter(ActivationDataDTO.class).toJson(new ActivationDataDTO(id, username, ""))))
                .andExpect(status().isBadRequest());
    }

}