package fr.mastersid.meghasli.alienslayer2184;

public interface OptionsBackEnd {
    void saveSettings(boolean musicStateFav, boolean soundStateFav, int skinIDFav);
    boolean loadMusicState();
    boolean loadSoundState();
    int loadSkinID();

    static boolean DEFAULT_MUSIC_STATE_VALUE = true;
    static boolean DEFAULT_SOUND_STATE_VALUE = true;
    static int DEFAULT_SKIN_ID_VALUE = 1;

}
