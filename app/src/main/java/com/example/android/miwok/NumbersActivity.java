package com.example.android.miwok;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<Word> words;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        createData();

        //init UI
        listView = (ListView) findViewById(R.id.list_view);

        //ArrayAdapter
        WordAdapter wordAdapter = new WordAdapter(this, words);
        listView.setAdapter(wordAdapter);
    }

    private void createData() {
        words = new ArrayList<Word>();
        words.add(new Word("one", "lutti"));
        words.add(new Word("one", "lutti"));
    }
}
