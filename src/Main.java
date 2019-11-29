import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) throws UnknownHostException, IOException {
		// Considerar que o número de generais falando a verdade deve ser 3f+1 para executar o algoritmo;
		ArrayList<Contato> contatos = new ArrayList<Contato>();
		Socket socket;
		PrintWriter pw;
		String decisao = null;
		int d = 0;
		
		
		Contato c1 = new Contato("localhost", 6781);
		Contato c2 = new Contato("localhost", 6782);
		Contato c3 = new Contato("192.168.1.153", 6783);
		contatos.add(c1);
		contatos.add(c2);
//		contatos.add(c3);
		Boolean quemFala = true;
		
			while (d!=1 && d!=2) {
				decisao = JOptionPane.showInputDialog("Atacar = 1\nRecuar = 2");
				d = Integer.parseInt(decisao);
				if (d!=1 && d!=2) {
					JOptionPane.showMessageDialog(null, "Você não respondeu a pergunta.");
				}else {
					if(d==1)
						decisao="Atacar;";
					else if (d==2)
						decisao="Recuar;";
					System.out.println(decisao + "" + quemFala.toString());
				}
			}
			for(Contato contato: contatos) {
				socket = new Socket(contato.getIp(), contato.getPorta());
				pw = new PrintWriter(socket.getOutputStream(), true);
				pw.println(decisao + "" + quemFala.toString());
				quemFala = false;
				socket.close();
			}
		
		
	}

}
