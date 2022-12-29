package java_12_28;


import java.util.List;
import java.util.Optional;

//goods 테이블에 수행할 데이터베이스 작업의 원형을 소유할 인터페이스
public interface GoodDAO {

    public List<Good> getAll();

    //    goods 테이블에서 code를 가지고 데이터를 조회하기
    public Good getCode(String code);

    //    Optional
// goods 테이블에서 name이나 manufacturer 에 포함된 데이터 조회
    public List<Good> likeGood(String content);

    //good 테이블에 데이터를 삽입하기
    //수정은 모양이 동일
    // 삭제는 동일하게 만들어도 되고 매개벼수를 기본키로 마들어도 된다
    public int insertGood(Good good);
}
