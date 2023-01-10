package com.example.springbootpractice;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
public class MemoRespositoryTestt {

    @Autowired
    MemoRepository memoRepository;

    @Test
    public void testInsert() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Memo memo = Memo.builder().memoText("sample .." + i)
                    .build();
            memoRepository.save(memo);
        });

    }
}
