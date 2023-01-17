package tn.esprit.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.repository.IUserRepository;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;


@Service
public class UserService implements IUserService {
    @Autowired
    IUserRepository IUserRepository;
    @Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String email;

    public boolean verify(String verificationCode) {
        User user = IUserRepository.findByVerificationCode(verificationCode);

        if (user == null) {
            return false;
        } else {
            user.setVerificationCode(null);
            IUserRepository.save(user);
            return true;
        }
    }

    public void sendVerificationEmail(User user, String siteURL)
            throws MessagingException, UnsupportedEncodingException {
        String toAddress = user.getEmail();
        String fromAddress = email;
        String senderName = "WelboTn";
        String subject = "Please verify your registration";
        String content = "Dear [[name]],<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>"
                + "WelboTn.";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", user.getUserName());
        String verifyURL = siteURL + "/verify?code=" + user.getVerificationCode();

        content = content.replace("[[URL]]", verifyURL);

        helper.setText(content, true);

        mailSender.send(message);

    }

    public String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "/auth");
    }
    public void sendChangePasswordEmail(User user, String siteURL)
            throws MessagingException, UnsupportedEncodingException {
        String toAddress = user.getEmail();
        String fromAddress = email;
        String senderName = "WelboTn";
        String subject = "Change password";
        String content = "Dear [[name]],<br>"
                + "Please click the link below to your password:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">CHANGE</a></h3>"
                + "Thank you,<br>"
                + "WelboTn.";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", user.getUserName());
        String changePasswordURL = siteURL + "/changepassword/userId?id=" + user.getId();

        content = content.replace("[[URL]]", changePasswordURL);

        helper.setText(content, true);

        mailSender.send(message);

    }

    @Override
    public User addUser(User user) {
        IUserRepository.save(user);
        return user;
    }

    @Override
    public void deleteUser(Long id) {
        IUserRepository.deleteById(id);
    }

    @Override
    public List<User> getUsers() {

        return (List<User>) IUserRepository.findAll();
    }

    @Override
    public User updateUser( User User) {
        IUserRepository.save(User);
        return User;
    }

    @Override
    public User getUser(Long UserId) {
        return IUserRepository.findById(UserId).get();
    }
}