package com.psw.clinicalcentre.scores;

import com.psw.clinicalcentre.appointments.AppointmentWithScoreDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/score")
@CrossOrigin(value = "*")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void addScore(@RequestBody AppointmentWithScoreDTO request){
        scoreService.addScore(request);
    }
}
