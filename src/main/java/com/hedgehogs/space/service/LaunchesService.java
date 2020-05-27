package com.hedgehogs.space.service;

import com.hedgehogs.space.entities.Launch;
import com.hedgehogs.space.entities.Rocket;
import com.hedgehogs.space.repositories.LaunchesRepository;
import jdk.internal.org.xml.sax.SAXException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

/**
 * @author natalya_ezhkova@mail.ru
 */
@Service
public class LaunchesService {
    private LaunchesRepository launchesRepository;
    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private LaunchesService launchesService;

    @Autowired
    public void setLaunchesService(LaunchesService launchesService) {
        this.launchesService = launchesService;
    }


    @Autowired
    public void setLaunchesRepository(LaunchesRepository launchesRepository) {
        this.launchesRepository = launchesRepository;
    }


    public Launch findOneByRocktid(String rocktid) {
        return launchesRepository.findOneByRocktid(rocktid);
    }

    public Launch save(Launch launch) {
        return launchesRepository.save(launch);
    }
    public boolean isLaunchExist(String rocktid) {
        return launchesRepository.existsByRocktid(rocktid);
    }

    public List<Launch> findAll() {
        return (List<Launch>)launchesRepository.findAll();
    }
    @PostConstruct
    public void init() throws IOException{
        String request = "https://api.spacexdata.com/v3/launches";
        String result = restTemplate.getForObject(request, String.class);
        // System.out.println(result);
        String launchyear;
        String missionname;
        Object mppatch;
        Object rockid;
        String rocktid;

        JSONArray ja = new JSONArray(result);
        for (int i = 0; i < ja.length(); i++) {
            //Получаем каждый объект из массива по отдельности
            JSONObject my_obj = ja.getJSONObject(i);
            launchyear = my_obj.getString("launch_year");
            missionname = my_obj.getString("mission_name");
            JSONObject links = (JSONObject) my_obj.get("links");
            mppatch = links.get("mission_patch");
            String patch = mppatch.toString();
            //формирует строку из всех ссылок
            String linksline = links.toString();
            // System.out.println("- mission_patch: " + links.get("mission_patch"));
            String linesWithoutNull = linksline.replaceAll("null", "нет ссылки");
            String linesTranslElem1 = linesWithoutNull.replaceAll("\"mission_patch_small\"", "маленькое изображение нашивки ");
            String linesTranslElem2 = linesTranslElem1.replaceAll("\"mission_patch\"", "изображение нашивки ");
            String linesTranslElem3 = linesTranslElem2.replaceAll("\"video_link\"", "ссылка на видео ");
            String linesTranslElem4 = linesTranslElem3.replaceAll("\"flickr_images\"", "изображения на Flickr ");
            String linesTranslElem5 = linesTranslElem4.replaceAll("\"reddit_recovery\"", "Reddit Recovery ");
            String linesTranslElem6 = linesTranslElem5.replaceAll("\"reddit_media\"", "Reddit Media ");
            String linesTranslElem7 = linesTranslElem6.replaceAll("\"reddit_campaign\"", "Reddit Campaign ");
            String linesTranslElem8 = linesTranslElem7.replaceAll("\"wikipedia\"", "википедия ");
            String linesTranslElem9 = linesTranslElem8.replaceAll("\"reddit_launch\"", "Reddit Launch ");
            String linesTranslElem10 = linesTranslElem9.replaceAll("\"youtube_id\"", "идентификационный номер You Tube ");
            String linesTranslElem11 = linesTranslElem10.replaceAll("\"presskit\"", "пресс-кит ");
            String finalline = linesTranslElem11.replaceAll("\"article_link\"", "ссылки на публикации ");
            finalline = finalline.replaceAll("[\\[\\](){}]","");
            finalline = finalline.replaceAll("\"", "");
            JSONObject rockids = (JSONObject) my_obj.get("rocket");
            rockid = rockids.get("rocket_id");
            rocktid = rockid.toString();
            System.out.println(rocktid);


            Launch launch = new Launch();
            if (launchesService.isLaunchExist(rocktid) == false) {
                launch.setRocktid(rocktid);
                launch.setMissionname(missionname);
                launch.setLaunchyear(launchyear);
                launch.setFinalline(finalline);
                // launch.setFinalline(linksline);
                launchesService.save(launch);
            }
        }
    }
}


