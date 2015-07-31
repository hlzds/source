package client;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GuiClientMain extends JFrame {
	private JTextField address;
	private JTextField port;
	private JTextField userName;
	private JButton loginButton;
	private JButton qutiButton;
	private JFrame clientmainframe;

	public GuiClientMain(String str) {

		super(str);
		clientmainframe = this;
		JLabel JLabel1 = new JLabel("服务器   IP：");
		JLabel JLabel2 = new JLabel("服务器port：");
		JLabel JLabel3 = new JLabel("QQ  用户名：");
		this.loginButton = new JButton("登录");
		this.qutiButton = new JButton("取消");

		this.address = new JTextField(null, 20);
		this.port = new JTextField(null, 20);
		this.userName = new JTextField(null, 20);

		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();
		clientmainframe.setLayout(new GridLayout(4, 1));

		p1.setLayout(new FlowLayout());
		p2.setLayout(new FlowLayout());
		p3.setLayout(new FlowLayout());
		p4.setLayout(new FlowLayout());
		p1.add(JLabel1);
		p1.add(this.address);
		p2.add(JLabel2);
		p2.add(this.port);
		p3.add(JLabel3);
		p3.add(this.userName);
		p4.add(this.loginButton);
		p4.add(this.qutiButton);

		clientmainframe.add(p1);
		clientmainframe.add(p2);
		clientmainframe.add(p3);
		clientmainframe.add(p4);
		clientmainframe.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	// showFrame()方法的作用是将窗口显示在指定的地方
	public void showFrame() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = clientmainframe.getSize();
		clientmainframe.setLocation(
				(screenSize.width - frameSize.width - 200) / 2,
				(screenSize.height - frameSize.height - 100) / 2);
		clientmainframe.pack();
		clientmainframe.setVisible(true);
	}

	public void listenLoginButton(final Client b) {
		this.loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b.login();
			}
		});
	}

	public void listenQuitButton(final Client b) {
		this.qutiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

	public String getAddress() {
		return address.getText();
	}

	public int getPort() {
		String port1 = port.getText();
		int port2 = Integer.parseInt(port1);
		return port2;
	}

	public String getUserName() {
		return userName.getText();
	}
}
