package hello.core.scan.filter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.context.annotation.FilterType.ANNOTATION;

public class ComponentFilterAppConfigTest {
    
    @Test
    void filterScan() {
        //given
        ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        BeanA beanA = ac.getBean("beanA", BeanA.class);

        //when
        
        //then
        assertThat(beanA).isNotNull();
        assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean("beanB", BeanB.class));
    }

    @Configuration
    @ComponentScan(
            //필터의 타입
            /*
            ANNOTATION : 기본값, 애노테이션을 인식해서 동작한다.
            ASSIGNABLE_TYPE : 지정한 타입과 자식 타입을 인식해서 동작한다.
            ASPECTJ
            REGEX
            CUSTOM
             */
            includeFilters = @Filter(type = ANNOTATION, classes = MyIncludeComponent.class),
            excludeFilters = @Filter(type = ANNOTATION, classes = MyExcludeComponent.class)
    )
    static class ComponentFilterAppConfig {

    }
}
