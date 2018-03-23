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
public class RestaurantsFragment extends Fragment {
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


    public RestaurantsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.data_list, container, false);
        audi=(AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE);
        final ArrayList<data> w= new ArrayList<data>();
        w.add(new data("1135 AD","",R.drawable.a1135ad,R.raw.a1135,3.8f));
        w.add(new data("Pink City Restaurant","",R.drawable.pikcityres,R.raw.pinkcity,3.9f));
        w.add(new data("Jal Mahal Restaurant","",R.drawable.jalmahalres,R.raw.jalmahalrestaurant,4.4f));
        w.add(new data("Surabhi Restayrant and Turban Museum","",R.drawable.surbhires,R.raw.surbhirestaurant,4.2f));
        w.add(new data("Suraj Restaurant","",R.drawable.surajres,R.raw.suraj,3.0f));
        dataadpter item= new dataadpter(getActivity(),w,R.color.category_res);
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
