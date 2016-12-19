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

public class FamilyMembersActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, MediaPlayer.OnCompletionListener, AudioManager.OnAudioFocusChangeListener {

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

    private void setUpListView() {
        //init UI
        listView = (ListView) findViewById(R.id.list_view);
        //ArrayAdapter
        WordAdapter wordAdapter = new WordAdapter(this, words, R.color.category_family);
        listView.setAdapter(wordAdapter);
        //OnItemClickListener
        listView.setOnItemClickListener(this);
    }

    private void createData() {

        words = new ArrayList<Word>();

        words.add(new Word("father", "әpә", R.drawable.family_father, R.raw.family_father));
        words.add(new Word("mother", "әṭa", R.drawable.family_mother, R.raw.family_mother));
        words.add(new Word("son", "angsi", R.drawable.family_son, R.raw.family_son));
        words.add(new Word("daughter", "tune", R.drawable.family_daughter, R.raw.family_daughter));
        words.add(new Word("older brother", "taachi", R.drawable.family_older_brother, R.raw.family_older_brother));
        words.add(new Word("younger brother", "chalitti", R.drawable.family_younger_brother, R.raw.family_younger_brother));
        words.add(new Word("older sister", "teṭe", R.drawable.family_older_sister, R.raw.family_older_sister));
        words.add(new Word("younger sister", "kolliti", R.drawable.family_younger_sister, R.raw.family_younger_sister));
        words.add(new Word("grandmother", "ama", R.drawable.family_grandmother, R.raw.family_grandmother));
        words.add(new Word("grandfather", "paapa", R.drawable.family_grandfather, R.raw.family_grandfather));

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        playSound(words.get(position));
    }

    @Override
    protected void onStop() {
        super.onStop();
        //only play Audio, when the user is in our app
        releaseMediaPlayer();
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
