package com.hedgehogs.space.controllers;


import com.hedgehogs.space.entities.Launch;
import com.hedgehogs.space.service.LaunchesService;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author natalya_ezhkova@mail.ru
 */
@Controller
@RequestMapping("/launches")
public class LaunchesController {
    private LaunchesService launchesService;
    @Autowired
    public void setLaunchesService(LaunchesService launchesService) {
        this.launchesService = launchesService;
    }


    //localhost:8189/space/launches/showlaunches

   @GetMapping("/showlaunches")
   public String showAllLaunches(Model model) {
                Launch launch = new Launch();
                model.addAttribute("launch", launch);
             model.addAttribute("launches", launchesService.findAll());
              return "launches";
        }


   // localhost:8189/space/launches/showlinks
    @GetMapping("/showlinks")
    public String showAllLinks(Model model) {
        Launch launch = new Launch();
        model.addAttribute("launch", launch);
        model.addAttribute("launches", launchesService.findAll());
        return "links";
    }

    }

