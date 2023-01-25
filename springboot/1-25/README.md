# CSRF 토큰 활용

- 현재 상태에서는 로그인이 수행이 안되기 때문에 CustomSecurityConfig 클래스의 filterchain메서드를 설정에 추가
    - https.csrf.disable()

### 자동 로그인

> 쿠키를 이용해서 브라우저에 로그인했던 정보를 유지하는 방식으로 구현

- 이 기능을 사용하기 위해서는 현재 프로젝트가 사용하는 데이터베이스에 persistent_logins 라는 테이블을 추가해야함

### CustomSecurityConfig 사용

### 회원정보를 나타내는 ClubMember Entity 생성  ``

### 로그인 처리결과 혹은 과정

> spring security를 사용하는 로그인 ㄹ처리에서는 로글인 처리 결과를 User클래스를 상속받는 클래스에 저장

### OAuth 개요

> 인증 서비스를 제공하는 업체들의 공통된 인증 방식(Open Authroization)

```java

@Log4j2
@Service
@RequiredArgsConstructor
public class CustomOauth2UserService extends DefaultOAuth2UserService {

    //카카오 로그인 성공 후 넘어오는 데이터를 이용해서 eamil을 추출해서 리턴하는 메서드
    private String getKakaoEmail(Map<String, Object> paramMap) {
        //카카오 계정 정보가 있는 Map을 추출
        log.info("카카오 맵" + paramMap.toString());
        Object value = paramMap.get("kakao_account");
        LinkedHashMap accountMap = (LinkedHashMap) value;
        String email = (String) accountMap.get("email");

        log.info("카카오 계정 이메일" + email);
        return email;
    }


    //로그인 성공했을 때 호출되는 메서드
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        //로그인에 성공한 서비스의 정보 가져오기
        ClientRegistration clientRegistration = userRequest.getClientRegistration();

        String clientName = clientRegistration.getClientName();
        log.info("client-Name (load User)" + clientName);

        //계정에 대한 정보 가져오기

        OAuth2User oAuth2User = super.loadUser(userRequest);

        Map<String, Object> paramMap = oAuth2User.getAttributes();
        String email = null;
        switch (clientName.toLowerCase()) {
            case "kakao":
                email = getKakaoEmail(paramMap);
                break;
        }
        log.info("email (loadUser method )" + email);

        return oAuth2User;
//        return super.loadUser(userRequest);
    }


}
```

### oauth2 를 사용했을 때 문제점

- 매번 새로운 유저로 판단
    - email같은 정를 데이터베이스 테이블에 저장해서 이전에 로그인 한 적이 있는 유저인지 판단
- 여러가지 로그인 방법을 제시하는경우 서로 다른 유저로 판단하는 문제
    - 재가입을 시키는 형태로 해결할 수 있는데 이 때 이메일을 전부 외래키로 등록을 해서 소셜로 로그인했을때 아이디를 찾아주는 방식

### 카카오로 로그인 성공시 데이터베이스에 등록

- spring security에서 사용하고있는 ClubMemberSecurityDTO 클래스를 수정

```java

@Getter
@Setter
@ToString
public class ClubMembmerSecurityDTO extends User implements OAuth2User {
    /*
            ..
            ..
     */
    private Map<String, Object> socialMap;

    @Override
    public Map<String, Object> getAttributes() {
        return this.getSocialMap();
    }

    @Override
    public String getName() {
        return this.mid;
    }
}

```

### 소셜 로그인을 수행한 후 이메일을 가진 사용자를 찾아보고 없는 경우에는 회원가입을 하고 있는경우에는 그정도를 리턴하도록 CustomOauth2UserService 클래스 수정 