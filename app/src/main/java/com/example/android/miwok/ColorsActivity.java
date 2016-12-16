package com.example.android.miwok;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Word> words;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        //init UI
        listView = (ListView) findViewById(R.id.list_view);
        createData();
        //ArrayAdapter
        WordAdapter wordAdapter = new WordAdapter(this, words);
        listView.setAdapter(wordAdapter);
    }

    private void createData() {
        words = new ArrayList<Word>();
        words.add(new Word("red", "weṭeṭṭi", R.drawable.color_red));
        words.add(new Word("green", "chokokki", R.drawable.color_green));
        words.add(new Word("brown", "ṭakaakki", R.drawable.color_brown));
        words.add(new Word("gray", "ṭopoppi", R.drawable.color_gray));
        words.add(new Word("black", "kululli", R.drawable.color_black));
        words.add(new Word("white", "kelelli", R.drawable.color_white));
        words.add(new Word("dusty yellow", "ṭopiisә", R.drawable.color_dusty_yellow));
        words.add(new Word("mustard yellow", "chiwiiṭә", R.drawable.color_mustard_yellow));
    }
}
