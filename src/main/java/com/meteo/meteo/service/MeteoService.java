package com.meteo.meteo.service;

import com.meteo.meteo.data.*;
import com.meteo.meteo.properties.MeteoProperties;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;


@Service
public class MeteoService {
    @Autowired
    private MeteoProperties meteoProperties;

    private static final Logger logger = LoggerFactory.getLogger(MeteoService.class);
    private final String token = "b4221c34a141ac2934e8e24c22696d91";

    private String apiKey = "b4221c34a141ac2934e8e24c22696d91";

    private static final String WEATHER_URL =
            "http://api.openweathermap.org/data/2.5/weather?q={city}&APPID={key}";


    /**
     * METODI UTILI
     **/

    private static JSONObject getObject(String tagName, JSONObject jObj) throws JSONException {
        JSONObject subObj = jObj.getJSONObject(tagName);
        return subObj;
    }

    private static String getString(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getString(tagName);
    }

    private static float getFloat(String tagName, JSONObject jObj) throws JSONException {
        return (float) jObj.getDouble(tagName);
    }

    private static int getInt(String tagName, JSONObject jObj)  {
        try {
            return jObj.getInt(tagName);
        } catch (JSONException e) {
            logger.error("Eccezione JSONException in getInt ", e);
        }
        return 0;
    }

