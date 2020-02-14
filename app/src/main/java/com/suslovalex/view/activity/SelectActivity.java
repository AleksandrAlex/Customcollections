package com.suslovalex.view.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.suslovalex.customcollections.R;

public class SelectActivity extends AppCompatActivity {

    private Spinner artistSpinner;
    private Spinner genreSpinner;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_artist_activity);

        artistSpinner = findViewById(R.id.spinnerArtist);
        artistSpinner.setPrompt("Sorting by artist");
        genreSpinner = findViewById(R.id.spinnerGenre);
        genreSpinner.setPrompt("Sorting by title");
        recyclerView = findViewById(R.id.select_recycler_view);

        ArrayAdapter<?> artistAdapter = ArrayAdapter.createFromResource(this,R.array.artists,
                android.R.layout.simple_spinner_item);
        artistAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        artistSpinner.setAdapter(artistAdapter);
        artistSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });

        ArrayAdapter<?> genreAdapter = ArrayAdapter.createFromResource(this,
                R.array.genres, android.R.layout.simple_spinner_item);
        genreAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genreSpinner.setAdapter(genreAdapter);
        genreSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
