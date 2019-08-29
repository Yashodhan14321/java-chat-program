import java.net.*;
import java.io.*;
class SendAndRecieve implements Runnable
{
	int clientport=30, serverport=40;
	DatagramSocket ds;
	Thread receiveThread;
	SendAndRecieve() throws Exception
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
				System.out.println("CLIENT2 SAYS:- "+x);
			}
			catch(Exception e)
			{

			}
		}
	}
	public static void main(String args[]) throws Exception
	{
		SendAndRecieve sr = new SendAndRecieve();	
	}
}