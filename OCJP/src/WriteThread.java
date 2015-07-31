import java.io.BufferedReader;
import java.io.PrintWriter;


public class WriteThread extends Thread{
	private BufferedReader br1;
	private PrintWriter out;
	
	public WriteThread(BufferedReader br1, PrintWriter out) {
		super();
		this.br1 = br1;
		this.out = out;
	}

	@Override
	public void run() {
		try {
			while(true){
			String str1=br1.readLine();
			if("".equals(str1)||str1==null)continue;
			out.println(str1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
