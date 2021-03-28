package fr.mastersid.meghasli.alienslayer2184;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

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

        optionsFragmentModel.gameMusicState.observe(getViewLifecycleOwner(), value ->{
            if(value){
                binding.switchMusic.setChecked(true);
            }
            else {
                binding.switchMusic.setChecked(false);
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
