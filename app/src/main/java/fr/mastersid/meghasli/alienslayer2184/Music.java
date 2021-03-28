package fr.mastersid.meghasli.alienslayer2184;

import android.content.Context;
import android.media.MediaPlayer;

public class Music {
    MediaPlayer menuMusic;
    MediaPlayer gameMusic;
    MediaPlayer optionsAmb;
    MediaPlayer optionsAmb2;
    MediaPlayer scoreAmb;

    public Music(Context context){
        menuMusic = MediaPlayer.create(context, R.raw.menumusic);
        gameMusic = MediaPlayer.create(context, R.raw.gamemusic);
        optionsAmb = MediaPlayer.create(context,R.raw.optionsamb);
        optionsAmb2 = MediaPlayer.create(context,R.raw.optionsamb2);
        scoreAmb = MediaPlayer.create(context,R.raw.scoreamb);

    }

    public void playMenuMusic(){
        menuMusic.start();
    }
    public void stopMenuMusic(){
        menuMusic.stop();
    }
    public void playGameMusic(){
        gameMusic.start();
    }
    public void stopGameMusic(){
        gameMusic.stop();
    }
    public void pauseGameMusic(){
        gameMusic.pause();
    }
    public void playOptionsAmb(){optionsAmb.start();}
    public void stopOptionsAmb(){optionsAmb.stop();}
    public void playOptionsAmb2(){optionsAmb2.start();}
    public void stopOptionsAmb2(){optionsAmb2.stop();}
    public void playScoreAmb(){scoreAmb.start();}
    public void stopScoreAmb(){scoreAmb.stop();}

}
