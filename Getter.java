package ru.qdts;

import java.io.IOException;
import java.io.PrintWriter;

import com.ibm.mq.MQGetMessageOptions;
import com.ibm.mq.MQMessage;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;
import com.ibm.mq.constants.MQConstants;

public class Getter {

	public static void main(String[] args) {
		
		System.out.println("Get tester");
		
		MQQueueManager qMgr = null;
		MQQueue queue = null;
		try{
			qMgr = new MQQueueManager("QM1");
			queue = new MQQueue(qMgr, "Q1", MQConstants.MQOO_INPUT_EXCLUSIVE, "", "", "");
		
		MQMessage myMessage = new MQMessage();
		 
		MQGetMessageOptions gmo = new MQGetMessageOptions();
		gmo.options = MQConstants.MQGMO_COMPLETE_MSG;

		 
		queue.get(myMessage,gmo);
		System.out.println("Message length: " + myMessage.getMessageLength() + " bytes");
		writeFile("C://result200MB.txt", myMessage.readStringOfCharLength(myMessage.originalLength));
		
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
	
	static void writeFile(String fileName, String value) throws IOException {
		PrintWriter out = new PrintWriter(fileName);
	    try {
	    	out.println(value);
	    }
	    catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	    finally {
			out.close();
		}
	}
	

}
