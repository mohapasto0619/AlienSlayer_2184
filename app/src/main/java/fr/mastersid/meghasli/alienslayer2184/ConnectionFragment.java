package fr.mastersid.meghasli.alienslayer2184;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import fr.mastersid.meghasli.alienslayer2184.databinding.FragmentConnectionBinding;

import static android.os.Looper.getMainLooper;

public class ConnectionFragment extends Fragment {
    private FragmentConnectionBinding binding;
    MyWifiP2pManager myWifiP2pManager;
    BroadcastReceiver receiver;
    Socket socket;
    ServerClass serverClass;
    ClientClass clientClass;
    boolean isHost;

    volatile MutableLiveData<String> reader;



    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentConnectionBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myWifiP2pManager = new MyWifiP2pManager(getActivity());
        receiver = new WifiDirectBroadcastReceiver(myWifiP2pManager.getManager(), myWifiP2pManager.getChannel(),
                this, getActivity());
        reader = new MutableLiveData<>();
        enableWifi();

        binding.searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    binding.connectionStatus.setText("Permission denied");
                    return;
                }
                myWifiP2pManager.getManager().discoverPeers(myWifiP2pManager.getChannel(), new WifiP2pManager.ActionListener() {
                    @Override
                    public void onSuccess() {
                        binding.connectionStatus.setText("Discovery started");
                    }

                    @Override
                    public void onFailure(int reason) {
                        binding.connectionStatus.setText("Discovery failed");
                    }
                });
            }
        });

        binding.devicesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final WifiP2pDevice device = myWifiP2pManager.getDeviceArray()[i];
                WifiP2pConfig config = new WifiP2pConfig();
                config.deviceAddress = device.deviceAddress;
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    binding.connectionStatus.setText("Permission denied");
                    return;
                }
                myWifiP2pManager.getManager().connect(myWifiP2pManager.getChannel(), config, new WifiP2pManager.ActionListener() {
                    @Override
                    public void onSuccess() {
                        binding.connectionStatus.setText("Connected : " + device.deviceAddress);
                    }

                    @Override
                    public void onFailure(int reason) {
                        binding.connectionStatus.setText("Connetion failed");
                    }
                });
            }
        });

        binding.buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExecutorService executor = Executors.newSingleThreadExecutor();
                String msg = binding.editTextMsg.getText().toString();
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        if(msg != null && isHost){
                            serverClass.write(msg.getBytes());
                        }
                        else if(msg != null && !isHost){
                            clientClass.write(msg.getBytes());
                        }
                    }
                });
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void enableWifi() {
        Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
        if (!myWifiP2pManager.getmWifi().isWifiEnabled()){
            wifiSettingsResultLauncher.launch(intent);
            Toast.makeText(getActivity(),"Please Turn ON Wifi",Toast.LENGTH_SHORT).show();
        }
    }

    ActivityResultLauncher<Intent> wifiSettingsResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // There are no request codes
                        Intent data = result.getData();
                    }
                }
            });

    WifiP2pManager.PeerListListener peerListListener = new WifiP2pManager.PeerListListener(){
        @Override
        public void onPeersAvailable(WifiP2pDeviceList wifiP2pDeviceList) {
            if(!wifiP2pDeviceList.equals(myWifiP2pManager.getPeers())){
                myWifiP2pManager.getPeers().clear();
                myWifiP2pManager.getPeers().addAll(wifiP2pDeviceList.getDeviceList());
                myWifiP2pManager.deviceNameArray = new String[wifiP2pDeviceList.getDeviceList().size()];
                myWifiP2pManager.deviceArray = new WifiP2pDevice[wifiP2pDeviceList.getDeviceList().size()];

                int i = 0;
                for(WifiP2pDevice device : wifiP2pDeviceList.getDeviceList()){
                    myWifiP2pManager.getDeviceNameArray()[i] = device.deviceName;
                    myWifiP2pManager.getDeviceArray()[i] = device;
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                        android.R.layout.simple_list_item_1, myWifiP2pManager.getDeviceNameArray());
                binding.devicesList.setAdapter(arrayAdapter);

                if(myWifiP2pManager.getPeers().size() == 0){
                    binding.connectionStatus.setText("No Device");
                    return;
                }
            }
        }
    };

    WifiP2pManager.ConnectionInfoListener connectionInfoListener = new WifiP2pManager.ConnectionInfoListener(){
        @Override
        public void onConnectionInfoAvailable(WifiP2pInfo wifiP2pInfo) {
            final InetAddress groupOwnerAddress = wifiP2pInfo.groupOwnerAddress;
            if(wifiP2pInfo.groupFormed && wifiP2pInfo.isGroupOwner){
                binding.connectionStatus.setText("Host");
                isHost = true;
                serverClass = new ServerClass();
                serverClass.start();

            }
            else if(wifiP2pInfo.groupFormed){
                binding.connectionStatus.setText("Client");
                isHost = false;
                clientClass = new ClientClass(groupOwnerAddress);
                clientClass.start();
            }
        }
    };

    public FragmentConnectionBinding getBinding() {
        return binding;
    }

    @Override
    public void onResume() {
        super.onResume();
        requireActivity().registerReceiver(receiver,myWifiP2pManager.getIntentFilter());
    }

    @Override
    public void onPause() {
        super.onPause();
        requireActivity().unregisterReceiver(receiver);
    }

    public class ServerClass extends Thread{
        ServerSocket serverSocket;
        private InputStream inputStream;
        private OutputStream outputStream;

        public void write(byte[] bytes){
            try {
                outputStream.write(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            //super.run();
            try {
                serverSocket = new ServerSocket(8888);
                socket = serverSocket.accept();
                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ExecutorService executor = Executors.newSingleThreadExecutor();
            Handler handler = new Handler(Looper.getMainLooper());

            executor.execute(new Runnable(){
                @Override
                public void run() {
                    byte[] buffer = new byte[1024];
                    int bytes;

                    while(socket != null){
                        try {
                            bytes = inputStream.read(buffer);
                            if(bytes > 0){
                                int finalBytes = bytes;
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        String tempMsg = new String(buffer,0,finalBytes);
                                        binding.messageView.setText(tempMsg);
                                    }
                                });
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }

    public class ClientClass extends Thread {
        String hostAdd;
        private InputStream inputStream;
        private OutputStream outputStream;

        public ClientClass(InetAddress hostAddress){
            hostAdd = hostAddress.getHostAddress();
            socket = new Socket();
        }

        public void write(byte[] bytes){
            try {
                outputStream.write(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                socket.connect(new InetSocketAddress(hostAdd, 8888),500);
                outputStream = socket.getOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ExecutorService executor = Executors.newSingleThreadExecutor();
            Handler handler = new Handler(Looper.getMainLooper());

            executor.execute(new Runnable(){
                @Override
                public void run() {
                    byte[] buffer = new byte[1024];
                    int bytes;

                    while(socket != null){
                        try {
                            bytes = inputStream.read(buffer);
                            if(bytes > 0){
                                int finalBytes = bytes;
                                handler.post(new Runnable(){
                                    @Override
                                    public void run() {
                                        String tempMsg = new String(buffer,0,finalBytes);
                                        binding.messageView.setText(tempMsg);
                                    }
                                });
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }

}
