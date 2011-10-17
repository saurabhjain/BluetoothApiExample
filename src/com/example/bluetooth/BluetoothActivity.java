package com.example.bluetooth;

/**
 * @author Saurabh
 * This tutorial list you any of your paired bluetooth devices.
 */
import java.util.Set;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.widget.TextView;

public class BluetoothActivity extends Activity {

	TextView out;
	int currentstate;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		out = (TextView) findViewById(R.id.out);

		// Getting the Bluetooth adaptor
		BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
		// out.append("\n Adapter:" + adapter);

		// Checking for Bluetooth support
		if (adapter == null) {
			out.append("\n Bluetooth not supported, Aborting !");
			return;
		}

		currentstate = adapter.getState();
		if (currentstate == 12) {
			out.append("\n Bluetooth is on...");
		} else if (currentstate == 10) {
			out.append("\n Bluetooth is not turned on, turn it on and restart the app.");
			return;
		} else {
			out.append("\n Error occured, restart app after turning on bluetooth.");
			return;
		}

		// Starting the device discovery
		out.append("\n \n Searching for bluetooth devices nearby " + adapter.getName() + "...");
		adapter.startDiscovery();
		out.append("\n \n Finished searching for bluetooth devices...");

		// Listing paired devices
		out.append("\n \n Listing the paired devices with " + adapter.getName() + ":");
		Set<BluetoothDevice> devices = adapter.getBondedDevices();
		for (BluetoothDevice device : devices) {
			out.append("\n \n Found device: " + device);
		}
	}
}