package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;


import static org.assertj.core.api.Assertions.*;

public class SingletonTest {

    @Test
    void singletonBeanFind() {
        //given
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class);

        //when
        System.out.println("SingletonBean bean1");
        SingletonBean bean1 = ac.getBean(SingletonBean.class);
        System.out.println("SingletonBean bean2");
        SingletonBean bean2 = ac.getBean(SingletonBean.class);

        //then
        System.out.println("SingletonBean bean1 = " + bean1);
        System.out.println("SingletonBean bean2 = " + bean2);

        assertThat(bean1).isSameAs(bean2);

        ac.close();
    }

    @Scope("singleton") //default로 보통 생략해서 쓴다.
    static class SingletonBean {

        @PostConstruct
        public void init() {
            System.out.println("SingletonBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("SingletonBean.destroy");
        }

    }
}
