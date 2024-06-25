package kr.co.sist.etmarket.service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Duration;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import kr.co.sist.etmarket.dao.UserDao;
import kr.co.sist.etmarket.dto.UserDto;
import kr.co.sist.etmarket.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {
	
	private static final String AUTH_CODE_PREFIX = "verifyCode ";
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	MailService mailService;
	
	@Autowired
	RedisService redisService;
	
	@Value("${spring.mail.auth-code-expiration-millis}")
    private long authCodeExpirationMillis;
	
	
	// 로그인
	public UserDto login(UserDto userDto) {
		// user에서 userLoginId 값 가져옴
		User findUser = userDao.findByUserLoginId(userDto.getUserLoginId());
		
		// 아이디가 존재하는지 확인하고, 비밀번호가 일치하는지 확인
		if (findUser!=null && findUser.getUserPassword().equals(userDto.getUserPassword())) {
			//System.out.println("LoginService에서 유저정보 출력:"+findUser);
			return UserDto.fromEntity(findUser);
		}

        return null;
    }
	
	// 아이디 찾기
	public UserDto findLoginId(String userEmail, String userPhone){
		User findLoginId = userDao.findByUserPhoneAndUserEmail(userPhone, userEmail);
		
		if(findLoginId!=null) {
			//System.out.println("로그인 아이디 서비스에서 출력:"+findLoginId);			
			return UserDto.fromEntity(findLoginId);
		}			
		return null;
	}
	
	// 비밀번호 찾기
	public UserDto findPassword(String userLoginId, String userEmail) {
		User findPassword=userDao.findByUserLoginIdAndUserEmail(userLoginId, userEmail);
		
		if(findPassword!=null) {
			//System.out.println("LoginService 출력 - "+findPassword);
			return UserDto.fromEntity(findPassword);
		}	
		return null;
	}
	
	// 인증코드 생성
//	private String createCode() {
//		int lenth=6;
//		try {
//            Random random = SecureRandom.getInstanceStrong();
//            StringBuilder builder = new StringBuilder();
//            for (int i = 0; i < lenth; i++) {
//                builder.append(random.nextInt(10));
//            }
//            return builder.toString();
//        } catch (NoSuchAlgorithmException e) {
//            log.debug("MemberService.createCode() exception occur");
//            throw new BusinessLogicException(ExceptionCode.NO_SUCH_ALGORITHM);
//        }
//	}
	
	// 인증코드 이메일로 발송 - redis에 인증코드 저장
	public void sendCodeToEmail(String userEmail) {
        String title = "Travel with me 이메일 인증 번호";
        //String verifyCode = this.createCode();
        Random random=new Random();
        String verifyCode=String.valueOf(random.nextInt(888888)+111111);
        
        mailService.sendEmail(userEmail, title, verifyCode);
        
        // 이메일 인증 요청 시 인증 번호 Redis에 저장 ( key = "verifyCode " + Email / value = verifyCode )
        redisService.setValues(AUTH_CODE_PREFIX + userEmail,
        		verifyCode, Duration.ofMillis(this.authCodeExpirationMillis));
    }
	
	// 인증코드 검증
	public boolean verifiedCode(String userEmail, String verifyCode) {
        String redisAuthCode = redisService.getValues(AUTH_CODE_PREFIX + userEmail);
        boolean authResult = redisService.checkExistsValue(redisAuthCode) && redisAuthCode.equals(verifyCode);

        return redisAuthCode.equals(verifyCode);
    }

}
