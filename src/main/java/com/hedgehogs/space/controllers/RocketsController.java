package com.hedgehogs.space.controllers;
import com.hedgehogs.space.entities.Rocket;
import com.hedgehogs.space.service.RocketsService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;


/**
 * @author natalya_ezhkova@mail.ru
 */
@Controller
@RequestMapping("/rockets")
public class RocketsController {
    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private RocketsService rocketsService;

    @Autowired
    public void setRocketsService(RocketsService rocketsService) {
        this.rocketsService = rocketsService;
    }

    //http://localhost:8189/space/rockets/
    @GetMapping("/")
    @ResponseBody
    public void getAllRockets() throws IOException {
        String request = "https://api.spacexdata.com/v3/rockets";
        String result = restTemplate.getForObject(request, String.class);
        // System.out.println(result);
        String rocketid;
        JSONArray ja = new JSONArray(result);
        for (int i = 0; i < ja.length(); i++) {
            //Получаем каждый объект из массива по отдельности
            JSONObject my_obj = ja.getJSONObject(i);
            //Получаем необходимые значения из объектов, по ключевым словам
            rocketid = my_obj.getString("rocket_id");
            System.out.println("rocket_id " + rocketid);

            Rocket rocket = new Rocket();
            if (rocketsService.isRocketExist(rocketid)==false) {
            rocket.setRocketid(rocketid);
            rocketsService.save(rocket);
        }}}
            @GetMapping("/list")
            public String showAllRockets(Model model) {
                Rocket rocket = new Rocket();
                model.addAttribute("rocket", rocket);
                model.addAttribute("rockets", rocketsService.findAll());
                return "rockets";
 }}

