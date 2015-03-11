package com.rustan.currency;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class JSONParser {

    public static JSONArray getJSONFromUrl(String url)  {
        StringBuilder builder = new StringBuilder();
        // Создаем HTTP клиент, который позволит работать с HTTP запросами
//        HttpClient client = new DefaultHttpClient();
//        // Формируем HTTP запрос методом GET
//        HttpGet httpGet = new HttpGet(url);


        int statusCode = 0;
        try {
//            // выполняем запрос и получаем ответ
//            HttpResponse response = client.execute(httpGet);
            URL murl = new URL(url);
            HttpURLConnection urlConnection = (HttpURLConnection) murl.openConnection();


//            // получаем статус ответа
//            StatusLine statusLine = response.getStatusLine();
//            statusCode = statusLine.getStatusCode();
            statusCode = urlConnection.getResponseCode();
            Log.d("myLog", "status code=" + statusCode);

            // если запрос удачный, то выполняем парсинг данных
            if (statusCode == 200) {
//                HttpEntity entity = response.getEntity();
//                InputStream content = entity.getContent();
                InputStream content = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                String line;
                // получаем JSON строку
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }
            } else {
                Log.e(JSONParser.class.toString(), "Failed to get currency data.");
            }
        } catch (IOException e) {
            Log.e(JSONParser.class.toString(), "Failed status code = " + statusCode);
        }

        JSONArray jsonArray = null;
        try {
            // формируем JSON объект
            jsonArray = new JSONArray(builder.toString());
            Log.d("myLog", builder.toString());
        } catch (JSONException e) {
            Log.e(JSONParser.class.getName(), "Not valid JSON data.");
        }

        // возвращаем сформированный JSON объект
        return jsonArray;
    }
}