import java.io.BufferedReader;

public class ReadThread extends Thread {
	// 从socket管道中进行读操作，打印到控制台
	private BufferedReader br;

	public ReadThread(BufferedReader br) {
		this.br = br;// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		try {
			while (true) {
				String str = br.readLine();
				if ("".equals(str) || str == null)
					continue;
				System.out.println(str);
			}
		} catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
	}

}
