package fr.mastersid.meghasli.alienslayer2184.ui.fragment;

import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import fr.mastersid.meghasli.alienslayer2184.sound.Music;
import fr.mastersid.meghasli.alienslayer2184.ui.GameView;
import fr.mastersid.meghasli.alienslayer2184.viewModels.SoloFragmentModel;
import fr.mastersid.meghasli.alienslayer2184.factory.SoloModelFactory;

public class SoloFragment extends Fragment {
    //private FragmentSoloBinding binding;
    Music music;
    GameView gameView;
    boolean soundState;
    boolean musicSound;
    int skinID;
    private int playerScore = 0;

    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        music = new Music(getActivity());
        gameView = new GameView(getActivity(),size.x,size.y);

        return gameView;
        //binding = FragmentSoloBinding.inflate(inflater, container, false);

        //View view = binding.getRoot();

        //return view;

    }

   /* @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;

    }*/

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        String name = SoloFragmentArgs.fromBundle(getArguments()).getPlayerName();
        SoloFragmentModel soloFragmentModel;
        SoloModelFactory soloModelFactory = new SoloModelFactory(getActivity());
        soloFragmentModel = new ViewModelProvider(this,soloModelFactory).get(SoloFragmentModel.class);

        soundState = soloFragmentModel.soloSoundState.getValue();
        musicSound = soloFragmentModel.soloMusicState.getValue();
        skinID = soloFragmentModel.soloSkinID.getValue();
        gameView.setGameOverState(soloFragmentModel.isItGameOver);
        gameView.setPlayerName(name);
        gameView.setSoundState(soundState);
        gameView.setSkinID(skinID);


        soloFragmentModel.isItGameOver.observe(getViewLifecycleOwner(), value ->{
            if(value){
                playerScore = gameView.getScore();
                NavDirections action = SoloFragmentDirections.actionSoloFragmentToSoloGameOverFragment(name,playerScore);
                Navigation.findNavController(view).navigate(action);
            }
        });

        /*Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);*/

        //binding.soloGameView = new GameView(getActivity().getApplicationContext(),size.x,size.y);

    }



    @Override
    public void onResume() {
        super.onResume();
        gameView.resume();
        if(musicSound) {
            music.playGameMusic();
        }
    }
    @Override
    public void onPause() {
        super.onPause();
        gameView.pause();
        if(musicSound) {
            music.pauseGameMusic();
        }
    }
}
