package com.mood.mentor.Controller;
import com.mood.mentor.Mapper.CheckMapper;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckingController {
    private final CheckMapper checkMapper;

    public CheckingController(CheckMapper checkMapper) {
        this.checkMapper = checkMapper;
    }
}
