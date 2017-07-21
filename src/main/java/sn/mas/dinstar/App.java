package sn.mas.dinstar;

import sn.mas.result.QueryUSSDResult;
import sn.mas.util.SDKGsm;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws InterruptedException
    {
         String text = "#116*1*783882740*100*0000#";
//        String text = "#177*769030305*0000*100#";
        int[] ports = { 1 };
        try {
			SDKGsm.createSendUSSD(text, ports, "send");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        Thread.sleep(1000);
        QueryUSSDResult result = SDKGsm.getQueryUSSD(ports);
        System.out.println("Query result code " +result.getErrorCode()+ " " +result.getJsonString());
    }
}
