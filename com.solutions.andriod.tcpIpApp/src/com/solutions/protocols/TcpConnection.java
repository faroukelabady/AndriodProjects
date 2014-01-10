/**
 * 
 */
package com.solutions.protocols;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author farouk
 * 
 */
public class TcpConnection implements ConnectionProtocol {

	// private parameters
	private Socket TCPSocket; // client connection
	private PrintWriter pw;
	String line1 = "";

	// end of parameters

	public TcpConnection(String hostname, int port) throws IOException {
		// Get a Socket for TCPConnection
		TCPSocket = new Socket(hostname, port);
		// create printwriter object and assign the socket output
		// stream to it using outputstreamwriter class
		pw = new PrintWriter(
				new OutputStreamWriter(TCPSocket.getOutputStream()));
		System.out.println("Connection Open");
	}

	// send data to the server the data more likely will be bytes
	// it returns an integer for
	public boolean sendData(String data) {

		// send the data to the server
		// pw.print(format);
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
		return TCPSocket.isClosed();
	}

	@Override
	public boolean isOpen() {
		// TODO Auto-generated method stub
		return TCPSocket.isConnected();
	}

	@Override
	public void openConnection(String hostname, int port) throws IOException {
		// TODO Auto-generated method stub
		TCPSocket.connect(new InetSocketAddress(hostname, port));
	}

}