    private static LocalDateTime getDate(String tagName) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(tagName, format);
    }


    public Optional<Weather> createWether(JSONObject myObject) {
        try {
            Location loc = new Location();
            Coordinate co = new Coordinate();
            Weather weather = new Weather();


            JSONArray jArr = myObject.getJSONArray("weather");

            JSONObject mainObj = getObject("main", myObject);
            weather.setHumidity(getInt("humidity", mainObj));
            weather.setPressure(getInt("pressure", mainObj));
            weather.setTempMax(getFloat("temp_max", mainObj));
            weather.setTempMin(getFloat("temp_min", mainObj));
            weather.setTemp(getFloat("temp", mainObj));
            weather.setPressure(getInt("pressure", mainObj));
            weather.setFeelsLike(getFloat("feels_like", mainObj));

            weather.setVisibility(getInt("visibility", myObject));

            loc.setTimezone(getInt("timezone", myObject));
            loc.setCitta(getString("name", myObject));


            JSONObject JSONWeather = jArr.getJSONObject(0);
            weather.setWeatherDescription(getString("description", JSONWeather));


            JSONObject sysObj = getObject("sys", myObject);
            loc.setCountry(getString("country", sysObj));
            weather.setSunset(getInt("sunset", sysObj));
            weather.setSunrise(getInt("sunrise", sysObj));


            JSONObject coordObj = getObject("coord", myObject);
            co.setLat(getFloat("lat", coordObj));
            co.setLon(getFloat("lon", coordObj));


            weather.setCoordinate(co);
            weather.setLocation(loc);

            return Optional.of(weather);
        } catch (JSONException e) {
            logger.error("Eccezione JSONException in getCity ", e);
        }
        return Optional.empty();
    }



    public Optional<Forecast> createWether5Days(JSONObject myObject) {
        try {
            Location loc = new Location();
            Coordinate co = new Coordinate();
            Forecast forecast = new Forecast();


            JSONObject sysObj = getObject("city", myObject);
            loc.setTimezone(getInt("timezone", sysObj));
            loc.setCitta(getString("name", sysObj));
            loc.setCountry(getString("country", sysObj));
            forecast.setSunset(getInt("sunset", sysObj));
            forecast.setSunrise(getInt("sunrise", sysObj));

            JSONObject coordObj = getObject("coord", sysObj);
            co.setLat(getFloat("lat", coordObj));
            co.setLon(getFloat("lon", coordObj));

            JSONArray jArr = myObject.getJSONArray("list");

            ArrayList<ForecastListInfo> listInfo = new ArrayList<>();

            for(int i = 0; i < jArr.length(); i++){

                ForecastListInfo forecastListInfo = new ForecastListInfo();
                JSONObject JSONArray = jArr.getJSONObject(i);

                JSONArray jArrWether = JSONArray.getJSONArray("weather");

                JSONObject JSONWeather = jArrWether.getJSONObject(0);

                forecastListInfo.setWeatherDescription(getString("description", JSONWeather));

                JSONObject mainObj = getObject("main", JSONArray);

                forecastListInfo.setHumidity(getInt("humidity", mainObj));
                forecastListInfo.setPressure(getInt("pressure", mainObj));
                forecastListInfo.setTempMax(getFloat("temp_max", mainObj));
                forecastListInfo.setTempMin(getFloat("temp_min", mainObj));
                forecastListInfo.setTemp(getFloat("temp", mainObj));
                forecastListInfo.setPressure(getInt("pressure", mainObj));
                forecastListInfo.setFeelsLike(getFloat("feels_like", mainObj));

                forecastListInfo.setDtTxt(getDate(JSONArray.getString("dt_txt")));

                listInfo.add(forecastListInfo);
            }

            forecast.setCoordinate(co);
            forecast.setLocation(loc);
            forecast.setListInfo(listInfo);

            return Optional.of(forecast);
        } catch (JSONException e) {
            logger.error("Eccezione JSONException in getCity ", e);
        }
        return Optional.empty();
    }


    /** SERVIZIOOOOO **/


    public Optional<Weather> getCity() {
        logger.info("getCity");
        String meteo_url = "http://api.openweathermap.org/data/2.5/weather?q={city name},{state},{country code}&appid={your api key}";

        try {
            URI url = new UriTemplate(meteo_url).expand(meteoProperties.getCitta(), meteoProperties.getStato()
                    , meteoProperties.getCountry(), this.apiKey);

            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);

            CloseableHttpResponse response = client.execute(httpPost);

            String json2 = EntityUtils.toString(response.getEntity());

            JSONObject myObject = new JSONObject(json2);

            Optional<Weather> weather = createWether(myObject);

            logger.info(json2);
            client.close();

            return weather;
        } catch (IOException e) {
            logger.error("Eccezione IOException in getCity ", e);
        } catch (ParseException e) {
            logger.error("Eccezione ParseException in getCity ", e);
        } catch (JSONException e) {
            logger.error("Eccezione JSONException in getCity ", e);
        }
        return Optional.empty();
    }


    public Optional<Weather> getCoordinate() {
        logger.info("getCoordinate");
        String meteo_url = "http://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={your api key}";
        String result = null;
        try {
            URI url = new UriTemplate(meteo_url).expand(meteoProperties.getLatitudine(), meteoProperties.getLongitudine(), this.apiKey);

            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);

            CloseableHttpResponse response = client.execute(httpPost);

            String json2 = EntityUtils.toString(response.getEntity());

            JSONObject myObject = new JSONObject(json2);

            Optional<Weather> weather = createWether(myObject);

            logger.info(json2);
            client.close();

            return weather;
        } catch (IOException e) {
            logger.error("Eccezione IOException in getCoordinate ", e);
        } catch (ParseException e) {
            logger.error("Eccezione ParseException in getCoordinate ", e);
        } catch (JSONException e) {
            logger.error("Eccezione JSONException in getCity ", e);
        }

        return Optional.empty();
    }

    public Optional<Weather> getZipCode() {
        logger.info("getZipCode");
        String meteo_url = "http://api.openweathermap.org/data/2.5/weather?zip={zip code},{country code}&appid={your api key}";
        String result = null;
        try {
            URI url = new UriTemplate(meteo_url).expand(meteoProperties.getCap(), meteoProperties.getCountry(), this.apiKey);

            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);

            CloseableHttpResponse response = client.execute(httpPost);

            String json2 = EntityUtils.toString(response.getEntity());

            JSONObject myObject = new JSONObject(json2);

            Optional<Weather> weather = createWether(myObject);

            logger.info(json2);
            client.close();

            return weather;

        } catch (IOException e) {
            logger.error("Eccezione IOException in getZipCode ", e);
        } catch (ParseException e) {
            logger.error("Eccezione ParseException in getZipCode ", e);
        } catch (JSONException e) {
            logger.error("Eccezione JSONException in getCity ", e);
        }
        return Optional.empty();
    }


    public Optional<Forecast> get5DaysCity() {
        logger.info("get5DaysCity");
        String meteo_url = "http://api.openweathermap.org/data/2.5/forecast?q={city name},{state},{country code}&appid={your api key}";
        String result = null;
        try {
            URI url = new UriTemplate(meteo_url).expand(meteoProperties.getCitta(), meteoProperties.getStato()
                    , meteoProperties.getCountry(), this.apiKey);

            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);

            CloseableHttpResponse response = client.execute(httpPost);

            String json2 = EntityUtils.toString(response.getEntity());

            JSONObject myObject = new JSONObject(json2);

            Optional<Forecast> forecast = createWether5Days(myObject);

            logger.info(json2);
            client.close();

            return forecast;
        } catch (IOException e) {
            logger.error("Eccezione IOException in getCity ", e);
        } catch (ParseException e) {
            logger.error("Eccezione ParseException in getCity ", e);
        } catch (JSONException e) {
            logger.error("Eccezione JSONException in getCity ", e);
        }
        return Optional.empty();
    }


    public Optional<Forecast> get5DaysCoordinate() {
        logger.info("get5DaysCoordinate");
        String meteo_url = "http://api.openweathermap.org/data/2.5/forecast?lat={lat}&lon={lon}&appid={your api key}";
        String result = null;
        try {
            URI url = new UriTemplate(meteo_url).expand(meteoProperties.getLatitudine(), meteoProperties.getLongitudine(), this.apiKey);

            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);

            CloseableHttpResponse response = client.execute(httpPost);

            String json2 = EntityUtils.toString(response.getEntity());

            JSONObject myObject = new JSONObject(json2);

            Optional<Forecast> forecast = createWether5Days(myObject);

            logger.info(json2);
            client.close();

            return forecast;

        } catch (IOException e) {
            logger.error("Eccezione IOException in getCoordinate ", e);
        } catch (ParseException e) {
            logger.error("Eccezione ParseException in getCoordinate ", e);
        } catch (JSONException e) {
            logger.error("Eccezione JSONException in getCity ", e);
        }
        return Optional.empty();
    }

    public Optional<Forecast> get5DaysZipCode() {
        logger.info("get5DaysZipCode");
        String meteo_url = "http://api.openweathermap.org/data/2.5/forecast?zip={zip code},{country code}&appid={your api key}";
        String result = null;
        try {
            URI url = new UriTemplate(meteo_url).expand(meteoProperties.getCap(), meteoProperties.getCountry(), this.apiKey);

            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);

            CloseableHttpResponse response = client.execute(httpPost);

            String json2 = EntityUtils.toString(response.getEntity());


            JSONObject myObject = new JSONObject(json2);

            Optional<Forecast> forecast = createWether5Days(myObject);

            logger.info(json2);
            client.close();

            return forecast;

        } catch (IOException e) {
            logger.error("Eccezione IOException in getZipCode ", e);
        } catch (ParseException e) {
            logger.error("Eccezione ParseException in getZipCode ", e);
        } catch (JSONException e) {
            logger.error("Eccezione JSONException in getCity ", e);
        }
        return Optional.empty();
    }



}

