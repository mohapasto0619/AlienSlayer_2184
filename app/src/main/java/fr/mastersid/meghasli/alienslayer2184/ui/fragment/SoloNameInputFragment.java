package fr.mastersid.meghasli.alienslayer2184.ui.fragment;


import android.bluetooth.BluetoothAdapter;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import fr.mastersid.meghasli.alienslayer2184.databinding.FragmentSoloNameInputBinding;

public class SoloNameInputFragment extends Fragment {
    private FragmentSoloNameInputBinding binding;


    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        binding = FragmentSoloNameInputBinding.inflate(inflater, container, false);
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


        binding.storyText.setMovementMethod(new ScrollingMovementMethod());
        binding.startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(binding.editName.getText().toString())){
                    Toast toast = Toast.makeText(getActivity(),"Please enter your name first",Toast.LENGTH_SHORT);
                    toast.show();

                }
                else{
                    NavDirections action = (NavDirections) SoloNameInputFragmentDirections.actionSoloNameInputFragmentToSoloFragment(
                            binding.editName.getText().toString());
                    Navigation.findNavController(view).navigate(action);
                }

            }
        });

    }



}

