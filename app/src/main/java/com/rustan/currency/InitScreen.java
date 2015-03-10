package com.rustan.currency;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class InitScreen extends Activity {

    private static final String URL = "https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5";
    private CurPOJO currency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);
        currency = new CurPOJO();
        new PrefetchDataCurrency().execute();

    }

    // Создаем AsyncTask, который и будет выполнять получение
    // курса валют в потоке
    private class PrefetchDataCurrency extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            // получаем курс UAH в USD с помощью нашего парсера
            JSONArray jsonUAHToUSD = JSONParser.getJSONFromUrl(URL);
            if (jsonUAHToUSD != null) {
                try {
                    JSONObject jobj = jsonUAHToUSD.getJSONObject(2);
                    Log.d("myLog2", jobj.toString());
                    Log.d("myLog2", "ccy=" + jobj.getString("ccy"));
                    currency.setCur_buy(jobj.getDouble("buy"));
                    currency.setCur_sale(jobj.getDouble("sale"));
                    currency.setCur_name(jobj.getString("ccy"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("myLog1", jsonUAHToUSD.toString());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            // создаем новый Intent для перехода на MainActivity
            Intent intent = new Intent(InitScreen.this, MainActivity.class);
            // Добавляем в Intent наш объект
            // обратите внимание, что класс Currency должен реализовывать
            // интерфейс Serializable
            intent.putExtra("currency", currency);

            // запускаем новое Activity c Intent-ом, который хранит наш объект currency
            startActivity(intent);

            // завершаем работу потока
            finish();
        }


    }
}
