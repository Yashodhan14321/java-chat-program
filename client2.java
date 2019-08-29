import java.net.*;
import java.io.*;
class SendAndRecieve1 implements Runnable
{
	int clientport=40, serverport=30;
	DatagramSocket ds;
	Thread receiveThread;
	SendAndRecieve1() throws Exception
	{
		ds = new DatagramSocket(serverport);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		receiveThread = new Thread(this);
		//ds = new DatagramSocket(clientport);
		receiveThread.start();
		while(true)
		{
			try
			{
				String data = br.readLine();
				if(data.equals("EXIT"))
				{
					break;
				}
				DatagramPacket dp = new DatagramPacket(data.getBytes(),data.length(),InetAddress.getLocalHost(),clientport);
				ds.send(dp);
			}
			catch(Exception e)
			{

			}
		}
	}
	public void run()
	{
		byte b[] = new byte[1000];
		while(true)
		{
			try
			{
				DatagramPacket dp = new DatagramPacket(b, b.length);
				ds.receive(dp);
				String x = new String(b,0,dp.getLength());
				System.out.println("CLIENT1 SAYS:- "+x);
			}
			catch(Exception e)
			{

			}
		}
	}
	public static void main(String args[]) throws Exception
	{
		SendAndRecieve1 sr = new SendAndRecieve1();	
	}
}