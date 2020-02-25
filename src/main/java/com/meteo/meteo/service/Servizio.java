package com.meteo.meteo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meteo.meteo.data.GenericResponse;
import com.meteo.meteo.data.Weather;
import jdk.nashorn.internal.parser.JSONParser;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


@Service
public class Servizio {

    private static final Logger logger = LoggerFactory.getLogger(Servizio.class);
    private final String token = "b4221c34a141ac2934e8e24c22696d91";

    private String apiKey = "b4221c34a141ac2934e8e24c22696d91";

    private static final String WEATHER_URL =
            "http://api.openweathermap.org/data/2.5/weather?q={city}&APPID={key}";


    public void getCity(String citta, String stato, String country) {
        logger.info("getCity");
        String meteo_url = "http://api.openweathermap.org/data/2.5/weather?q={city name},{state},{country code}&appid={your api key}";

        try {
            URI url = new UriTemplate(meteo_url).expand(citta,stato,country, this.apiKey);

            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);

            CloseableHttpResponse response = client.execute(httpPost);

            String json2 = EntityUtils.toString(response.getEntity());

            logger.info(json2);
            client.close();

        } catch (IOException e) {
            logger.error("Eccezione IOException in getCity ", e);
        } catch (ParseException e) {
            logger.error("Eccezione ParseException in getCity ", e);
        }

    }


    public void getCoordinate(String lon, String lat) {
        logger.info("getCoordinate");
        String meteo_url = "http://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={your api key}";

        try {
            URI url = new UriTemplate(meteo_url).expand(lat,lon, this.apiKey);

            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);

            CloseableHttpResponse response = client.execute(httpPost);

            String json2 = EntityUtils.toString(response.getEntity());

            logger.info(json2);
            client.close();

        } catch (IOException e) {
            logger.error("Eccezione IOException in getCoordinate ", e);
        } catch (ParseException e) {
            logger.error("Eccezione ParseException in getCoordinate ", e);
        }

    }

    public void getZipCode(String zipCode, String countryCode) {
        logger.info("getZipCode");
        String meteo_url = "http://api.openweathermap.org/data/2.5/weather?zip={zip code},{country code}&appid={your api key}";

        try {
            URI url = new UriTemplate(meteo_url).expand(zipCode,countryCode, this.apiKey);

            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);

            CloseableHttpResponse response = client.execute(httpPost);

            String json2 = EntityUtils.toString(response.getEntity());

            logger.info(json2);
            client.close();

        } catch (IOException e) {
            logger.error("Eccezione IOException in getZipCode ", e);
        } catch (ParseException e) {
            logger.error("Eccezione ParseException in getZipCode ", e);
        }

    }


    public void get5DaysCity(String citta, String stato, String country) {
        logger.info("get5DaysCity");
        String meteo_url = "http://api.openweathermap.org/data/2.5/forecast?q={city name},{state},{country code}&appid={your api key}";

        try {
            URI url = new UriTemplate(meteo_url).expand(citta,stato,country, this.apiKey);

            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);

            CloseableHttpResponse response = client.execute(httpPost);

            String json2 = EntityUtils.toString(response.getEntity());

            logger.info(json2);
            client.close();

        } catch (IOException e) {
            logger.error("Eccezione IOException in getCity ", e);
        } catch (ParseException e) {
            logger.error("Eccezione ParseException in getCity ", e);
        }

    }


    public void get5DaysCoordinate(String lon, String lat) {
        logger.info("get5DaysCoordinate");
        String meteo_url = "http://api.openweathermap.org/data/2.5/forecast?lat={lat}&lon={lon}&appid={your api key}";

        try {
            URI url = new UriTemplate(meteo_url).expand(lat,lon, this.apiKey);

            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);

            CloseableHttpResponse response = client.execute(httpPost);

            String json2 = EntityUtils.toString(response.getEntity());

            logger.info(json2);
            client.close();

        } catch (IOException e) {
            logger.error("Eccezione IOException in getCoordinate ", e);
        } catch (ParseException e) {
            logger.error("Eccezione ParseException in getCoordinate ", e);
        }

    }

    public void get5DaysZipCode(String zipCode, String countryCode) {
        logger.info("get5DaysZipCode");
        String meteo_url = "http://api.openweathermap.org/data/2.5/forecast?zip={zip code},{country code}&appid={your api key}";

        try {
            URI url = new UriTemplate(meteo_url).expand(zipCode,countryCode, this.apiKey);

            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);

            CloseableHttpResponse response = client.execute(httpPost);

            String json2 = EntityUtils.toString(response.getEntity());

            logger.info(json2);
            client.close();

        } catch (IOException e) {
            logger.error("Eccezione IOException in getZipCode ", e);
        } catch (ParseException e) {
            logger.error("Eccezione ParseException in getZipCode ", e);
        }

    }

    public void city2() {

        try {
            URIBuilder uriBuilder = new URIBuilder();
            uriBuilder.setScheme("http")
                    .setHost("api.openweathermap.org/data/2.5/weather")
                    .addParameter("q", "Milan")
                    .addParameter("appid", apiKey);

            URI uri = uriBuilder.build();

            String city = "Milan";

            URI url = new UriTemplate(WEATHER_URL).expand(city, this.apiKey);


            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);

            CloseableHttpResponse response = client.execute(httpPost);

            String json2 = EntityUtils.toString(response.getEntity());

            logger.info(json2);


            client.close();
        } catch (IOException e) {
            logger.error("Eccezione IOException in informazioni ", e);
        } catch (ParseException e) {
            logger.error("Eccezione ParseException in informazioni ", e);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }


}

