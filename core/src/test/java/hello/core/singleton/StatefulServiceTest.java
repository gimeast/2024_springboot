package hello.core.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    @DisplayName("싱글톤 상태를 유지하도록 설계시")
    void statefulServiceSingleton() {
        //given
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        //when
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //ThreadA: A사용자 10000원 주문
        statefulService1.statefulOrder("A", 10000);
        //ThreadB: B사용자 20000원 주문
        statefulService2.statefulOrder("B", 20000);

        //then
        assertThat(statefulService1.getPrice()).isEqualTo(statefulService2.getPrice()); //상태를 유지(stateful)하기 때문

    }

    @Test
    @DisplayName("싱글톤 무상태로 설계시")
    void statelessServiceSingleton() {
        //given
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        //when
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //ThreadA: A사용자 10000원 주문
        int aPrice = statefulService1.statelessOrder("A", 10000);
        //ThreadB: B사용자 20000원 주문
        int bPrice = statefulService2.statelessOrder("B", 20000);

        //then
        assertThat(aPrice).isNotEqualTo(bPrice); //무상태로 설계하였기때문

    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }

}