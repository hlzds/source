package server;

import help.ClientInfo;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import client.Client;
public class Server {
	private static Socket clientSocket;
	private static String clientIP;
	private static String clientPort;
	private static String userName;
	private static Object head;
	private static DataInputStream dis;
	ServerSocket serverSocket;

	public Server() {
		try {
			serverSocket = new ServerSocket(8000);
			new Thread(new StartServerThread()).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 下面这个线程类中类，能够完成服务器可以与多个客户端连接，不用线程的话与一个客户端连接后就会结束程序。
	class StartServerThread implements Runnable {
		// Socket clientSocket;
		int count = 0;

		public void run() {
			while (true) {
				try {
					Socket clientSocket = serverSocket.accept();
					InputStream ins = clientSocket.getInputStream();
					DataInputStream dataIn = new DataInputStream(ins);
					String str = dataIn.readUTF();
					System.out.println("这是从客户端传过来的信息：" + str);
					count++;
					System.out.println("这是第" + count + "个连接用户");
					// clientSocket = serverSocket.accept();
					new Thread(new ServerReadThread(clientSocket)).start();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
	}

	// 下面这个线程类中类，能够完成服务器可以可以接受一个客户的多条信息。不用线程的话，服务器只能接受一条语句。
	class ServerReadThread implements Runnable {
		Socket clientSocket;

		public ServerReadThread(Socket clientSocket) {
			this.clientSocket = clientSocket;
		}

		public void run() {
			try {
				InputStream ins = clientSocket.getInputStream();
				DataInputStream dataIn = new DataInputStream(ins);
				while (true) {
					String str = null;
					if (dataIn.available() > 0)
						str = dataIn.readUTF();
					if (str != null)
						System.out.println(str);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		new Server();
		ClientInfo clientInfo=new ClientInfo();
		clientInfo.setClientSocket(clientSocket);
		clientInfo.setClientIP(clientIP);
		clientInfo.setClientPort(clientPort);
		clientInfo.setUserName(userName);
		if("NEW".equals(head)){
			//如果为新对象时，将这个对象放在集合中
			//clientList.add(clientInfo);
			//System.out.println("集合中的个数=="+clientList.size());
			System.out.println("这是"+userName+"---新客户在登录！");
			//如果为新对象时，还得将这个对象发给已在线的老客户。
		}
		String str = null;
		try {
			str = dis.readUTF();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("来自客户端的消息："+str);

		int index1=str.indexOf("/",0);
		int index2=str.indexOf("/",index1+1);
		int index3=str.indexOf("/",index2+1);
		int index4=str.indexOf("/",index3+1);				
		String head=str.substring(0,index1);
		System.out.println("head=="+head);
		String clientIP=str.substring(index1+1,index2);
		System.out.println("clientIP=="+clientIP);
		String clientPort=str.substring(index2+1,index3);
		System.out.println("clientPort=="+clientPort);
		String userName=str.substring(index3+1,index4);
		System.out.println("userName=="+userName);


	}
//开启服务
	public void startServer() {
		try {
			serverSocket = new ServerSocket(8000);
			new Thread(new StartServerThread()).start();
		} catch (IOException e) {
			// TODO: handle exception
		}// TODO Auto-generated method stub

	}
	//关闭服务
	public void endServer(){
		System.exit(0);
	}
}
