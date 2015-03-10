package com.rustan.currency;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView sale_v = (TextView) findViewById(R.id.Sale_view);
        TextView buy_v = (TextView) findViewById(R.id.buy_view);
        TextView cur_name = (TextView) findViewById(R.id.currency_name);


        CurPOJO curPOJO = (CurPOJO) getIntent().getSerializableExtra("currency");

        sale_v.setText(String.valueOf(curPOJO.getCur_sale()));
        buy_v.setText(String.valueOf(curPOJO.getCur_buy()));
        cur_name.setText(String.valueOf(curPOJO.getCur_name()));
    }
}
