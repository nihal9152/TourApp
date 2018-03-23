package com.example.chiranjivrajput.tourapp;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventFragment extends Fragment {

    private MediaPlayer m;
    private MediaPlayer.OnCompletionListener on=new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };
    private AudioManager audi;
    private AudioManager.OnAudioFocusChangeListener afs=new AudioManager.OnAudioFocusChangeListener(){
        @Override
        public void onAudioFocusChange(int i) {
            if((i==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT||i==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK)&&m!= null)
            {
                m.pause();
                m.seekTo(0);
            }
            else if(i==AudioManager.AUDIOFOCUS_LOSS)
            {
                releaseMediaPlayer();
            }
            else if(i==AudioManager.AUDIOFOCUS_GAIN_TRANSIENT)
            {
                m.start();
            }
        }
    };  private void releaseMediaPlayer() {
        if (m != null) {
            m.release();
            m = null;
            audi.abandonAudioFocus(afs);
        }
    }

    public EventFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.data_list, container, false);
        audi=(AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE);
        final ArrayList<data> w= new ArrayList<data>();
        w.add(new data("IDP's Australia Education Fair","Feb 11 SUN at Hilton Hotel, Jaipur",R.drawable.idp,R.raw.idp,3.1f));
        w.add(new data("Achal's Magical Fair","Jan 17 onwards at VT Road Ground, Jaipur",R.drawable.achals,R.raw.achal,3.3f));
        w.add(new data("Rajasthan Internation Film Festival","Jan 20 onwards at INOX, Crystal Palm, Jaipur",R.drawable.filmfestive,R.raw.rajasthan,3.7f));
        w.add(new data("Jaipur Literature Festival","Jan 25 onwards at Hotel Diggi Palace, Rajasthan",R.drawable.literaturefestival,R.raw.jaipur,3.2f));
        w.add(new data("Nishant Tanwar's Jugaad - A Stand Up Special","Jan 25 onwards at Holiday Inn ,Jaipur",R.drawable.juggad,R.raw.juggad,3.5f));
        dataadpter item= new dataadpter(getActivity(),w,R.color.category_eve);
        ListView l=(ListView) rootView.findViewById(R.id.root);
        l.setAdapter(item);
        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releaseMediaPlayer();
                int r= audi.requestAudioFocus(afs,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if(r==AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    m = MediaPlayer.create(getActivity(), w.get(i).showaudioresourceid());
                    m.start();
                    m.setOnCompletionListener(on);
                }     }
        });


        return rootView;
    }
    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }


}
