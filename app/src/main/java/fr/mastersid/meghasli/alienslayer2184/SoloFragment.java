package fr.mastersid.meghasli.alienslayer2184;

import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;

import fr.mastersid.meghasli.alienslayer2184.databinding.FragmentSoloBinding;

public class SoloFragment extends Fragment {
    //private FragmentSoloBinding binding;
    Music music;
    GameView gameView;

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
        gameView.setPlayerName(name);

        /*Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);*/

        //binding.soloGameView = new GameView(getActivity().getApplicationContext(),size.x,size.y);

    }



    @Override
    public void onResume() {
        super.onResume();
        gameView.resume();
        music.playGameMusic();
    }
    @Override
    public void onPause() {
        super.onPause();
        gameView.pause();
        music.pauseGameMusic();
    }
}
