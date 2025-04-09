package com.green.attaparunever2.user;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailSendService {
    private final JavaMailSender mailSender;
    LocalDateTime expireTimes = LocalDateTime.now().plusMinutes(10);

    //인증키 생성
    public String getKey(int size) {
        return generateAuthCode(size);
    }

    public String generateAuthCode(int size) {
        SecureRandom random = new SecureRandom();
        StringBuilder buffer = new StringBuilder();
        while(buffer.length() < size) {
            int num = random.nextInt(10);  // 0-9 범위의 난수 생성
            buffer.append(num);
        }
        return buffer.toString();
    }

    //인증메일 보내기(사용자)
    public boolean sendAuthMail(String url, String email, long userId, String authCode) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom("jumoney1012@gmail.com");
            helper.setTo(email);
            helper.setSubject("아따 빠르네 인증 메일 입니다.");
            helper.setText(new StringBuffer()
                    .append("<h1>[이메일 인증]</h1>")
                    .append("<p>아래 링크를 클릭하시면 이메일 인증이 완료됩니다.</p>")
                    .append("<a href='http://112.222.157.156:5222/api" + url)
                    .append(userId)
                    .append("&token=")
                    .append(authCode)
                    .append("' target='_blank'>이메일 인증 확인 (클릭)</a>")
                    .append("</div>")
                    .append("<div class='footer'>")
                    .append("<p>감사합니다.<br>관리자 드림</p>")
                    .append("</div>")
                    .toString(), true);
            mailSender.send(mimeMessage);
            return true;
        } catch (MessagingException e) {
            // 로깅 처리 추가 (개선)
            log.error("이메일 전송 실패: " + e.getMessage());
            return false;
        }
    }

    //유저아이디 메일 보내기
    public boolean sendFindIdMail(String email, String uId) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom("jumoney1012@gmail.com");
            helper.setTo(email);
            helper.setSubject("아따 빠르네 아이디 찾기 메일 입니다.");
            helper.setText(new StringBuffer()
                    .append("<div>")
                    .append("<h1>회원님의 아이디는</h1>")
                    .append("<h3>")
                    .append(uId)
                    .append("</h3>")
                    .append("<h4> 입니다.</h4>")
                    .append("</div>")
                    .append("<div class='footer'>")
                    .append("<p>감사합니다.<br>관리자 드림</p>")
                    .append("</div>")
                    .toString(), true);
            mailSender.send(mimeMessage);
            return true;
        } catch (MessagingException e) {
            // 로깅 처리 추가 (개선)
            log.error("이메일 전송 실패: " + e.getMessage());
            return false;
        }
    }

    //인증메일 보내기(사용자)
    public boolean sendFindPasswordMail(String email, String password) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom("jumoney1012@gmail.com");
            helper.setTo(email);
            helper.setSubject("아따 빠르네 비밀번호 찾기 메일 입니다.");
            helper.setText(new StringBuffer()
                    .append("<div>")
                    .append("<h1>[비밀번호 찾기]</h1>")
                    .append("<p>요청하신 비밀번호는 </p>")
                    .append("<h3>")
                    .append(password)
                    .append("</h3>")
                    .append("<p>입니다.</p>")
                    .append("</div>")
                    .append("<div class='footer'>")
                    .append("<p>감사합니다.<br>관리자 드림</p>")
                    .append("</div>")
                    .toString(), true);
            mailSender.send(mimeMessage);
            return true;
        } catch (MessagingException e) {
            // 로깅 처리 추가 (개선)
            log.error("이메일 전송 실패: " + e.getMessage());
            return false;
        }
    }

    // 관리자 가입 완료시 업장 등록 페이지 메일
    public boolean sendAddStoreMail(String email, long adminId) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom("jumoney1012@gmail.com");
            helper.setTo(email);
            helper.setSubject("아따 빠르네 가입을 환영합니다.");
            helper.setText(new StringBuffer()
                    .append("<h1>[식당 등록]</h1>")
                    .append("<p>아래 링크를 클릭하시면 식당 등록으로 이동 합니다.</p>")
                    .append("<a href='http://112.222.157.156:5222/addstore?adminId=" + adminId)
                    .append("' target='_blank'>식당 등록하기</a>")
                    .append("</div>")
                    .append("<div class='footer'>")
                    .append("<p>감사합니다.<br>관리자 드림</p>")
                    .append("</div>")
                    .toString(), true);
            mailSender.send(mimeMessage);
            return true;
        } catch (MessagingException e) {
            // 로깅 처리 추가 (개선)
            log.error("이메일 전송 실패: " + e.getMessage());
            return false;
        }
    }

    // 관리자 가입 완료시 업장 등록 페이지 메일
    public boolean sendAdminSignUpMail(String email, long adminId, String code) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom("jumoney1012@gmail.com");
            helper.setTo(email);
            helper.setSubject("아따 빠르네 가입을 환영합니다.");
            helper.setText(new StringBuffer()
                    .append("<h1>[관리자 회원가입]</h1>")
                    .append("<p>아래 링크를 클릭하시면 관리자 회원가입으로 이동 합니다.</p>")
                    .append("<a href='https://attaparune.kro.kr:5232/auth/signup?adminId=" + adminId + "&code=" + code)
                    .append("' target='_blank'>관리자 회원 가입</a>")
                    .append("</div>")
                    .append("<div class='footer'>")
                    .append("<p>감사합니다.<br>관리자 드림</p>")
                    .append("</div>")
                    .toString(), true);
            mailSender.send(mimeMessage);
            return true;
        } catch (MessagingException e) {
            // 로깅 처리 추가 (개선)
            log.error("이메일 전송 실패: " + e.getMessage());
            return false;
        }
    }
}
