package com.kakao.itstudy.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.kakao.itstudy.dto.Member;

public class MemberValidator implements Validator{

	//유효성 검사를 수행할 DTO 설정
	@Override
	public boolean supports(Class<?> clazz) {
		return Member.class.isAssignableFrom(clazz);
	}

	//유효성 검사를 수행할 메서드
	@Override
	public void validate(Object target, Errors errors) {
		//검사할 대상을 원래의 자료형으로 형 변환
		Member member = (Member)target;
		//검사
		if(member.getEmail() == null ||
				member.getEmail().trim().isEmpty()) {
			errors.rejectValue("email", "required");
		}
		if(member.getPw() == null ||
				member.getPw().trim().isEmpty()) {
			errors.rejectValue("pw", "required");
		}	
	}
}
