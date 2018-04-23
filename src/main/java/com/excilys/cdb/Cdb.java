package main.java.com.excilys.cdb;

import main.java.com.excilys.cdb.ui.CommandLineInterface;

/**
 * @author Aurelien Denoize
 *
 */
public class Cdb {

    /**
     * @param args
     *            list of arguments.
     */
    public static void main(String[] args) {

        CommandLineInterface cli = new CommandLineInterface();

        cli.run(args);

    }

}
