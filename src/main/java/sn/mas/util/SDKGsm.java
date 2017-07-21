package sn.mas.util;

import sn.mas.result.QueryUSSDResult;
import sn.mas.result.SendUSSDResult;
import sn.mas.sms.Gateway;

public class SDKGsm {
	
	//public static final String host = "127.0.0.1";
	public static final String host = "10.7.0.30";
	
	public static final int port = 80;
	public static final String authUser = "admin";
	public static final String authPassword = "admin";
	
	public static final int tempsAttenteEnvoieCredit = 2 * 60 * 1000; // en milliseconde la doc recommende 8seconde
    public static final int tempsAttenteEnvoieSMS = 1 * 60 * 1000; // en milliseconde la doc recommende 8seconde
    
    /**
     * 
     * @param text
     * @param ports
     * @param command
     * @return
     * @throws Exception
     */
    public static SendUSSDResult createSendUSSD(String text, int[] ports, String command) throws Exception {
    	Gateway gateway = new Gateway(host, port, authUser, authPassword);
    	return gateway.sendUSSD(text, ports, command);
    }
    
    /**
     * 
     * @param ports
     * @return
     */
    public static QueryUSSDResult getQueryUSSD(int[] ports) {
    	Gateway gateway = new Gateway(host, port, authUser, authPassword);
    	return gateway.queryUSSD(ports);
    }
}
