package com.example.recyclerviewdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;

import com.example.recyclerviewdemo.adapters.TuneAdapter;
import com.example.recyclerviewdemo.databinding.ActivityMainBinding;
import com.example.recyclerviewdemo.model.Tune;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<String> TuneNames = new ArrayList<>(Arrays.asList("Beauty and the Beast",
            "Lion King", "Mary Poppins", "Game of Thrones", "Ozark" ));
    List<Integer> TunePics = new ArrayList<>(Arrays.asList(R.drawable.beauty,
            R.drawable.lionking, R.drawable.marypoppins,
            R.drawable.gameofthrones, R.drawable.ozark ));

    List<Tune> TuneList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        ActivityMainBinding mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        LoadModel(); // add tune objects to list
        Log.d("RECYCLERVIEWDEMO", TuneList.size() + " tunes in list");
        TuneAdapter tuneAdapter = new TuneAdapter(TuneList);
        // set up linear layout manager or gird layout manager
        LinearLayoutManager lm = new LinearLayoutManager(this);
        mainBinding.reccyclerViewTunes.setLayoutManager(lm);

        // set up adapter
        mainBinding.reccyclerViewTunes.setAdapter(tuneAdapter);
    }

    private void LoadModel(){
        for (int i = 0; i < TuneNames.size(); i++) {
            Tune tune = new Tune(TuneNames.get(i), TunePics.get(i));
            TuneList.add(tune);
        }
    }
}