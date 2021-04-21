package fr.mastersid.meghasli.alienslayer2184;

import android.Manifest;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pManager;

import androidx.core.app.ActivityCompat;

import java.util.ArrayList;
import java.util.List;

import static android.os.Looper.getMainLooper;

public class MyWifiP2pManager {
    WifiManager mWifi;
    WifiP2pManager manager;
    WifiP2pManager.Channel channel;
    IntentFilter intentFilter;
    Context context;
    //Client client;
    //Server server;

    List<WifiP2pDevice> peers = new ArrayList<WifiP2pDevice>();
    String[] deviceNameArray;
    WifiP2pDevice[] deviceArray;

    public MyWifiP2pManager(Context context) {
        this.context = context;
        mWifi = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        manager = (WifiP2pManager) context.getSystemService(Context.WIFI_P2P_SERVICE);
        channel = manager.initialize(context, getMainLooper(),null);
        intentFilter = new IntentFilter();
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
    }

    public WifiManager getmWifi() {
        return mWifi;
    }

    public WifiP2pManager getManager() {
        return manager;
    }

    public WifiP2pManager.Channel getChannel() {
        return channel;
    }

    public IntentFilter getIntentFilter() {
        return intentFilter;
    }

    public List<WifiP2pDevice> getPeers() {
        return peers;
    }

    public String[] getDeviceNameArray() {
        return deviceNameArray;
    }

    public WifiP2pDevice[] getDeviceArray() {
        return deviceArray;
    }

    public String discoverPeers(){
        final String[] state = new String[1];
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return "Permission Denied";
        }
        manager.discoverPeers(channel, new WifiP2pManager.ActionListener() {
            @Override
            public void onSuccess() {
                state[0] = "Discovery started";
            }

            @Override
            public void onFailure(int reason) {
                state[0] = "Discovery failed";
            }
        });
        return state[0];
    }
}
