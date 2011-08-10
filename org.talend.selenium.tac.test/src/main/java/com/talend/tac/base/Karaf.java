package com.talend.tac.base;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.sshd.ClientChannel;
import org.apache.sshd.ClientSession;
import org.apache.sshd.SshClient;
import org.apache.sshd.client.future.ConnectFuture;
import org.apache.sshd.common.RuntimeSshException;

public class Karaf {
    private static String host = "localhost";
    private static int port = 8101;
    private static String user = "karaf";
    private static String password = "karaf";

    private static SshClient client = null;
    private static ClientSession session = null;
    private static ClientChannel channel;
	
	private static final InputStream string2Input(String str) {
		return new ByteArrayInputStream(str.getBytes());
	}
	
	private static final String[] arrayAppend(String array[], String append) {
		for(int i=0; i<array.length; i++) {
			array[i] = array[i]+append;
		}
		return array;
	}
	
	public static final String array2String(String array[]){
		String str = "";
		for(String element:array) {
			str = str + element;
		}
		return str;
	}
	
	static final ClientSession connectKaraf(){
        client = SshClient.setUpDefaultClient();
        client.start();
        
        try {
			do {
				ConnectFuture future = client.connect(host, port);
				future.await();
				try {
					session = future.getSession();
				} catch (RuntimeSshException  e) {
					e.printStackTrace();
				}
			} while (session==null);
			session.authPassword(user, password);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return session;
	}
	
	private static final void action(InputStream input, int timeout){
		try {
			channel = session.createChannel("shell");
//      channel.setIn(System.in);
			channel.setIn(input);
			channel.setOut(System.out);
			channel.setErr(System.err);
			channel.open();
			channel.waitFor(ClientChannel.STDOUT_DATA, timeout);
		} catch (Throwable t) {
          t.printStackTrace();
          System.exit(1);
		} finally {
			try {
				client.stop();
			} catch (Throwable t) { }
		}
		System.exit(0);
	}
	
	private static final void doAction(String action, int timeout){		
		action(string2Input(action+"\n"),timeout);
	}
	
	private static final void doActions(String actions[], int timeout){
		action(string2Input(array2String(arrayAppend(actions,"\n"))),timeout);
	}
	
	public static final void doKarafAction(String action, int timeout){
		connectKaraf();
		doAction(action, timeout);
	}
	
	public static final void doKarafActions(String actions[], int timeout){
		connectKaraf();
		doActions(actions, timeout);
	}
}
