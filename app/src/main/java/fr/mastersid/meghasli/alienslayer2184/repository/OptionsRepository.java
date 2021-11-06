package fr.mastersid.meghasli.alienslayer2184.repository;

import fr.mastersid.meghasli.alienslayer2184.backend.OptionsBackEnd;

public class OptionsRepository {
    OptionsBackEnd optionsBackEnd;
    public OptionsRepository(OptionsBackEnd optionsBackEnd){
        this.optionsBackEnd = optionsBackEnd;
    }

    public void saveSettings(boolean musicStateFav, boolean soundStateFav, int skinIDFav){
        optionsBackEnd.saveSettings(musicStateFav,soundStateFav,skinIDFav);
    }

    public boolean loadMusicState(){
        return optionsBackEnd.loadMusicState();
    }

    public boolean loadSoundState(){
        return optionsBackEnd.loadSoundState();
    }

    public int loadSkinID(){
        return optionsBackEnd.loadSkinID();
    }

    public static boolean DEFAULT_MUSIC_STATE_VALUE = OptionsBackEnd.DEFAULT_MUSIC_STATE_VALUE;
    public static boolean DEFAULT_SOUND_STATE_VALUE = OptionsBackEnd.DEFAULT_SOUND_STATE_VALUE;
    public static int DEFAULT_SKIN_ID_VALUE = OptionsBackEnd.DEFAULT_SKIN_ID_VALUE;

}
