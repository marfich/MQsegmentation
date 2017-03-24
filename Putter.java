package ru.qdts;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.ibm.mq.MQMessage;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;
import com.ibm.mq.constants.MQConstants;

public class Putter {
	
	public static void main(String[] args) {
		
		
		System.out.println("Put tester");
		
		MQQueueManager qMgr = null;
		MQQueue queue = null;
		try{
			qMgr = new MQQueueManager("QM1");
			queue = new MQQueue(qMgr, "Q1", MQConstants.MQOO_OUTPUT, "", "", "");
		
			MQMessage myMessage = new MQMessage();
	 
			String name = readFile("C://source200MB.txt");
			myMessage.writeString(name);
			myMessage.messageFlags = MQConstants.MQMF_SEGMENTATION_ALLOWED;
			
			System.out.println("Message length: " + myMessage.getMessageLength() + " bytes");
			 
			queue.put(myMessage);
		
		}
		catch(Exception ex){
			// System.out.println(ex.getMessage());
		}
	}
	
	static String readFile(String fileName) throws IOException {
	    BufferedReader br = new BufferedReader(new FileReader(fileName));
	    try {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            sb.append("\n");
	            line = br.readLine();
	        }
	        return sb.toString();
	    } finally {
	        br.close();
	    }
	}

}
