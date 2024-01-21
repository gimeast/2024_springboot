package hello.core.lifecycle;


import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class NetworkClient {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
        connect();
        call("초기화 연결 메시지");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + " message = " + message);
    }

    public void disconnect() {
        System.out.println("close: " + url);
    }

    @PostConstruct //spring이 아닌 자바에 내장되어있는 기술이다
    public void init() throws Exception { //의존관계 주입이 끝난경우 호출된다.
        System.out.println("init");
        connect();
        call("초기화 연결 메시지");
    }

    @PreDestroy //spring이 아닌 자바에 내장되어있는 기술이다
    public void close() throws Exception {
        System.out.println("close");
        disconnect();
    }
}
