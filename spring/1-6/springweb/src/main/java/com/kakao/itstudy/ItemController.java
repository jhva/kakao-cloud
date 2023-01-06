package com.kakao.itstudy;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kakao.itstudy.domain.Command;
import com.kakao.itstudy.dto.ItemDTO;
import com.kakao.itstudy.dto.Member;
import com.kakao.itstudy.service.ItemService;
import com.kakao.itstudy.validation.MemberValidator;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ItemController {
	private final ItemService itemService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		//서비스 메서드 호출
		List<ItemDTO> list = itemService.allItem();
		//결과 저장
		model.addAttribute("list", list);
		return "home";
	}
	
	@RequestMapping(value="detail/{itemid}", method = RequestMethod.GET)
	public String detail(Model model,
			@PathVariable("itemid") int itemid) {
		
		//서비스 메서드 호출
		ItemDTO dto = itemService.getItem(itemid);
		
		//데이터 전달
		model.addAttribute("item", dto);
		
		//출력할 뷰 이름 설정
		return "detail";
	}
	
	//item.xls 요청이 왔을 때 excel이라는 뷰로 출력
	@RequestMapping(value="item.xls", method=RequestMethod.GET)
	public String excel(Model model) {
		
		List<ItemDTO> list = itemService.allItem();
		model.addAttribute("list", list);
		
		return "excel";
	}
	
	//item.pdf 요청이 왔을 때 pdf라는 뷰로 출력
	@RequestMapping(value="item.pdf", method=RequestMethod.GET)
	public String pdf(Model model) {
		
		List<ItemDTO> list = itemService.allItem();
		model.addAttribute("list", list);
		
		return "pdf";
	}
	
	//JSON 요청 처리
	@RequestMapping(value="itemlist.json",
			method=RequestMethod.GET)
	public String jsonReport(Model model) {
		List <ItemDTO> list = itemService.allItem();
		model.addAttribute("list", list);
		
		return "itemlist";
	}
	
	@RequestMapping(value="exception",method=RequestMethod.GET)
	public String input(Model model) {
		return "input";
	}
	
	@RequestMapping(value="exception",method=RequestMethod.POST)
	public String input(Model model, 
			@RequestParam("num1") int num1,
			@RequestParam("num2") int num2) {
		int result = num1 + num2;
		model.addAttribute("result", result);
		return "result";
	}
	
	//예외가 발생했을 때 처리하기 위한 메서드
	@ExceptionHandler(Exception.class)
	public String handleException(Model model, Exception e) {
		model.addAttribute("result", e.getLocalizedMessage());
		return "error/exception";
	}
	
	//@ModelAttribute 가 추가된 메서드는 리턴하는 데이터를 모든 뷰에게 전송
	@ModelAttribute("loginTypes")
	public List<String> loginTypes(){
		List<String> list = new ArrayList<>();
		list.add("일반회원");
		list.add("기업회원");
		list.add("비회원");
		return list;
	}
	
	@RequestMapping(value = "message", method=RequestMethod.GET)
	//유효성 검사에 실패했을 때 이전 입력한 내용을 가지고 loginform으로 가도록 수정
	public String form(@ModelAttribute("member") Member member) {
		return "loginform";
	}
	
	@RequestMapping(value = "message", method=RequestMethod.POST)
	public String submit(@ModelAttribute("member") Member member,
			Member memberInfo, BindingResult result) {
		//유효성 검사 수행
		new MemberValidator().validate(memberInfo, result);
		if(result.hasErrors()) {
			return "loginform";
		}else {
			return "created";
		}
	}
	
	/*
	//hello 요청을 GET 방식으로 전송했을 때 처리
	@RequestMapping(value="hello", method=RequestMethod.GET)
	public String hello(Model model) {
		model.addAttribute("message", "뷰에게 전달하는 데이터");
		return "hello";
	}
	
	@RequestMapping(value="message/detail/{num}",
			method=RequestMethod.GET)
	public String detail(Model model, 
			@PathVariable("num") Integer num) {
		System.out.println("num:" + num);
		model.addAttribute("num", num);
		return "detail";
	}
	
	@RequestMapping(value="param1", method=RequestMethod.GET)
	public String param1(Model model, 
			HttpServletRequest request) {
		//전통적인 자바 웹 프로그래밍에서의 파라미터를 읽기
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		System.out.println("name:" + name);
		System.out.println("password:" + password);
		
		return "";
	}
	
	@RequestMapping(value="param2", method=RequestMethod.POST)
	public String param2(Model model,
			@RequestParam("name") String name,
			@RequestParam("password") String password) {
		System.out.println("name:" + name);
		System.out.println("password:" + password);
		return "";
	}
	
	@RequestMapping(value="param3", method=RequestMethod.POST)
	public String param3(Model model, Command command) {
		System.out.println(command);
		return "";
	}
	
	@RequestMapping(value="redirect", method=RequestMethod.GET)
	public String redirect(Model model, HttpSession session,
			RedirectAttributes rattr) {
		//데이터 생성 - request.setAttribute("msg", "메시지") 와 동일
		//redirect 로 이동을 하게되면 request 의 데이터는 전달되지 않습니다.
		//request 가 새로 만들어지기 때문입니다.
		//이런 경우에는 session 이나 RedirectAttributes를 이용해야 합니다.
		model.addAttribute("msg", "메시지");
		//브라우저를 종료하거나 세션에서 제거하지 않는 이상 계속 유지
		//session.setAttribute("msg", "세션을 이용한 데이터 전달");
		rattr.addFlashAttribute("msg", "일회용 데이터 전달");
		
		//리다이렉트 할 때는 redirect: 다음에 URL을 설정
		return "redirect:disp";
	}
	
	@RequestMapping(value="disp", method=RequestMethod.GET)
	public String disp(Model model) {
	
		return "redirect";
	}
	*/
}




