package hello.core.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS) //proxyMode를 적용하면 프록시 객체가 생성된다.
public class MyLogger {

    private String uuid;

    @Setter
    private String requestURL;

    public void log(String message) {
        System.out.printf("[%s][%s][%s]\n", uuid, requestURL, message);
    }

    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.printf("[%s] request scope bean create:[%s]\n", uuid, this);
    }

    @PreDestroy
    public void close() {
        System.out.printf("[%s] request scope bean close:[%s]\n", uuid, this);
    }
}
