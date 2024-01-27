package hello.core.web;

import hello.core.common.MyLogger;
import jakarta.inject.Provider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService {

//    private final MyLogger myLogger;
    private final Provider<MyLogger> myLoggerProvider;

    public void logic(String id) {
//        myLogger.log("service id = " + id);
        myLoggerProvider.get().log("service id = " + id);
    }
}
