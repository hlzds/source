package help;
public class MessageBean {
	private String head;
	private String ip;
	private String port;
	private String userName;
	private String sendMsg;

	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getSendMsg() {
		return sendMsg;
	}
	public void setSendMsg(String sendMsg) {
		this.sendMsg = sendMsg;
	}
	//写一个方法组合字符串
	public String buildString(MessageBean msgBean){
		String str=null;
		str=msgBean.getHead()+"/"+msgBean.getIp()+"/"+msgBean.getPort()+"/"+msgBean.getUserName()+"/"+msgBean.getSendMsg();
		return str;
	}
	//写一个方法打开字符串，返回一个字符串的Bean对象
	public MessageBean openString(String str){
		MessageBean msgBean=new MessageBean();
		int index1=str.indexOf("/",0);
		int index2=str.indexOf("/",index1+1);
		int index3=str.indexOf("/",index2+1);
		int index4=str.indexOf("/",index3+1);
		
		String head=str.substring(0,index1);		
		String ip=str.substring(index1+1,index2);		
		String port=str.substring(index2+1,index3);		
		String userName=str.substring(index3+1,index4);
		String sendMsg=str.substring(index4+1);

		msgBean.setHead(head);
		msgBean.setIp(ip);
		msgBean.setPort(port);
		msgBean.setSendMsg(sendMsg);
		msgBean.setUserName(userName);		
		return msgBean;
		
		
	}
	if("NEW".equals(head)){

		MessageBean msgBean=new MessageBean();			
		msgBean=msgBean.openString(str);						
		String clientIP=msgBean.getIp();
		String clientPort=msgBean.getPort();
		String userName=msgBean.getUserName();
		
		ClientInfo clientInfo=new ClientInfo();
		clientInfo.setClientSocket(clientSocket);
		clientInfo.setClientIP(clientIP);
		clientInfo.setClientPort(clientPort);
		clientInfo.setUserName(userName);
		
		
		//如果为新对象时，将这个对象放在集合中
clientList.add(clientInfo);
System.out.println("集合中的个数=="+clientList.size());
System.out.println("这是"+userName+"---新客户在登录！");
		//因为为新对象，还得将这个对象发给已在线的老客户。
		sendNewToOld(clientInfo,msgBean);
		//因为为新对象，还得将老对象发给它。
		sendOldToNew(clientInfo); 
}
