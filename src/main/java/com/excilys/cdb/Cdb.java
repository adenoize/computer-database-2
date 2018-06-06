package main.java.com.excilys.cdb;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import main.java.com.excilys.cdb.config.AppConfig;
import main.java.com.excilys.cdb.ui.CommandLineInterface;

/**
 * @author Aurelien Denoize
 */
public class Cdb {

    /**
     * @param args list of arguments.
     */
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getEnvironment().setActiveProfiles("cli");
        context.register(AppConfig.class);
        context.refresh();

        CommandLineInterface cli = context.getBean(CommandLineInterface.class);

        cli.run(args);

        context.close();
    }

}
