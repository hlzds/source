package client;
import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
public class GuiClient extends JFrame {
	private JButton sendButton;
	private JButton closeButton;
	private JTextArea receiveMsg;
	private JTextArea sendMsg;
	private GuiClient frm;
	private Choice clientList;

	public GuiClient(String str) {
		super(str);
		frm = this;

		clientList = new Choice();
		this.clientList.add("ALL                                     ");

		receiveMsg = new JTextArea(5, 20);
		sendMsg = new JTextArea(5, 20);

		this.sendMsg.setText("");
		this.receiveMsg.setText("");

		this.sendButton = new JButton("发送");
		this.closeButton = new JButton("退出");

		JPanel p = new JPanel();
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();

		frm.add(p);
		p.setLayout(new BorderLayout());
		p.add(p1, "North");
		p.add(p2, "Center");
		p2.add(p4);
		p.add(p3, "South");

		p1.setLayout(new FlowLayout());
		p1.add(clientList);

		p4.setLayout(new GridLayout(2, 1));

		p4.add(receiveMsg);

		p4.add(sendMsg);

		p3.setLayout(new FlowLayout());
		p3.add(this.sendButton);
		p3.add(this.closeButton);

		frm.add(p);

	}

	// showFrame()方法的作用是将窗口显示在指定的地方

	public void showFrame() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = frm.getSize();
		frm.setLocation((screenSize.width - frameSize.width - 200) / 2,
				(screenSize.height - frameSize.height - 100) / 2);
		frm.pack();
		frm.setVisible(true);
	}

	public void listenSendButton(final Client b) {

		this.sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b.send();
			}
		});
	}

	public void listenCloseButton(final Client b) {

		this.closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b.quit();
			}
		});
	}

	public String getSendMsg() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setSendMsgClear() {
		// TODO Auto-generated method stub
		
	}



	public void addWindowListener(client.WindowAdapter windowAdapter) {
		// TODO Auto-generated method stub
		
	}
}