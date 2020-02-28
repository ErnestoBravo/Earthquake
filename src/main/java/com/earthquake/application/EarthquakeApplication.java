package com.earthquake.application;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.earthquake.application.entity.TerremotoEntity;
import com.earthquake.application.service.TerremotoService;

@SpringBootApplication
@RestController
public class EarthquakeApplication {

	@Autowired
	private TerremotoService terremotoService;
	
	public static void main(String[] args) {
		SpringApplication.run(EarthquakeApplication.class, args);
	}

	@RequestMapping(value = "/getTerremotoFecha")
	public String getTerremotoFecha(@RequestParam(name = "fechaIni") String fechaIni, @RequestParam(name = "fechaTerm") String fechaTerm) {
		final String uri = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=" + fechaIni + "&endtime=" + fechaTerm;

		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(uri, String.class);

		return result;
	}

	@RequestMapping(value = "/getTerremotoMag")
	public String getTerremotoMag(@RequestParam(name = "minMagnitude") Double minmagnitude, @RequestParam(name = "maxMagnitude") Double maxmagnitude) {
		final String uri = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&minmagnitude=" + minmagnitude + "&maxmagnitude=" + maxmagnitude;

		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(uri, String.class);

		return result;

	}

	@RequestMapping(value = "/guardaTerremotoFecha")
	public String guardaTerremotoFecha() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String fechaIni = sdf.format(new Date());
		String fechaTerm = sdf.format(new Date());
		
		final String uri = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=" + fechaIni+"T00:00:00" + "&endtime=" + fechaTerm+"T23:59:59";

		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(uri, String.class);

		try {
			JSONObject obj = new JSONObject(result);

			List<JSONObject> list = new ArrayList<JSONObject>();
			JSONArray array = obj.getJSONArray("features");
			for (int i = 0; i < array.length(); i++) {
				list.add(array.getJSONObject(i));
			}

			List<JSONObject> wqawq = new ArrayList<JSONObject>();
			for (int i = 0; i < list.size(); i++) {
				wqawq.add(list.get(i).getJSONObject("properties"));
			}

			List<TerremotoEntity> listt = new ArrayList<TerremotoEntity>();

			for (int i = 0; i < wqawq.size(); i++) {
				TerremotoEntity terremoto = new TerremotoEntity();
				terremoto.setMag(wqawq.get(i).get("mag").toString());
				terremoto.setPlace(wqawq.get(i).get("place").toString());
				terremoto.setTime(wqawq.get(i).get("time").toString());
				terremoto.setType(wqawq.get(i).get("type").toString());
				terremoto.setTz(wqawq.get(i).get("tz").toString());
				terremoto.setUpdated(wqawq.get(i).get("updated").toString());

				listt.add(terremoto);
			}
			
			terremotoService.guardarTerremoto(listt);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
}
