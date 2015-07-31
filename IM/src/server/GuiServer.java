package server;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class GuiServer extends Frame {
	// 写三个组件
	private JTextArea text = new JTextArea("h", 10, 40);
	private JButton startButton = new JButton("开始");
	private JButton closeButton = new JButton("结束");

	// 写了三个容器
	private JPanel p1 = new JPanel();
	private JPanel p2 = new JPanel();
	@SuppressWarnings("unused")
	private JPanel p3 = new JPanel();

	public GuiServer() {
		super("某某服务器");
		p1.add(startButton);
		p1.add(closeButton);
		p2.add(text);
		this.add(p2, BorderLayout.NORTH);
		this.add(p1, BorderLayout.CENTER);
		this.pack();
		this.setVisible(true);
	}

	public void startButton(final Server a) {
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				text.setText("服务器开始工作了……");
				a.startServer();

			}

		});
	}

	public void endButtonListener(final Server b){
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				b.endServer();
			}
			
			
		});
	}
}