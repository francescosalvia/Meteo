package com.meteo.meteo.service;

import com.google.common.collect.Iterables;
import com.meteo.meteo.data.*;
import com.meteo.meteo.properties.MeteoProperties;
import com.meteo.meteo.repository.CoordinateRepository;
import com.meteo.meteo.repository.LocationRepository;
import com.meteo.meteo.repository.WeatherRepository;
import com.meteo.meteo.request.MeteoRequest;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class MeteoService {
    @Autowired
    private MeteoProperties meteoProperties;

    private static final Logger logger = LoggerFactory.getLogger(MeteoService.class);
    private final String token = "b4221c34a141ac2934e8e24c22696d91";

    private static final String WEATHER_URL =
            "http://api.openweathermap.org/data/2.5/weather?q={city}&APPID={key}";

    @Autowired
    private WeatherRepository weatherRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private CoordinateRepository coordinateRepository;


    /**
     * METODI UTILI
     **/

    private static JSONObject getObject(String tagName, JSONObject jObj) {
        try {
            JSONObject subObj = jObj.getJSONObject(tagName);
            return subObj;
        } catch (JSONException e) {
            logger.error("Eccezione JSONException in getObject ", e);
        }
        return null;
    }

    private static String getString(String tagName, JSONObject jObj) throws JSONException {
        try {
            return jObj.getString(tagName);
        } catch (JSONException e) {
            logger.error("Eccezione JSONException in getString ", e);
        }
        return null;
    }

    private static float getFloat(String tagName, JSONObject jObj) throws JSONException {
        try {
            return (float) jObj.getDouble(tagName);
        } catch (JSONException e) {
            logger.error("Eccezione JSONException in getFloat ", e);
        }
        return 0;
    }

    private static int getInt(String tagName, JSONObject jObj) {
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

            for (int i = 0; i < jArr.length(); i++) {

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


    public WeatherDb addWeather(Weather weather, String tipo) {
        WeatherDb weatherDb = new WeatherDb();
        weatherDb.setFeelsLike(weather.getFeelsLike());
        weatherDb.setTemp(weather.getTemp());
        weatherDb.setHumidity(weather.getHumidity());
        weatherDb.setPressure(weather.getPressure());
        weatherDb.setTempMax(weather.getTempMax());
        weatherDb.setTempMin(weather.getTempMin());
        weatherDb.setVisibility(weather.getVisibility());
        weatherDb.setSunrise(weather.getSunrise());
        weatherDb.setSunset(weather.getSunset());
        weatherDb.setWeatherDescription(weather.getWeatherDescription());
        weatherDb.setTipoMetodo(tipo);
        weatherDb.setScadenza(LocalDateTime.now().plusMinutes(120));

        return weatherRepository.save(weatherDb);
    }

    public void addCoordinate(Weather weather, int id, String tipo) {
        Coordinate coordinate = new Coordinate();
        coordinate.setLat(weather.getCoordinate().getLat());
        coordinate.setLon(weather.getCoordinate().getLon());
        coordinate.setIdWeather(id);
        coordinate.setTipo(tipo);
        coordinate.setScadenza(LocalDateTime.now().plusMinutes(120));
        coordinateRepository.save(coordinate);
    }

    public void addLocation(Weather weather, int id, String tipo) {
        Location location = new Location();

        location.setTimezone(weather.getLocation().getTimezone());
        location.setCitta(weather.getLocation().getCitta());
        location.setCountry(weather.getLocation().getCountry());
        location.setIdWeather(id);
        location.setTipo(tipo);
        location.setScadenza(LocalDateTime.now().plusMinutes(120));
        locationRepository.save(location);
    }

    public void addLocationZipCode(Weather weather, int id, String tipo,String zipcode) {
        Location location = new Location();

        location.setTimezone(weather.getLocation().getTimezone());
        location.setCitta(weather.getLocation().getCitta());
        location.setCountry(weather.getLocation().getCountry());
        location.setIdWeather(id);
        location.setTipo(tipo);
        location.setScadenza(LocalDateTime.now().plusMinutes(120));
        location.setZipCode(zipcode);
        locationRepository.save(location);
    }


    public Optional<Weather> returnDB(WeatherDb lastElement) {

        Optional<Coordinate> coordinateDB = coordinateRepository.findCoordinateByIdWeather(lastElement.getId());
        Optional<Location> locationDB = locationRepository.findLocationByIdWeather(lastElement.getId());
        if (coordinateDB.isPresent()) {
            Coordinate coordinate = coordinateDB.get();
            if (locationDB.isPresent()) {
                Location location = locationDB.get();

                Weather weatherReturn = new Weather();

                weatherReturn.setVisibility(lastElement.getVisibility());
                weatherReturn.setSunrise(lastElement.getSunrise());
                weatherReturn.setSunset(lastElement.getSunset());
                weatherReturn.setPressure(lastElement.getPressure());
                weatherReturn.setFeelsLike(lastElement.getFeelsLike());
                weatherReturn.setWeatherDescription(lastElement.getWeatherDescription());
                weatherReturn.setTemp(lastElement.getTemp());
                weatherReturn.setTempMax(lastElement.getTempMax());
                weatherReturn.setTempMin(lastElement.getTempMin());
                weatherReturn.setLocation(location);
                weatherReturn.setCoordinate(coordinate);

                return Optional.of(weatherReturn);
            }
        }
        logger.info("Nessun valore trovato");
        return Optional.empty();
    }

    public Optional<Weather> searchDBCity(String tipo, String citta) {

        List<WeatherDb> weatherDb = new ArrayList<>();

        List<Location> listCitta = locationRepository.findLocationByCitta(citta);
        if (listCitta.size() > 0) {
            Location lastCitta = Iterables.getLast(listCitta);
            weatherDb = weatherRepository.findWeatherDbByTipoMetodoAndId(tipo,lastCitta.getIdWeather());
        }

        if (weatherDb.size() > 0) {
            WeatherDb lastElement = Iterables.getLast(weatherDb);

            LocalDateTime ora = LocalDateTime.now();
            long minuts = ChronoUnit.MINUTES.between(ora, lastElement.getScadenza());

            if (minuts > 0) {
                return returnDB(lastElement);
            }
        }
        logger.info("Nessun valore trovato");
        return Optional.empty();
    }

    public Optional<Weather> searchDBCoordinate(String tipo, float latitudine, float longitudine) {

        List<WeatherDb> weatherDb = new ArrayList<>();

        List<Coordinate> listCoordinate = coordinateRepository.findCoordinateByTipoAndLatAndLon(tipo,latitudine,longitudine);
        if (listCoordinate.size() > 0) {
            Coordinate lastCoord = Iterables.getLast(listCoordinate);
            weatherDb = weatherRepository.findWeatherDbByTipoMetodoAndId(tipo,lastCoord.getIdWeather());
        }

        if (weatherDb.size() > 0) {
            WeatherDb lastElement = Iterables.getLast(weatherDb);

            LocalDateTime ora = LocalDateTime.now();
            long minuts = ChronoUnit.MINUTES.between(ora, lastElement.getScadenza());

            if (minuts > 0) {
                return returnDB(lastElement);
            }
        }
        logger.info("Nessun valore trovato");
        return Optional.empty();
    }


    public Optional<Weather> searchDBZipCode(String tipo, String zipcode) {

        List<WeatherDb> weatherDb = new ArrayList<>();

        List<Location> locations = locationRepository.findLocationByTipoAndZipCode(tipo,zipcode);
        if (locations.size() > 0) {
            Location lastZip = Iterables.getLast(locations);
            weatherDb = weatherRepository.findWeatherDbByTipoMetodoAndId(tipo,lastZip.getIdWeather());
        }

        if (weatherDb.size() > 0) {
            WeatherDb lastElement = Iterables.getLast(weatherDb);

            LocalDateTime ora = LocalDateTime.now();
            long minuts = ChronoUnit.MINUTES.between(ora, lastElement.getScadenza());

            if (minuts > 0) {
                return returnDB(lastElement);
            }
        }
        logger.info("Nessun valore trovato");
        return Optional.empty();
    }





    /**
     * SERVIZIOOOOO
     **/


    public Optional<Weather> getCity(MeteoRequest mr) {

        Optional<Weather> weatherSearch = searchDBCity("city", mr.getCitta());

        if (weatherSearch.isPresent()) {
            return weatherSearch;
        }

        logger.info("getCity");
        String meteo_url = meteoProperties.getUrlWeather() + "q={city name},{state},{country code}&appid={your api key}";

        try {
            URI url = new UriTemplate(meteo_url).expand(mr.getCitta(), mr.getStato()
                    , mr.getCountry(), meteoProperties.getApikey());

            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);

            CloseableHttpResponse response = client.execute(httpPost);

            String json2 = EntityUtils.toString(response.getEntity());

            JSONObject myObject = new JSONObject(json2);

            Optional<Weather> weather = createWether(myObject);

            if (weather.isPresent()) {
                WeatherDb weather1 = addWeather(weather.get(), "city");
                addCoordinate(weather.get(), weather1.getId(),"city");
                addLocation(weather.get(), weather1.getId(),"city");
            }

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


    public Optional<Weather> getCoordinate(MeteoRequest mr) {

        Optional<Weather> weatherSearch = searchDBCoordinate("coordinate",mr.getLatitudine(),mr.getLongitudine());

        if (weatherSearch.isPresent()) {
            return weatherSearch;
        }

        logger.info("getCoordinate");
        String meteo_url = meteoProperties.getUrlWeather() + "lat={lat}&lon={lon}&appid={your api key}";
        String result = null;
        try {
            URI url = new UriTemplate(meteo_url).expand(mr.getLatitudine(), mr.getLongitudine(), meteoProperties.getApikey());

            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);

            CloseableHttpResponse response = client.execute(httpPost);

            String json2 = EntityUtils.toString(response.getEntity());

            JSONObject myObject = new JSONObject(json2);

            Optional<Weather> weather = createWether(myObject);

            if (weather.isPresent()) {
                WeatherDb weather1 = addWeather(weather.get(), "coordinate");
                addCoordinate(weather.get(), weather1.getId(),"coordinate");
                addLocation(weather.get(), weather1.getId(),"coordinate");
            }


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

    public Optional<Weather> getZipCode(MeteoRequest mr) {

        Optional<Weather> weatherSearch = searchDBZipCode("zipCode",mr.getCap());

        if (weatherSearch.isPresent()) {
            return weatherSearch;
        }

        logger.info("getZipCode");
        String meteo_url = meteoProperties.getUrlWeather() + "zip={zip code},{country code}&appid={your api key}";
        String result = null;
        try {
            URI url = new UriTemplate(meteo_url).expand(mr.getCap(), mr.getCountry(), meteoProperties.getApikey());

            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);

            CloseableHttpResponse response = client.execute(httpPost);

            String json2 = EntityUtils.toString(response.getEntity());

            JSONObject myObject = new JSONObject(json2);

            Optional<Weather> weather = createWether(myObject);

            if (weather.isPresent()) {
                WeatherDb weather1 = addWeather(weather.get(), "zipCode");
                addCoordinate(weather.get(), weather1.getId(),"zipCode");
                addLocationZipCode(weather.get(), weather1.getId(),"zipCode",mr.getCap());
            }

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


    public Optional<Forecast> get5DaysCity(MeteoRequest mr) {
        logger.info("get5DaysCity");
        String meteo_url = "http://api.openweathermap.org/data/2.5/forecast?q={city name},{state},{country code}&appid={your api key}";
        String result = null;
        try {
            URI url = new UriTemplate(meteo_url).expand(mr.getCitta(), mr.getStato(),
                    mr.getCountry(), meteoProperties.getApikey());

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


    public Optional<Forecast> get5DaysCoordinate(MeteoRequest mr) {
        logger.info("get5DaysCoordinate");
        String meteo_url = "http://api.openweathermap.org/data/2.5/forecast?lat={lat}&lon={lon}&appid={your api key}";
        String result = null;
        try {
            URI url = new UriTemplate(meteo_url).expand(mr.getLatitudine(), mr.getLongitudine(), meteoProperties.getApikey());

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

    public Optional<Forecast> get5DaysZipCode(MeteoRequest mr) {
        logger.info("get5DaysZipCode");
        String meteo_url = "http://api.openweathermap.org/data/2.5/forecast?zip={zip code},{country code}&appid={your api key}";
        String result = null;
        try {
            URI url = new UriTemplate(meteo_url).expand(mr.getCap(), mr.getCountry(), meteoProperties.getApikey());

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

