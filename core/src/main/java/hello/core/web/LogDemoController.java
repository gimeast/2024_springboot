package hello.core.web;

import hello.core.common.MyLogger;
import jakarta.inject.Provider;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    // #1
//    private final MyLogger myLogger; //myLogger의 스코프는 request인데 request가 없는데 dependency를 하여 오류를 발생 시킨다. 이럴때 Provider를 사용하면 된다.
    // #2
//    private final Provider<MyLogger> myLoggerProvider;
    // #3
    private final MyLogger myLogger; // 프록시 적용.

    @GetMapping("/log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
        String requestURL = request.getRequestURL().toString();

        // #1
//        myLogger.setRequestURL(requestURL);
//        myLogger.log("controller test");

        // #2
//        MyLogger myLogger = myLoggerProvider.get();
        myLogger.setRequestURL(requestURL);
        myLogger.log("controller test");

        logDemoService.logic("testId"); //이것은 프록시 객체의 logic을 호출한것이다.

        System.out.println("myLogger = " + myLogger.getClass());
        // proxyMode = ScopedProxyMode.TARGET_CLASS 를 설정하면
        // CGLIB라는 라이브러리로 내 클래스를 상속 받은 가짜 프록시 객체를 만들어서 주입한다.
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(MyLogger.class);
        MyLogger bean = ac.getBean(MyLogger.class);
        System.out.println("MyLogger bean = " + bean.getClass()); // 이 또한 프록시 객체가 조회된다.

        // 가장 중요한 핵심 개념은 Provider를 상요하든 proxy를 사용하든 객체 조회를 필요한 시점까지 지연처리 하였다는것이다.

        return "OK";
    }
}
