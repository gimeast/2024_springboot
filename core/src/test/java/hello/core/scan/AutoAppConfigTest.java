package hello.core.scan;

import hello.core.AutoAppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class AutoAppConfigTest {

    @Test
    void basicScan() {
        //given
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        //when
        MemberService memberService = ac.getBean(MemberService.class);

        //then
        assertThat(memberService).isInstanceOf(MemberService.class);

        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " val = " + beansOfType.get(key));
        }
    }
}
