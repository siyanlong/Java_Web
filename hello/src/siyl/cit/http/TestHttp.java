package siyl.cit.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class TestHttp {

	public static void main(String[] args) {
		try {
			Socket socket = new Socket("localhost", 8080);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			out.println("GET /hello/reg.html HTTP/1.1");
			out.println("Host:localhost");
			out.println("Content-Type:text/html");
			out.println();
			String str = null;
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			while((str = reader.readLine()) != null) {
				System.out.println(str);
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
