package com.eparking.springboot.app.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

@Service
@EnableAsync
public class SendEmailService {
	
	@Autowired
	private JavaMailSender mail;
	
	public String Body(String to,String nombres, String apellidos,String telefono, String correo, String marca, String modelo, 
			String placa, Date fecha, String hora, int nhoras, String Estado, int tarifa) {
		String pattern = "MM/dd/yyyy";
		DateFormat df = new SimpleDateFormat(pattern);
		String body = "Datos personales del ".concat(to).concat("\n\n")
				.concat("Nombres y Apellidos: ").concat(nombres).concat(" ").concat(apellidos).concat("\n")
				.concat("Telefono: ").concat(telefono).concat("\n")
				.concat("Correo: ").concat(correo).concat("\n\n")
				.concat("Datos del vehiculo").concat("\n\n")
				.concat("Marca: ").concat(marca).concat("\n")
				.concat("Modelo: ").concat(modelo).concat("\n")
				.concat("Placa: ").concat(placa).concat("\n\n")
				.concat("Detalles de la reserva").concat("\n\n")
				.concat("Fecha: ").concat(df.format(fecha)).concat("\n")
				.concat("Hora: ").concat(hora).concat("\n")
				.concat("N°horas: ").concat(Integer.toString(nhoras)).concat("\n")
				.concat("Estado: ").concat(Estado).concat("\n")
				.concat("Costo total: S/").concat(Integer.toString(tarifa*nhoras)).concat("\n");
		return body;
	}
	
	@Async
	public void sendEmail(String to, String body, String topic) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom("smpl3000@gmail.com");
		mailMessage.setTo(to);
		mailMessage.setSubject(topic);
		mailMessage.setText(body);
		mail.send(mailMessage);
	}
	
}
