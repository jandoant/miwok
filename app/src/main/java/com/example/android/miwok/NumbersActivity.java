package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, MediaPlayer.OnCompletionListener, AudioManager.OnAudioFocusChangeListener {

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
        WordAdapter wordAdapter = new WordAdapter(this, words, R.color.category_numbers);
        listView.setAdapter(wordAdapter);
        //OnItemClickListener
        listView.setOnItemClickListener(this);
    }

    private void createData() {

        words = new ArrayList<>();

        words.add(new Word("one", "lutti", R.drawable.number_one, R.raw.number_one));
        words.add(new Word("two", "otiiko", R.drawable.number_two, R.raw.number_two));
        words.add(new Word("three", "tolookosu", R.drawable.number_three, R.raw.number_three));
        words.add(new Word("four", "oyyisa", R.drawable.number_four, R.raw.number_four));
        words.add(new Word("five", "massokka", R.drawable.number_five, R.raw.number_five));
        words.add(new Word("six", "temmokka", R.drawable.number_six, R.raw.number_six));
        words.add(new Word("seven", "kenekaku", R.drawable.number_seven, R.raw.number_seven));
        words.add(new Word("eight", "kawinta", R.drawable.number_eight, R.raw.number_eight));
        words.add(new Word("nine", "wo’e", R.drawable.number_nine, R.raw.number_nine));
        words.add(new Word("ten", "na’aacha", R.drawable.number_ten, R.raw.number_ten));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        playSound(words.get(position));
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

    private void playSound(Word word) {

        //Request Audio Focus
        int result = audioManager.requestAudioFocus(this, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            releaseMediaPlayer();
            mediaPlayer = MediaPlayer.create(this, word.getSoundResourceId());
            mediaPlayer.setOnCompletionListener(this);
            mediaPlayer.start();
        } else {
            Toast.makeText(this, "Die Datei kann leider nicht agespielt werden", Toast.LENGTH_SHORT).show();
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