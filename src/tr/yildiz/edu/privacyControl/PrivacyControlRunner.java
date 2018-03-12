package tr.yildiz.edu.privacyControl;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PrivacyControlRunner {


    public static void main(String[] args) {

        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("application-context.xml");
        for (int i = 0; i < 10; i++) {
            new Thread(context.getBean("ruleSelector", RuleOneVerifier.class)).start();


        }
    }
}
