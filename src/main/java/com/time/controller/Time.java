package com.time.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.TimeZone;

@Controller
public class Time {
    @GetMapping("/")
    public String showTime(){
        return "index";
    }

    @GetMapping("/time")
    public ModelAndView time(@RequestParam("zone") String city) {
        ModelAndView modelAndView = new ModelAndView("index");

        String timeCity = getTime(city);
        modelAndView.addObject("timeCity", timeCity);
        return modelAndView;
    }

    public String getTime(String city) {
        Date date = new Date();

        TimeZone local = TimeZone.getDefault();

        TimeZone citySearch = TimeZone.getTimeZone(city);

        Long result = date.getTime() + (citySearch.getRawOffset() - local.getRawOffset());

        date.setTime(result);

        return String.valueOf(date);
    }


}
