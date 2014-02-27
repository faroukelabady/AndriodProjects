package com.solutions.protocols;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer implements ConnectionProtocol, Runnable {

	// Context context;

	public TCPServer() {
		super();
		// this.context = context;
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
	public void run() {
		// TODO Auto-generated method stub
		String line = "";
		ServerSocket TCPServerSocket = null;
		for (;;) {
			System.out.println("begin data receiving waiting for input.......");
			try {
				// get the next tcp client

				TCPServerSocket = new ServerSocket();
				TCPServerSocket.setReuseAddress(true);
				TCPServerSocket.bind(new InetSocketAddress(7650));
				System.out.println("open on port"
						+ TCPServerSocket.getLocalPort());
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

					// Toast.makeText(context, "data recieved" + line,
					// Toast.LENGTH_LONG).show();
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
