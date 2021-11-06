package fr.mastersid.meghasli.alienslayer2184.wifiService;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.NetworkInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import fr.mastersid.meghasli.alienslayer2184.ui.fragment.ConnectionFragment;


public class WifiDirectBroadcastReceiver extends BroadcastReceiver {
    private WifiP2pManager manager;
    private WifiP2pManager.Channel channel;
    private ConnectionFragment fragment;
    private Context context;

    public WifiDirectBroadcastReceiver(WifiP2pManager manager, WifiP2pManager.Channel channel,
                                       ConnectionFragment fragment, Context context) {
        this.manager = manager;
        this.channel = channel;
        this.fragment = fragment;
        this.context = context;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION.equals(action)) {

        } else if (WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(action)) {
            if (manager != null) {
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(context, "Connexion to other devices failed",Toast.LENGTH_SHORT).show();
                    return;
                }
                manager.requestPeers(channel, fragment.peerListListener);
            }
        }
        else if(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(action)){
                if(manager != null){
                    NetworkInfo networkInfo = intent.getParcelableExtra(WifiP2pManager.EXTRA_NETWORK_INFO);
                    if(networkInfo.isConnected()){
                        manager.requestConnectionInfo(channel, fragment.connectionInfoListener);
                    }
                    else{
                        fragment.getBinding().connectionStatus.setText("Not connected");
                    }
                }
        }

    }
}
