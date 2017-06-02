package com.example.andrey.magnittest.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.andrey.magnittest.R;
import com.example.andrey.magnittest.adapters.RowsAdapter;
import com.example.andrey.magnittest.entities.Item;
import com.example.andrey.magnittest.managers.ItemManager;
import com.example.andrey.magnittest.storage.OnListItemClickListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EditItemActivity extends AppCompatActivity{
    private ItemManager itemManager;
    private static final String TITLE = "Settings";
    public static final String EDITED = "edited";
    private EditText rowEdit;
    private EditText rateEdit;
    private RecyclerView recyclerView;
    private RowsAdapter adapter;
    private MainActivity mainActivity = new MainActivity();
//    private HashMap<Integer, Double>itemsMap = new HashMap<>();

    private OnListItemClickListener clickListener = (v, position) -> {

    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_activity);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle(TITLE);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        itemManager = new ItemManager(this);
        Button ok_btn = (Button) findViewById(R.id.ok_btn);
        rowEdit = (EditText) findViewById(R.id.row_edit);
        rateEdit = (EditText) findViewById(R.id.rate_edit);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        ok_btn.setOnClickListener(v -> onOkBtn());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RowsAdapter(itemManager.getNewItems(), clickListener);
        recyclerView.setAdapter(adapter);
    }

    private void onOkBtn() {
        int number = 0;
        double rate = 0;
        boolean isRateNumber = false;
        boolean isNumberInt = false;
        try {
            number = Integer.parseInt(rowEdit.getText().toString());
            isNumberInt = true;
        }catch (NumberFormatException e){
            isNumberInt = false;
            rowEdit.setText("Введите число");
        }
        try {
            rate = Double.parseDouble(rateEdit.getText().toString());
            isRateNumber = true;
        }catch (NumberFormatException e){
            isRateNumber = false;
            rateEdit.setText("Введите число");
        }
        if(isNumberInt && isRateNumber) {
            Item item = new Item(number, rate);
            itemManager.addOrUpdateNewItem(item);
            itemManager.addOrUpdate(item);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onBackPressed() {
        putOnBackPressed();
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public boolean onSupportNavigateUp(){
        putOnBackPressed();
        return true;
    }


    private void putOnBackPressed(){
        Intent intent = getIntent();
        intent.putExtra(EDITED, true);
        setResult(RESULT_OK, intent);
        finish();
    }
}
