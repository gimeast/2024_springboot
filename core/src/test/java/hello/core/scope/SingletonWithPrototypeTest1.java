package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Provider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.*;

public class SingletonWithPrototypeTest1 {

    @Test
    @DisplayName("프로토타입 테스트")
    void prototypeFind() {
        //given
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        //when
        PrototypeBean bean1 = ac.getBean(PrototypeBean.class);
        bean1.addCount();

        PrototypeBean bean2 = ac.getBean(PrototypeBean.class);
        bean2.addCount();

        //then
        System.out.println("bean1.count : " + bean1.getCount());
        System.out.println("bean2.count : " + bean2.getCount());

        assertThat(bean1.getCount()).isEqualTo(1);
        assertThat(bean2.getCount()).isEqualTo(1);
        assertThat(bean1).isNotSameAs(bean2);
    }

    @Test
    @DisplayName("싱글톤과 프로토타입 같이쓰는 경우")
    void singletonClientUsePrototype() {
        //given
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class, ClientBean.class);


        //when
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        PrototypeBean prototypeBean = ac.getBean(PrototypeBean.class);

        //then
        int count1 = clientBean1.logic();
        int count2 = clientBean2.logic();

        System.out.println("count1:" + count1);
        System.out.println("count2:" + count2);
        prototypeBean.addCount();
        System.out.println("prototypeBean.count:" + prototypeBean.getCount());

//        assertThat(count1).isLessThan(count2);
//        assertThat(prototypeBean.getCount()).isNotEqualTo(count2);
        assertThat(count1).isEqualTo(1);
        assertThat(count2).isEqualTo(1);
        assertThat(prototypeBean.getCount()).isEqualTo(1);

    }

    @Scope("singleton")
    static class ClientBean {

        private final Provider<PrototypeBean> prototypeBeanProvider;

//        private final ObjectProvider<PrototypeBean> prototypeBeanObjectProvider;

//        private final PrototypeBean prototypeBean;
//        private final ApplicationContext applicationContext;

//        public ClientBean(ApplicationContext applicationContext) {
//            this.applicationContext = applicationContext;
//        }

        public ClientBean(/*PrototypeBean prototypeBean, *//*ObjectProvider<PrototypeBean> prototypeBeanObjectProvider*/Provider<PrototypeBean> prototypeBeanProvider) {
//            this.prototypeBeanObjectProvider = prototypeBeanObjectProvider;
//            this.prototypeBean = prototypeBean;
            this.prototypeBeanProvider = prototypeBeanProvider;
        }

        public int logic() {
//            PrototypeBean prototypeBean = applicationContext.getBean(PrototypeBean.class);
//            prototypeBean.addCount();
//            PrototypeBean prototypeBean = prototypeBeanObjectProvider.getObject();
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init : " + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }

    }
}
