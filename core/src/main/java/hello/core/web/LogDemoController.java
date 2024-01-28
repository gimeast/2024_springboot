package hello.core.web;

import hello.core.common.MyLogger;
import jakarta.inject.Provider;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
//    private final MyLogger myLogger; //myLogger의 스코프는 request인데 request가 없는데 dependency를 하여 오류를 발생 시킨다. 이럴때 Provider를 사용하면 된다.
    private final Provider<MyLogger> myLoggerProvider;

    @GetMapping("/log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
        String requestURL = request.getRequestURL().toString();

//        myLogger.setRequestURL(requestURL);
//        myLogger.log("controller test");

        MyLogger myLogger = myLoggerProvider.get();
        myLogger.setRequestURL(requestURL);
        myLogger.log("controller test");

        logDemoService.logic("testId");

        return "OK";
    }
}
