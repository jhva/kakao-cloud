//package com.example.springbootpractice;
//
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.test.annotation.Commit;
//
//import javax.transaction.Transactional;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.IntStream;
//
//@SpringBootTest
//public class MemoRespositoryTestt {
//
//    @Autowired
//    MemoRepository memoRepository;
//
//    @Test
//    public void testInsert() {
//        IntStream.rangeClosed(1, 100).forEach(i -> {
//            Memo memo = Memo.builder().memoText("sample .." + i)
//                    .build();
//            memoRepository.save(memo);
//        });
//
//    }
//
//    @Test
//    public void testAll() {
//        List<Memo> list = memoRepository.findAll();
//        for (Memo memo : list) {
//            System.out.println(memo);
//        }
//    }
//
//    @Test
//    public void getId() {
//        //기본키를 가지고 조회하면 없거나 1개의 데이터를 리턴한다.
//        //optional 은 있을수도있고 없을수도있다
//        Optional<Memo> result = memoRepository.findById(100L);
//        if (result.isPresent()) {
//            System.out.println(result.get());
//        } else {
//            System.out.println("not found");
//        }
//    }
//
//    @Test
//    public void updateTest() {
//        Memo memo = Memo.builder()
//                .memoText("데이터 수정 ..")
//                .build();
//        memoRepository.save(memo);
//        //기본키의 값이 존재하면 수정이지만
//        //존재하지않은 경우에는 삽입이 발생하므로 데이터가 한 개 더 생긴다
//    }
//
//
//    @Test
//    public void 삭제메서드테스트() {
//        memoRepository.deleteById(100L);// 기본키를 가지고 삭제
//        memoRepository.delete(Memo.builder().id(99L).build());
//        //entity를 이용해서 삭제
//        //없는 데이터를 삭제하고 자하면 에러
//        //삭제를 할때는 존재여부를 확인해야한다.
//        memoRepository.deleteById(1000L);
//    }
//
//    @Test
//    public void testPaging() {
//        Pageable pageable = PageRequest.of(0, 10);
//        Page<Memo> memo = memoRepository.findAll(pageable);
////        System.out.println(memo + "result");
////        System.out.println(memo.getTotalPages() + "get total page");
//// getTotalPage :10
//
//        //조회된 데이터 순회
//        for (Memo memo1 : memo.getContent()) {
//            System.out.println(memo1.toString());
//            //10개씩 , 10페이지
//        }
//
//
//    }
//
//    @Test
//    public void testSort() {
//
//        //id의 내림차순
//        Sort sort = Sort.by("id").descending();
//
//        Pageable pageable = PageRequest.of(0, 10, sort);
//        //페이지번호 ,개수, 정렬
//
//        Page<Memo> result = memoRepository.findAll(pageable);
//        for (Memo memo : result.getContent()) {
//            System.out.println(memo);
//        }
//    }
//
//    @Test
//    public void testSortConcate() {
//        Sort sort = Sort.by("id").descending();
//        Sort sort2 = Sort.by("memoText").descending();
//        //2개의 결합 - sort1의 값이 같으면 sort2로 정렬
//        Sort sortall = sort.and(sort2);
//        Pageable pageable = PageRequest.of(0, 10, sortall);
//
//        Page<Memo> result = memoRepository.findAll(pageable);
//        for (Memo memo : result.getContent()) {
//            System.out.println(memo);
//        }
//    }
//
//    @Test
//    public void queryMethod1() {
////        List<Memo> list = memoRepository.findByIdBetweenOrderByIdDesc(30L,40L);
////
////        for(Memo memo : list){
////            System.out.println(memo);
////        }
//        Pageable pageable = PageRequest.of(1, 5);
//        Page<Memo> result = memoRepository.findByIdBetweenOrderByIdDesc(0L, 50L, pageable);
//        //0~50번중에서
//
//        for (Memo memo : result.getContent()) {
//            System.out.println(memo);
//        }
//    }
//
//    @Test
//    //특정한 작업에서는 트랜잭션을 적용하지않으면 예외가 발생
//    @Transactional
//    //트랜잭션이 적용되면 자동 COmmit 되지 않으므로 Commit을 호출해야
//    //실제 작업이 완성된다.
//    @Commit
//    public void deleteMehotdTest() {
//        memoRepository.deleteByIdLessThen(10L);
//    }
//
//    @Test
//    public void updateTestQuery() {
//        System.out.println(memoRepository.updateQueryMemoText(Memo.builder().memoText("123").id(1L).build()));
//    }
//
//    @Test
//    public void testSelectQuerty() {
//        //0번 페이지 10개의 데이터를 가져오고 내리참순으로 정렬해서
//
//        //pageable객체
//        Pageable pageable = PageRequest.of(0, 10, Sort.by("id").descending());
//
//        Page<Memo> result = memoRepository.getListWithQuery(50L,pageable);
//
//        for (Memo memo : result.getContent()) {
//            System.out.println(memo);
//        }
//    }
//
//
//}
