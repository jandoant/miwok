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

public class PhrasesActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, MediaPlayer.OnCompletionListener, AudioManager.OnAudioFocusChangeListener {

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
        WordAdapter wordAdapter = new WordAdapter(this, words, R.color.category_phrases);
        listView.setAdapter(wordAdapter);
        //OnItemClickListener
        listView.setOnItemClickListener(this);
    }


    private void createData() {

        words = new ArrayList<>();

        words.add(new Word("Where are you going?", "minto wuksus", R.raw.phrase_where_are_you_going));
        words.add(new Word("What is your name?", "tinnә oyaase'nә", R.raw.phrase_what_is_your_name));
        words.add(new Word("My name is...", "oyaaset...", R.raw.phrase_my_name_is));
        words.add(new Word("How are you feeling?", "michәksәs?", R.raw.phrase_how_are_you_feeling));
        words.add(new Word("I’m feeling good.", "kuchi achit", R.raw.phrase_im_feeling_good));
        words.add(new Word("Are you coming?", "әәnәs'aa?", R.raw.phrase_are_you_coming));
        words.add(new Word("Yes, I’m coming.", "hәә’ әәnәm", R.raw.phrase_yes_im_coming));
        words.add(new Word("I’m coming.", "әәnәm", R.raw.phrase_im_coming));
        words.add(new Word("Let’s go.", "yoowutis", R.raw.phrase_lets_go));
        words.add(new Word("Come here.", "әnni'nem", R.raw.phrase_come_here));
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
