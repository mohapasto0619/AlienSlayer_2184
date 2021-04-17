package fr.mastersid.meghasli.alienslayer2184;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import fr.mastersid.meghasli.alienslayer2184.databinding.FragmentMenuBinding;

public class MenuFragment extends Fragment {
    private FragmentMenuBinding binding;
    private Music music;

    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        binding = FragmentMenuBinding.inflate(inflater, container, false);
        music = new Music(getActivity().getApplicationContext());
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

        binding.buttonSolo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_menuFragment_to_soloNameInputFragment);
            }
        });

        binding.buttonScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_menuFragment_to_scoreFragment);
            }
        });

        binding.buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_menuFragment_to_optionsFragment);
            }
        });

        binding.buttonMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_menuFragment_to_connectionFragment);
            }
        });

        /*MutableLiveData data = new MutableLiveData();
        data.setValue("yes");
        data.observe(getViewLifecycleOwner(), value ->{

        });*/

    }

    @Override
    public void onResume() {
        super.onResume();
        music.playMenuMusic();
    }
    @Override
    public void onPause() {
        super.onPause();
        music.stopMenuMusic();
    }



}
