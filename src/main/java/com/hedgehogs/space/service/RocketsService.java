package com.hedgehogs.space.service;

import com.hedgehogs.space.entities.Rocket;
import com.hedgehogs.space.repositories.RocketsRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

/**
 * @author natalya_ezhkova@mail.ru
 */
@Service
public class RocketsService {

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
    private RocketsRepository rocketsRepository;
    @Autowired
    public void setRocketsRepository(RocketsRepository rocketsRepository) {
        this.rocketsRepository = rocketsRepository;
    }


    public Rocket findById(Long id) {
        return rocketsRepository.findById(id).get();
    }
    public Rocket findOneByRocketid(String rocketid) {
        return rocketsRepository.findOneByRocketid(rocketid);
    }

    public Rocket save(Rocket rocket) {
        return rocketsRepository.save(rocket);
    }
        public boolean isRocketExist(String rocketid) {
            return rocketsRepository.existsByRocketid(rocketid);
    }

    public List<Rocket> findAll() {
        return (List<Rocket>)rocketsRepository.findAll();
    }

    @PostConstruct
    public void init() throws IOException {
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
            if (rocketsService.isRocketExist(rocketid) == false) {
                rocket.setRocketid(rocketid);
                rocketsService.save(rocket);
            }
        }
            }
}
