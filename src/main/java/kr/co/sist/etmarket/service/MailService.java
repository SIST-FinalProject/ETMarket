package kr.co.sist.etmarket.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MailService {

	private final JavaMailSender javaMailSender;

	// 이메일을 발송하는 메서드 
	public void sendEmail(String userEmail, String title, String text) {
		SimpleMailMessage emailForm = createEmailForm(userEmail, title, text);
		try {
			javaMailSender.send(emailForm);
			log.info("이메일 발송 성공!!");
		} catch (RuntimeException e) {
			log.debug("MailService.sendEmail exception occur toEmail: {}, " + "title: {}, text: {}", userEmail, title,
					text);
			log.info("이메일 발송 실패,,");
			//throw new BusinessLogicException(ExceptionCode.UNABLE_TO_SEND_EMAIL);
		}
	}

	// 발신할 이메일 데이터 세팅
	private SimpleMailMessage createEmailForm(String userEmail, String title, String text) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(userEmail);
		message.setSubject(title);
		message.setText(text);

		return message;
	}

}
