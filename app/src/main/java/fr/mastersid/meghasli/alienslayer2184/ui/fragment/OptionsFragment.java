package fr.mastersid.meghasli.alienslayer2184.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import fr.mastersid.meghasli.alienslayer2184.sound.Music;
import fr.mastersid.meghasli.alienslayer2184.viewModels.OptionsFragmentModel;
import fr.mastersid.meghasli.alienslayer2184.factory.OptionsModelFactory;
import fr.mastersid.meghasli.alienslayer2184.R;
import fr.mastersid.meghasli.alienslayer2184.databinding.FragmentOptionsBinding;

public class OptionsFragment extends Fragment {
    private FragmentOptionsBinding binding;
    private Music music;

    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        binding = FragmentOptionsBinding.inflate(inflater, container, false);
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

        //OptionsFragmentModel optionsFragmentModel = new ViewModelProvider(this).get(OptionsFragmentModel.class);
        OptionsFragmentModel optionsFragmentModel ;
        OptionsModelFactory optionsModelFactory = new OptionsModelFactory(getActivity());
        optionsFragmentModel = new ViewModelProvider(this, optionsModelFactory).get(OptionsFragmentModel.class);
        binding.confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                optionsFragmentModel.saveOptionsSettings();
                Navigation.findNavController(view).navigate(R.id.action_optionsFragment_to_menuFragment);
            }
        });

        binding.switchMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.switchMusic.isChecked()){
                    optionsFragmentModel.turnOnGameMusic();
                }
                else {
                    optionsFragmentModel.turnOffGameMusic();
                }
            }
        });

        binding.switchSon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.switchSon.isChecked()){
                    optionsFragmentModel.turnOnGameSound();
                }
                else {
                    optionsFragmentModel.turnOffGameSound();
                }
            }
        });

        binding.rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                optionsFragmentModel.changeSkinRight();
            }
        });

        binding.leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                optionsFragmentModel.changeSkinLeft();
            }
        });

        optionsFragmentModel.gameMusicState.observe(getViewLifecycleOwner(), value ->{
            if(value){
                binding.switchMusic.setChecked(true);
            }
            else {
                binding.switchMusic.setChecked(false);
            }
        });

        optionsFragmentModel.gameSoundState.observe(getViewLifecycleOwner(), value ->{
            if(value){
                binding.switchSon.setChecked(true);
            }
            else {
                binding.switchSon.setChecked(false);
            }
        });

        optionsFragmentModel.skinID.observe(getViewLifecycleOwner(), value ->{
            if (value == 1){
                binding.skinImageView.setImageResource(R.drawable.playership2);
            }
            else if(value == 2){
                binding.skinImageView.setImageResource(R.drawable.playership1);
            }
            else if(value == 3){
                binding.skinImageView.setImageResource(R.drawable.playership3);
            }
            else if(value == 4){
                binding.skinImageView.setImageResource(R.drawable.playership4);
            }
            else if(value == 5){
                binding.skinImageView.setImageResource(R.drawable.playership6);
            }

            else if(value == 6){
                binding.skinImageView.setImageResource(R.drawable.xwings_n);
            }

        });

    }

    @Override
    public void onResume() {
        super.onResume();
        music.playOptionsAmb();
        music.playOptionsAmb2();
    }
    @Override
    public void onPause() {
        super.onPause();
        music.stopOptionsAmb();
        music.stopOptionsAmb2();
    }
}
