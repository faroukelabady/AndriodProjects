/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.solutions.protocols;

import java.io.IOException;

/**
 * 
 * @author farouk a ConnctionProctocol interface defines the methods to be used
 *         to connect to the server and send & recieve data from it
 */

public interface ConnectionProtocol {
	boolean closeConnection();

	boolean isClose();

	boolean isOpen();

	void openConnection(String hostname, int port) throws IOException;

	boolean sendData(String data);

	void recieveData() throws IOException;
}
