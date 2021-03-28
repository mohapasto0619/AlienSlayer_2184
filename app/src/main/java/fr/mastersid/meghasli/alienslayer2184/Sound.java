package fr.mastersid.meghasli.alienslayer2184;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;

public class Sound {
    SoundPool gameSound;
    AudioAttributes audioAttributes;
    private int lazerSoundID;

    public Sound(Context context){
        audioAttributes = new AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .setUsage(AudioAttributes.USAGE_GAME).build();
        gameSound = new SoundPool.Builder().setAudioAttributes(audioAttributes).build();
        lazerSoundID = gameSound.load(context, R.raw.laser4,0 );
    }

    public void playLazerSound(){
        gameSound.play(lazerSoundID,1,1,0,0,1);
    }

    public void stopLazerSound(){
        gameSound.stop(lazerSoundID);
    }
}
