/**
 * 
 */
package com.solutions.protocols;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

import android.os.AsyncTask;

/**
 * @author farouk
 * 
 */
public class TcpConnection extends AsyncTask<String, Void, Void> implements
		ConnectionProtocol {

	// private parameters
	private Socket TCPSocket; // client connection
	private PrintWriter pw;
	String line1 = "";

	// end of parameters

	public TcpConnection() throws IOException {
		// Get a Socket for TCPConnection
		// System.out.println("Connection Open" + hostname + ":" + port);
		// try {
		// TCPSocket = new Socket(hostname, port);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// // create printwriter object and assign the socket output
		// // stream to it using outputstreamwriter class
		// pw = new PrintWriter(
		// new OutputStreamWriter(TCPSocket.getOutputStream()));
		// System.out.println("Connection Open");
	}

	// send data to the server the data more likely will be bytes
	// it returns an integer for
	public boolean sendData(String data) {

		// send the data to the server
		// pw.print(format);
		if (pw == null && TCPSocket != null) {
			try {
				pw = new PrintWriter(new OutputStreamWriter(
						TCPSocket.getOutputStream()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		pw.print(data);
		pw.flush();
		// pw.close();
		// System.out.println("data send");
		return true;

	}

	public boolean closeConnection() {
		try {
			pw.close();
			TCPSocket.close();
			return true;
		} catch (IOException ex) {
			// Logger.getLogger(TCPConnection.class.getName()).log(Level.SEVERE,
			// null, ex);
			return false;
		} catch (NullPointerException npe) {
			return false;
		}
		// return true;
	}

	public void recieveData() throws IOException {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public boolean isClose() {
		// TODO Auto-generated method stub
		if (TCPSocket != null) {
			return TCPSocket.isClosed();
		}
		return true;

	}

	@Override
	public boolean isOpen() {
		// TODO Auto-generated method stub
		if (TCPSocket != null) {
			return TCPSocket.isConnected();
		}
		return true;
	}

	@Override
	public void openConnection(String hostname, int port) throws IOException {
		// TODO Auto-generated method stub
		TCPSocket.connect(new InetSocketAddress(hostname, port));
	}

	@Override
	protected Void doInBackground(String... arg0) {
		// TODO Auto-generated method stub
		System.out.println("Connection Open" + arg0[0] + ":" + arg0[1]);
		try {
			TCPSocket = new Socket(arg0[0], Integer.parseInt(arg0[1]));

			// create printwriter object and assign the socket output
			// stream to it using outputstreamwriter class
			pw = new PrintWriter(new OutputStreamWriter(
					TCPSocket.getOutputStream()));
			System.out.println("Connection Open");

			pw.print(arg0[2]);
			pw.flush();
			pw.close();
			TCPSocket.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	

}
