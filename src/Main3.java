import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Main3 {

	public static void main(String[] args) throws UnknownHostException, IOException {
		// Considerar que o número de generais falando a verdade deve ser 3f+1 para executar o algoritmo;
		ArrayList<Contato> contatosNG = new ArrayList<Contato>();
		ServerSocket serversocket;
		ServerSocket askingsocket;
		Socket conexao;
		Socket asker;
		Socket answer;
		Socket s1, s2;
		PrintWriter askpw;
		String resposta[] = null;
		int attacc = 0;
		int protecc = 0;
		int total = 0;
		String message[] = null;
		Boolean quemFala = false;
		
		Contato c2 = new Contato("localhost", 6782);
		Contato c0 = new Contato("localhost", 6781);
		
		contatosNG.add(c2);
		contatosNG.add(c0);
		
		System.out.println("Iniciou");
			// Receber uma mensagem do general comandante e verificar com os outros contatos?
			while(message == null) {
				serversocket = new ServerSocket(6783); // Qual porta eu uso e como decido qual porta?
				conexao = serversocket.accept();
				System.out.println("Nova conexão com o cliente " + conexao.getInetAddress().getHostAddress());
				
				try {
					Scanner scn = new Scanner(conexao.getInputStream());
					message = scn.nextLine().split(";");
					System.out.println(message[0]);
					quemFala = Boolean.valueOf(message[1]);
					Thread.currentThread().sleep(5000);
				}catch(Exception e) {
					System.out.println("Não foi possível receber a mensagem");
				}
				//Será que esse while funciona? Não vejo como posso testar sozinho. PS: Agora eu consegui :D mas deu pau :c
				serversocket.close();
			}
			
			// A partir daqui eu verifico se a mensagem que eu recebi é a mesma das dos outros?
			// Criar uma lista de objetos Contato para cada general e fazer a verificação em cada um?
			Integer respostas = 0;
			
			
			askingsocket = new ServerSocket(6783);
			System.out.println("Aguardando respostas");
			while(respostas < contatosNG.size()) {
				if(quemFala) {
						for(Contato contato : contatosNG) {
							asker = new Socket(contato.getIp(), contato.getPorta());
							askpw = new PrintWriter(asker.getOutputStream(), true);
							askpw.println(message[0] + ";" + quemFala.toString());
							quemFala = false;
							asker.close();
						}
					}else {
				
				answer = askingsocket.accept();
				System.out.println("Nova resposta do cliente " + answer.getInetAddress().getHostAddress());
				try {
					Scanner scn = new Scanner(answer.getInputStream());
					resposta = scn.nextLine().split(";");
					System.out.println("mAS"+resposta[0]);
					if(resposta[0].equals("Atacar")) {
						attacc++;
					}else if(resposta[0].equals("Recuar")) {
						protecc++;
					}
					respostas++;
				}catch(Exception e) {
					System.out.println("Não foi possível receber a mensagem");
				}
			}
		}
			
			
			
			System.out.println(attacc + ";" + protecc);
			// Se sim, como faço o algoritmo considerando a equação n >= 3f+1?
		
		
	}

}
