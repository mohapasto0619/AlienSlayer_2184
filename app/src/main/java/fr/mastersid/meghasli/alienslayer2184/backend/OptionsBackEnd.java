package fr.mastersid.meghasli.alienslayer2184.backend;

public interface OptionsBackEnd {
    void saveSettings(boolean musicStateFav, boolean soundStateFav, int skinIDFav);
    boolean loadMusicState();
    boolean loadSoundState();
    int loadSkinID();

    public static boolean DEFAULT_MUSIC_STATE_VALUE = true;
    public static boolean DEFAULT_SOUND_STATE_VALUE = true;
    public static int DEFAULT_SKIN_ID_VALUE = 1;

}
