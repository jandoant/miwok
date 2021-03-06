package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, MediaPlayer.OnCompletionListener, AudioManager.OnAudioFocusChangeListener {

    private ListView listView;
    private ArrayList<Word> words;
    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        audioManager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);

        createData();
        setUpListView();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //only play Audio, when the user is in our app
        releaseMediaPlayer();
    }

    private void setUpListView() {
        listView = (ListView) findViewById(R.id.list_view);
        //ArrayAdapter
        WordAdapter wordAdapter = new WordAdapter(this, words, R.color.category_colors);
        listView.setAdapter(wordAdapter);
        //OnItemClickListener
        listView.setOnItemClickListener(this);
    }

    private void createData() {
        words = new ArrayList<Word>();
        words.add(new Word("red", "weṭeṭṭi", R.drawable.color_red, R.raw.color_red));
        words.add(new Word("green", "chokokki", R.drawable.color_green, R.raw.color_green));
        words.add(new Word("brown", "ṭakaakki", R.drawable.color_brown, R.raw.color_brown));
        words.add(new Word("gray", "ṭopoppi", R.drawable.color_gray, R.raw.color_gray));
        words.add(new Word("black", "kululli", R.drawable.color_black, R.raw.color_black));
        words.add(new Word("white", "kelelli", R.drawable.color_white, R.raw.color_white));
        words.add(new Word("dusty yellow", "ṭopiisә", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        words.add(new Word("mustard yellow", "chiwiiṭә", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        playSound(words.get(position));
    }

    private void playSound(Word word) {
        releaseMediaPlayer();
        mediaPlayer = MediaPlayer.create(this, word.getSoundResourceId());
        mediaPlayer.setOnCompletionListener(this);
        mediaPlayer.start();
    }

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();
            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer = null;

            audioManager.abandonAudioFocus(this);
        }
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        releaseMediaPlayer();
    }

    @Override
    public void onAudioFocusChange(int focusChange) {
        switch (focusChange) {

            case AudioManager.AUDIOFOCUS_GAIN: //Focus wieder zurück erlangt
                mediaPlayer.start();
                break;
            case AudioManager.AUDIOFOCUS_LOSS: //langfristiger Focusverlust
                mediaPlayer.stop();
                releaseMediaPlayer();
                break;
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT: //kurzzeiteiger Focusverlust
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK: //kurzzeitiger Focusverlust und unserer App wird von Focuserlanger erlaubt leiser weiter Audio abzuspielen
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
                break;

        }//Ende switch
    }
}
