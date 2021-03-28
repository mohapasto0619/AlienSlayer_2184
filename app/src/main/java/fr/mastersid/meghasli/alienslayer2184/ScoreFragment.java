package fr.mastersid.meghasli.alienslayer2184;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import fr.mastersid.meghasli.alienslayer2184.databinding.FragmentMenuBinding;
import fr.mastersid.meghasli.alienslayer2184.databinding.FragmentScoreBinding;

public class ScoreFragment extends Fragment {

    private FragmentScoreBinding binding;
    private Music music;

    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        binding = FragmentScoreBinding.inflate(inflater, container, false);
        music = new Music(getActivity());
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_scoreFragment_to_menuFragment);
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        music.playScoreAmb();
    }
    @Override
    public void onPause() {
        super.onPause();
        music.stopScoreAmb();
    }


}

