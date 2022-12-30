import com.example.servlet.service.MemberService;
import com.example.servlet.service.MemberServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.testng.annotations.Test;

public class TestCase {

    @Test
    public void Hello() {
        System.out.println("!23");
    }

    @Test
    @DisplayName("service test")
    public void serviceTest() {
        MemberService service = MemberServiceImpl.getInstance();
        System.out.println(
                service.login("user", "1111")
        );
    }

}
