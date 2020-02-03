package com.psw.clinicalcentre.registration;

import com.psw.clinicalcentre.users.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UnprocessedRequestsDTO {

    private Set<UserDTO> userData;

}
