package com.example.andrey.magnittest.activities;

import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.example.andrey.magnittest.R;

public class OneItemActivity extends AppCompatActivity{
    public static final String ITEM_NUMBER = "itemNumber";
    public static final String ITEM_RATE = "itemRate";
    public static final String TITLE = "Row № ";
    private int itemNumber;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.one_item_activity);
        insertValues();
        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle(TITLE + itemNumber);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    private void insertValues() {
        TextView rowNumber = (TextView) findViewById(R.id.row_number);
        Button btn = (Button) findViewById(R.id.button);
        itemNumber = getIntent().getIntExtra(ITEM_NUMBER, 0);
        double iteRate = getIntent().getDoubleExtra(ITEM_RATE, 0);
        rowNumber.setText(String.valueOf(itemNumber));
        ClipDrawable drawable = (ClipDrawable) btn.getBackground();
        drawable.setLevel((int)(iteRate *10000));
    }


}
