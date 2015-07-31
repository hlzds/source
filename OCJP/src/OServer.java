import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class OServer {
	public static void main(String[] args){
		try {
			ServerSocket s=new ServerSocket(8000);
			System.out.println("传输对象服务已经开启……");
			//接收客户端的访问，产生一组流
			Socket socket=s.accept();
			System.out.println("接收到啦");
			//开始读啦
			ObjectInputStream ois=new ObjectInputStream
				socket.getInputStream());
				Users users=(Users)ois.readObject();
				System.out.println(users);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
