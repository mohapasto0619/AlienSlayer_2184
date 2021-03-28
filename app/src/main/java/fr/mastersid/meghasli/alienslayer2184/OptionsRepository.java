package fr.mastersid.meghasli.alienslayer2184;

public class OptionsRepository {
    OptionsBackEnd optionsBackEnd;
    public OptionsRepository(OptionsBackEnd optionsBackEnd){
        this.optionsBackEnd = optionsBackEnd;
    }

    void saveSettings(boolean musicStateFav, boolean soundStateFav, int skinIDFav){
        optionsBackEnd.saveSettings(musicStateFav,soundStateFav,skinIDFav);
    }

    boolean loadMusicState(){
        return optionsBackEnd.loadMusicState();
    }

    boolean loadSoundState(){
        return optionsBackEnd.loadSoundState();
    }

    int loadSkinID(){
        return optionsBackEnd.loadSkinID();
    }

    static boolean DEFAULT_MUSIC_STATE_VALUE = OptionsBackEnd.DEFAULT_MUSIC_STATE_VALUE;
    static boolean DEFAULT_SOUND_STATE_VALUE = OptionsBackEnd.DEFAULT_SOUND_STATE_VALUE;
    static int DEFAULT_SKIN_ID_VALUE = OptionsBackEnd.DEFAULT_SKIN_ID_VALUE;

}
