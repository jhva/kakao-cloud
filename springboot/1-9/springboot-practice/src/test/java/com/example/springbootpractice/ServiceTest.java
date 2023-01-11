package com.example.springbootpractice;

import com.example.springbootpractice.domain.GuestBook;
import com.example.springbootpractice.domain.GuestBookDTO;
import com.example.springbootpractice.domain.PageRequestDTO;
import com.example.springbootpractice.domain.PageResponseDTO;
import com.example.springbootpractice.repository.GuestBookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ServiceTest {


    @Autowired
    private GuestBookService guestBookService;

    @Test
    public void testRegister() {
        GuestBookDTO dto = GuestBookDTO.builder()
                .title("샘플제목")
                .content("샘플내용")
                .writer("ㅅㅂ")
                .build();
        System.out.println(guestBookService.register(dto));
    }


    @Test
    public void testList() {
        PageRequestDTO pageRequestDTO =
                PageRequestDTO.builder()
                        .page(1)
                        .size(10)
                        .build();
        PageResponseDTO<GuestBookDTO, GuestBook> result
                = guestBookService.getList(pageRequestDTO);

        for (GuestBookDTO dto : result.getDtoList()) {
            System.out.println(dto);
        }
    }

    @Test
    public void testListInformation() {
        PageRequestDTO pageRequestDTO =
                PageRequestDTO.builder()
                        .page(1)
                        .size(10)
                        .build();

        PageResponseDTO<GuestBookDTO, GuestBook> result = guestBookService.getList(pageRequestDTO);

        //데이터 확인
        //데이터 목록
        System.out.println(result.getDtoList());
        System.out.println(result.getPageList());
        System.out.println(result.getTotalCount());
        System.out.println(result.isPrevPage());
        System.out.println(result.isNextPage());
    }
}
