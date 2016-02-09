package com.shoikat.radiolemon24;

import java.io.IOException;
import java.net.URL;
import android.os.Build;
import android.os.StrictMode;
import android.widget.TextView;
import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class MainActivity extends Activity implements OnClickListener {

	 private final static String RADIO_STATION_URL = "http://office.mcc.com.bd:8050";

	public MediaPlayer myPlayer;

	public AudioManager audio;

	float currentVolume = 0;
	
	

	Button buttonPlay, buttonMute;

	TextView songInfo;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		buttonPlay = (Button) findViewById(R.id.btnPlay);
		buttonMute = (Button) findViewById(R.id.btnMute);
		

		myPlayer = new MediaPlayer();
		
		
		buttonMute.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				
			  //System.out.println("Helloooo........." + currentVolume);
				if (buttonMute.isPressed() && currentVolume != 0) {
					myPlayer.setVolume(0, 0);
					currentVolume = 0;
					buttonMute.setBackgroundResource(R.drawable.mute);
					
				} else {
					myPlayer.setVolume(1.0f, 1.0f);
					currentVolume = 11;
					buttonMute.setBackgroundResource(R.drawable.loud);
				}
				
				

			}
		});

		buttonPlay.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				if (!myPlayer.isPlaying()) {
					playMedia();
					buttonPlay.setBackgroundResource(R.drawable.pause);
				} else {
					stopMedia();
					buttonPlay.setBackgroundResource(R.drawable.play);
				}
				
				
			}
		});

	}

	

	private void playMedia() {

		try {
			myPlayer.setDataSource(RADIO_STATION_URL);
		} catch (IllegalArgumentException e) {

			e.printStackTrace();
		} catch (SecurityException e) {

			e.printStackTrace();
		} catch (IllegalStateException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		myPlayer.prepareAsync();
		myPlayer.setOnPreparedListener(new OnPreparedListener() {

			public void onPrepared(MediaPlayer mp) {

				myPlayer.setVolume(1.0f, 1.0f);

				myPlayer.start();

				audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

				currentVolume = audio
						.getStreamVolume(AudioManager.STREAM_MUSIC);
				setVolumeControlStream(AudioManager.STREAM_MUSIC);
				//System.out.println("banana..." + currentVolume);

			}
		});
	}

	private void stopMedia() {

		if (myPlayer.isPlaying()) {
			myPlayer.stop();
		}
		
		
		
		
		

	}

	public void onClick(View arg0) {

	}
	
	public boolean isNetworkOnline() {
	    boolean status=false;
	    try{
	        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	        NetworkInfo netInfo = cm.getNetworkInfo(0);
	        if (netInfo != null && netInfo.getState()==NetworkInfo.State.CONNECTED) {
	            status= true;
	        }else {
	            netInfo = cm.getNetworkInfo(1);
	            if(netInfo!=null && netInfo.getState()==NetworkInfo.State.CONNECTED)
	                status= true;
	        }
	    }catch(Exception e){
	        e.printStackTrace();  
	        return false;
	    }
	    return status;

	    }  
	
	
	
	
	
	
}	


