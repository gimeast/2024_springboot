package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.*;

public class PrototypeTest {

    @Test
    void prototypeBeanFind() {
        //given
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        //when
        System.out.println("PrototypeBean bean1");
        PrototypeBean bean1 = ac.getBean(PrototypeBean.class);
        System.out.println("PrototypeBean bean2");
        PrototypeBean bean2 = ac.getBean(PrototypeBean.class);

        //then
        System.out.println("PrototypeBean bean1 = " + bean1);
        System.out.println("PrototypeBean bean2 = " + bean2);

        assertThat(bean1).isNotSameAs(bean2);

        ac.close(); // 작동되지않음 - 프로토 타입은 클로즈까지 관리하지 않는다.

        // 만약 호출해야 한다면 수동으로 빈들을 destroy하면 된다.
        bean1.destroy();
        bean2.destroy();
    }

    @Scope("prototype")
    static class PrototypeBean {
        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }

}
