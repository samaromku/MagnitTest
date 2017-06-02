package com.example.andrey.magnittest.activities;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.andrey.magnittest.R;
import com.example.andrey.magnittest.adapters.RowsAdapter;
import com.example.andrey.magnittest.entities.Item;
import com.example.andrey.magnittest.managers.ItemManager;
import com.example.andrey.magnittest.storage.DataBaseHelper;
import com.example.andrey.magnittest.storage.OnListItemClickListener;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private RecyclerView itemsList;
    private RowsAdapter adapter;
    private ItemManager itemManager;
    public static final String ITEM_NUMBER = "itemNumber";
    public static final String ITEM_RATE = "itemRate";
    public static final String TITLE = "Screen title";
    public static final String EDITED = "edited";
    static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle(TITLE);
        }
        itemManager = new ItemManager(this);
        itemsList = (RecyclerView) findViewById(R.id.recyclerView);
        itemsList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RowsAdapter(itemManager.getAllItems(), clickListener);
        itemsList.setAdapter(adapter);
    }


    private OnListItemClickListener clickListener = (v, position) -> {
        Intent intent = new Intent(this, OneItemActivity.class);
        intent.putExtra(ITEM_NUMBER, itemManager.getAllItems().get(position).getNumber());
        intent.putExtra(ITEM_RATE, itemManager.getAllItems().get(position).getRate());
        startActivity(intent);
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                Intent intent = new Intent(this, EditItemActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.println(requestCode + " " + resultCode + " "+  data);
        if (requestCode == REQUEST_CODE && data != null) {
            if (resultCode == RESULT_OK) {
                boolean isEdited = data.getBooleanExtra(EDITED, false);
                if(isEdited){
                    itemManager.updateItems();
                    adapter.notifyDataSetChanged();
                }
            }
        }
    }
}
