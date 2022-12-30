package com.example.servlet.service;

import com.example.servlet.MemberDTO;
import com.example.servlet.MemberVO;
import persistence.MemberDAO;

import java.lang.reflect.Member;

public class MemberServiceImpl implements MemberService {
    //service 엔 항상 DAO 가 주입되어야한다

    @Override
    public MemberDTO login(String uuid) {
        MemberDTO dto = null;

        MemberVO vo = memberDAO.login(uuid);
        if (vo != null) {
            dto = new MemberDTO();
            dto.setMid(vo.getMid());
            dto.setMname(vo.getMname());
        }

        return dto;
    }

    @Override
    public MemberDTO login(
            String mid, String mpw, String uuid) {
        MemberDTO dto = null;
        MemberVO vo = memberDAO.login(mid, mpw);
        //vo를 dto로 변환
        if (vo != null) {
            dto = new MemberDTO();
            dto.setMid(vo.getMid());
            dto.setMname(vo.getMname());
            //UUID 업데이트
            memberDAO.updateUUID(mid, uuid);
        }

        return dto;
    }


    private MemberDAO memberDAO;

    private MemberServiceImpl() {
        memberDAO = MemberDAO.getInstance();
    }

    private static MemberService service;

    public static MemberService getInstance() {
        if (service == null) {
            service = new MemberServiceImpl();
        }
        return service;
    }


}
