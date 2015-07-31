import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	public static void main(String[] args) {
		try {
			Socket socket = new Socket("localhost", 8000);
			System.out.println("我是客户端");
			// 客户端键盘读一行数据发送到服务器。一次读一行。
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));

			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			System.out.println("测试……");
			// 接收服务器端
			BufferedReader br1 = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			new WriteThread(br1, out).start();
			new ReadThread(br).start();

			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
