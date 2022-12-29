//package java_12_27;
//
//import org.testng.Assert;
//import org.testng.annotations.Test;
//
//public class TestApplication {
//
//
//    @Test
//    public void testMethod() {
//        System.out.println(new Source().add(100, 200));
//    }
//
//
//    //이런 방법을 많이 사용함
//    @Test
//    public void testMethod2() {
//        Source source = new Source();
//
//        //메서드의 수행 결과 찾아오기
//        int cnt = source.add(200, 300);
//
//        //기대값과 비교
//
//        Assert.assertEquals(cnt, 500);
//    }
//
//
//}
