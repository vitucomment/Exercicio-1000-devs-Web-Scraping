package br.com.mildevs.WebScraping.util;

import java.util.Properties;
import java.util.Scanner;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EnviaEmail {

	public static void EnviarEmail(String messagem, String email) {
		Properties props = new Properties();

		parametrosDeConexao(props);

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("enviajobs.1000@gmail.com", "ynddyqmqkcvkipor");
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("enviajobs.1000@gmail.com"));

			Address[] toUser = InternetAddress.parse(email);

			message.setRecipients(Message.RecipientType.TO, toUser);
			message.setSubject("Empregos disponíveis");
			message.setText(messagem);
			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	private static void parametrosDeConexao(Properties props) {
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
	}

	public static void enviar(String vagasString) {
		System.out.print("\nTudo pronto, agora informe seu email: ");
		Scanner input = new Scanner(System.in);
		String email = input.nextLine();
		System.out.println();
		EnviarEmail(vagasString, email);
		System.out.println("Olhe seu email :)");
		input.close();
	}
}