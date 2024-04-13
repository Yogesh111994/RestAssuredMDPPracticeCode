package com.wiremok;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;


public class WiremockSetup {

	protected static WireMockServer server;
	
	public static void startWireMockServer() {
		server= new WireMockServer(8085);
		WireMock.configureFor( "localhost", 8085);
		server.start();
	}
	
	public static void stopWireMockServer() {
		server.stop();
	}
}
