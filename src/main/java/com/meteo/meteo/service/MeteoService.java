package com.meteo.meteo.service;

import com.meteo.meteo.properties.MeteoProperties;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


@Service
public class MeteoService {

    @Autowired
    private MeteoProperties meteoProperties;

    private static final Logger logger = LoggerFactory.getLogger(MeteoService.class);
    private final String token = "b4221c34a141ac2934e8e24c22696d91";

    private String apiKey = "b4221c34a141ac2934e8e24c22696d91";

    private static final String WEATHER_URL =
            "http://api.openweathermap.org/data/2.5/weather?q={city}&APPID={key}";


    public void getCity() {
        logger.info("getCity");
        String meteo_url = "http://api.openweathermap.org/data/2.5/weather?q={city name},{state},{country code}&appid={your api key}";

        try {
            URI url = new UriTemplate(meteo_url).expand(meteoProperties.getCitta(),meteoProperties.getStato()
                    ,meteoProperties.getCountry(), this.apiKey);

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


    public void getCoordinate() {
        logger.info("getCoordinate");
        String meteo_url = "http://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={your api key}";

        try {
            URI url = new UriTemplate(meteo_url).expand(meteoProperties.getLongitudine(),meteoProperties.getLatitudine(), this.apiKey);

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

    public void getZipCode() {
        logger.info("getZipCode");
        String meteo_url = "http://api.openweathermap.org/data/2.5/weather?zip={zip code},{country code}&appid={your api key}";

        try {
            URI url = new UriTemplate(meteo_url).expand(meteoProperties.getCap(),meteoProperties.getCountry(), this.apiKey);

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


    public void get5DaysCity() {
        logger.info("get5DaysCity");
        String meteo_url = "http://api.openweathermap.org/data/2.5/forecast?q={city name},{state},{country code}&appid={your api key}";

        try {
            URI url = new UriTemplate(meteo_url).expand(meteoProperties.getCitta(),meteoProperties.getStato()
                    ,meteoProperties.getCountry(), this.apiKey);

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


    public void get5DaysCoordinate() {
        logger.info("get5DaysCoordinate");
        String meteo_url = "http://api.openweathermap.org/data/2.5/forecast?lat={lat}&lon={lon}&appid={your api key}";

        try {
            URI url = new UriTemplate(meteo_url).expand(meteoProperties.getLongitudine(),meteoProperties.getLatitudine(), this.apiKey);

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

    public void get5DaysZipCode() {
        logger.info("get5DaysZipCode");
        String meteo_url = "http://api.openweathermap.org/data/2.5/forecast?zip={zip code},{country code}&appid={your api key}";

        try {
            URI url = new UriTemplate(meteo_url).expand(meteoProperties.getCap(),meteoProperties.getCountry(), this.apiKey);

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
            logger.error("Eccezione URISyntaxException in informazioni ", e);
        }
    }


}

