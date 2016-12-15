package com.example.android.miwok;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<String> words;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        createData();

        //init UI
        listView = (ListView) findViewById(R.id.list_view);

        //ArrayAdapter
        ArrayAdapter<String> arrAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, words);
        listView.setAdapter(arrAdapter);
    }

    private void createData() {

        words = new ArrayList<String>();
        words.add("one");
        words.add("two");
        words.add("three");
        words.add("four");
        words.add("five");
        words.add("six");
        words.add("seven");
        words.add("eight");
        words.add("nine");
        words.add("ten");
    }
}
