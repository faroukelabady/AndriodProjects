package com.solutions.protocols;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import com.solutions.andriod.tcpipapp.MainActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

public class TCPServer extends AsyncTask<String, Void, Void> implements
		ConnectionProtocol {

	Context context;
	
	public TCPServer(Context context) {
		super();
		this.context = context;
	}

	@Override
	public boolean closeConnection() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isClose() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isOpen() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void openConnection(String hostname, int port) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean sendData(String data) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void recieveData() throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	protected Void doInBackground(String... params) {
		String line = "";
		ServerSocket TCPServerSocket = null;
		for (;;) {
			System.out.println("begin data receiving waiting for input.......");
			try {
				// get the next tcp client
				TCPServerSocket = new ServerSocket(5001);
				Socket client = TCPServerSocket.accept();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(client.getInputStream()));

				while (true) {
					System.out.println("Data Received");
					if (client.isClosed()) {
						continue;
					}
					line = reader.readLine();
					System.out.println("process Data" + line);
					
					Toast.makeText(context, "data recieved" + line , Toast.LENGTH_LONG).show();
					// Check for end of data

				}
			} catch (IOException ioe) {
				ioe.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
