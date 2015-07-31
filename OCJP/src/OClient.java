import java.io.ObjectOutputStream;
import java.net.Socket;

public class OClient {
	public static void main(String[] args) {
		try {
			// 连接服务器
			Socket socket = new Socket("localhost", 8000);
			Users users = new Users("zhangsan", 39);
			// 传输对象
			ObjectOutputStream oos = new ObjectOutputStream(
					socket.getOutputStream());
			
			oos.writeObject(users);
			oos.flush();
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
	}
}
