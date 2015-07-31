import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		ServerSocket s;
		try {
			s = new ServerSocket(8000);
			System.out.println("服务器已经开启");
			// 等待访问
			Socket socket = s.accept();
			System.out.println("接收到" + socket.getInetAddress().getHostAddress()
					+ "的访问");
			// 服务端通过socket.getInputStream进行读操作，转换成一次读一行
			BufferedReader br = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			System.out.print("测试……");

			// 从服务器端键盘读一段发给客户端
			BufferedReader br1 = new BufferedReader(new InputStreamReader(
					System.in));
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			new ReadThread(br).start();
			new WriteThread(br1, out).start();

			socket.close();
			s.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.print("errr");
			e.printStackTrace();
		}

	}
}
