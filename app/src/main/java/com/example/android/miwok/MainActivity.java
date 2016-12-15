/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txt_numbers, txt_family_members, txt_colors, txt_phrases;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init UI
        txt_numbers = (TextView) findViewById(R.id.txt_numbers);
        txt_family_members = (TextView) findViewById(R.id.txt_family_members);
        txt_colors = (TextView) findViewById(R.id.txt_colors);
        txt_phrases = (TextView) findViewById(R.id.txt_phrases);

        //apply OnClickListeners
        txt_numbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNumbersList();
            }
        });

        txt_family_members.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFamilyMembersList();
            }
        });

        txt_phrases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPhrasesList();
            }
        });

        txt_colors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openColorsList();
            }
        });
    }

    private void openColorsList() {
        Intent intent = new Intent(this, ColorsActivity.class);
        startActivity(intent);
    }

    private void openPhrasesList() {
        Intent intent = new Intent(this, PhrasesActivity.class);
        startActivity(intent);
    }

    private void openFamilyMembersList() {
        Intent intent = new Intent(this, FamilyMembersActivity.class);
        startActivity(intent);
    }

    private void openNumbersList() {
        Intent intent = new Intent(this, NumbersActivity.class);
        startActivity(intent);
    }
}
