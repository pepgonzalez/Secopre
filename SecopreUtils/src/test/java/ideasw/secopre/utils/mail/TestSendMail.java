package ideasw.secopre.utils.mail;

import javax.mail.MessagingException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSendMail {
	static final Logger LOG = LoggerFactory.getLogger(TestSendMail.class);
	@Test
	public void test() throws MessagingException{
        Mail mail = new Mail();
        mail.setMailFrom("jorge.canoc@gmail.com");
        mail.setMailTo("jorge.canoc@gmail.com");
        mail.setMailSubject("Correo Importante");
        mail.setMailContent("lol");
        mail.setMailTemplate(MailTemplate.HTML);
        mail.getVelocityAttributes().put("title", "Notificacion");
        mail.getVelocityAttributes().put("fullName", "Jorge A. Cano Cuevas");
        mail.getVelocityAttributes().put("message", "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo.");
        mail.getVelocityAttributes().put("URL", "https://www.google.com");

        @SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(
                "utils-config.xml");
        SendMail sender = (SendMail) context
                .getBean("sendMail");
        sender.sendMail(mail);		
	}
}
