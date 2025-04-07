package org.novak.java;

import org.novak.java.springConfig.SpringConfig;
import org.novak.java.view.MainView;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        MainView mainView = context.getBean(MainView.class);
        mainView.start();
    }
}
