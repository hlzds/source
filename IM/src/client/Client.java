package client;

import help.ClientInfo;
import help.MessageBean;

import java.awt.Window;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Iterator;

@SuppressWarnings("unused")
public class Client {
	private GuiClient gui;
	private String userName;
	private Socket client;
	private OutputStream ous;
	private GuiClientMain guiMain;
	private ClientInfo clientList;

	public static void main(String[] args) {
		Socket client;
		try {
			client = new Socket("localhost", 8000);
			// 获取一个字节输出流
			OutputStream os = client.getOutputStream();
			// 将字节输出流转化为字符输出流
			OutputStreamWriter osw = new OutputStreamWriter(os);
			// 将字符输出流转化为缓存流
			DataOutputStream dos = new DataOutputStream(os);
			// BufferedWriter bw=new BufferedWriter(osw);
			// bw.write("我是张三，老师好");
			dos.writeUTF("我是张三，老师好");
			dos.close();
			// bw.close();
			client.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void login() {
		try {

			// 首先要获取登录框中的内容，即用户输入的数据

			String severIP = guiMain.getAddress();
			int serverPort = guiMain.getPort();
			userName = guiMain.getUserName();

			System.out.println("severIP=" + severIP);
			System.out.println("serverPort=" + serverPort);
			System.out.println("userName=" + userName);
			client = new Socket(severIP, serverPort);

			System.out.println("与服务器连接成功。。。。。。。。");

			ous = client.getOutputStream();
			DataOutputStream dataOut = new DataOutputStream(ous);
			dataOut.writeUTF("IP为" + severIP + " ;  Port为" + serverPort
					+ "的人在联系你。");
			dataOut.writeUTF("用户名是：" + userName);
			// 这个IP前面带一个斜杆
			// String clientIp=client.getLocalAddress().toString();
			// 下面代码将斜杆去掉。

			String clientIp = client.getLocalAddress().toString().substring(1);
			int clientPort = client.getLocalPort();
			System.out.println("我的IP是：" + clientIp);
			System.out.println("我的port是：" + clientPort);
			// 登录界面退出
			guiMain.dispose();
			// 聊天界面显示
			// 调用send按钮监听方法，那么点击send按钮就会跳到send()方法
			gui.listenSendButton(this);
			// 点击close按钮就会跳到quit()方法
			gui.listenCloseButton(this);
			gui.setTitle(userName);
			gui.showFrame();
			gui.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					quit();
				}
			});

			// new Thread(new ReadThread()).start();

		} catch (Exception e) {
			System.out.println(e);
			System.out.println("连接有误，请检查服务器是否打开，或者服务器IP地址与端口号是否有误！");
		}
	}

	public void send() {
		System.out.println("send");
		try {
			String msg = gui.getSendMsg();
			System.out.println("msg==" + msg);
			ous = client.getOutputStream();
			ous = client.getOutputStream();
			DataOutputStream dataOut = new DataOutputStream(ous);
			dataOut.writeUTF(userName + "说：" + msg);
			gui.setSendMsgClear();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void quit() {
		// TODO Auto-generated method stub

	}

	class AcceptMessageThread implements Runnable {
		private Socket clientSocket;

		public void run() {
			try {
				InputStream ins = clientSocket.getInputStream();
				DataInputStream dis = new DataInputStream(ins);
				while (true) {
					String str = dis.readUTF();
					int index1 = str.indexOf("/", 0);
					if (index1 != -1) {
						System.out.println("来自客户端的消息：" + str);
						String head = str.substring(0, index1);
						if ("NEW".equals(head)) {
							MessageBean msgBean = new MessageBean();
							msgBean = msgBean.openString(str);
							String clientIP = msgBean.getIp();
							String clientPort = msgBean.getPort();
							String userName = msgBean.getUserName();
							ClientInfo clientInfo = new ClientInfo();
							clientInfo.setClientSocket(clientSocket);
							clientInfo.setClientIP(clientIP);
							clientInfo.setClientPort(clientPort);
							clientInfo.setUserName(userName);

							/*
							 * clientList.add(clientInfo);
							 * sendnewToOld1(clientInfo,str);
							 * sengOldToNew(clientInfo);
							 */

						}
					}
					MessageBean msgBean = new MessageBean();
					msgBean = msgBean.openString(str);
					String head = msgBean.getHead();
					if ("NEW".equals(head)) {
						System.out.println("这是老客户端，接收来自新客户的资料：" + str);
					} else if ("OLD".equals(head)) {
						System.out.println("这是新客户端，接收来自老客户的资料：" + str);
					}
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public void sendOldToNew(ClientInfo clientInfo) {
		@SuppressWarnings("rawtypes")
		Iterator iter = clientList.iterator();
		while (iter.hasNext()) {
			ClientInfo clientInfoOld = (ClientInfo) iter.next();
			if (clientInfo != clientInfoOld) {
				try {
					OutputStream ops = clientInfo.getClientSocket()
							.getOutputStream();
					DataOutputStream dos = new DataOutputStream(ops);
					String head = "OLD";
					String ip = clientInfoOld.getClientIP();
					String port = clientInfoOld.getClientPort();
					String userName = clientInfoOld.getUserName();
					MessageBean msgBean = new MessageBean();
					msgBean.setHead(head);
					msgBean.setIp(ip);
					msgBean.setPort(port);
					msgBean.setUserName(userName);
					String str = msgBean.buildString(msgBean);
					System.out.println("str=" + str);
					dos.writeUTF(str);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
