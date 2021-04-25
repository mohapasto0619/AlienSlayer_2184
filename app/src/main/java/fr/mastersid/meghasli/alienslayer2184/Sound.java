package fr.mastersid.meghasli.alienslayer2184;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;

public class Sound {
    SoundPool gameSound;
    SoundPool gameSound2;
    SoundPool gameSound3;
    AudioAttributes audioAttributes;
    private int lazerSoundID;
    private int lazerSound2ID;
    private int explosionID;

    public Sound(Context context){
        audioAttributes = new AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .setUsage(AudioAttributes.USAGE_GAME).build();
        gameSound = new SoundPool.Builder().setAudioAttributes(audioAttributes).build();
        gameSound2 = new SoundPool.Builder().setAudioAttributes(audioAttributes).build();
        gameSound3 = new SoundPool.Builder().setAudioAttributes(audioAttributes).build();
        lazerSoundID = gameSound.load(context, R.raw.laser,0 );
        explosionID = gameSound2.load(context,R.raw.explosion,0);
        lazerSound2ID = gameSound3.load(context,R.raw.laser1,0);

    }

    public void playLazerSound(){
        gameSound.play(lazerSoundID,1,1,0,0,1);
    }

    public void playLazer2Sound(){
        gameSound3.play(lazerSound2ID,1,1,0,0,1);
    }

    public void playExplosion(){gameSound2.play(explosionID,1,1,0,0,1);}


    public void stopLazerSound(){
        gameSound.stop(lazerSoundID);
    }
}
