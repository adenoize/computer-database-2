package main.java.com.excilys.cdb.ui;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import main.java.com.excilys.cdb.controller.CliController;
import main.java.com.excilys.cdb.model.Company;
import main.java.com.excilys.cdb.model.Computer;
import main.java.com.excilys.cdb.model.Page;

/**
 * @author Aurelien Denoize
 */
@Component
public class CommandLineInterface {

    
    CliController cliController;
    
    Queue<String> args = new LinkedList<String>();;

    
    public CommandLineInterface(CliController cliController) {
        super();
        this.cliController = cliController;
    }
    
    
    /**
     * Run the CLI.
     * @param arguments arguments of application
     */
    public void run(String[] arguments) {

        for (String a : arguments) {
            args.add(a);
        }

        if (args.size() < 2) {
            printHelp();
            return;
        }

        String arg = args.poll();

        switch (arg) {
        case "-a":
        case "--add":
            addComputer();
            break;
        case "-u":
        case "--update":
            updateComputer();
            break;
        case "-r":
        case "--remove":
            remove();
            break;
        case "-s":
        case "--show":
            showComputer();
            break;
        case "-l":
        case "--list":
            list();
            break;
        case "-h":
        case "--help":
            printHelp();
            break;
        default:
            printHelp();
        }

    }

    /**
     * Add new Computer.
     */
    private void addComputer() {

        String arg = args.poll();

        if (!arg.contains("=")) {
            System.err.println("Bad parameter: " + arg);
            printHelp();
            return;
        }

        final String[] tab = arg.split("=");

        if (!tab[0].equals("name")) {
            System.err.println("Bad parameter: " + arg);
            printHelp();
            return;
        }

        Map<String, String> parameters = parseParameters();
        parameters.put(tab[0], tab[1]);

        System.out.println(cliController.addComputer(parameters));

    }

    /**
     * Update a Computer.
     */
    private void updateComputer() {

        String arg = args.poll();

        try {
            Integer.parseInt(arg);
        } catch (NumberFormatException e) {
            System.err.println("Please enter an ID; (ex : 123)");
            printHelp();
            return;
        }

        Map<String, String> parameters = parseParameters();

        System.out.println(cliController.updateComputer(arg, parameters));
    }

    /**
     * Remove computers or companies.
     */
    private void remove() {
        String arg = args.poll();
        switch (arg) {
        case "--computer":
            removeComputer();
            break;
        case "--company":
            removeCompany();
            break;

        default:
            printHelp();
            break;
        }
    }

    /**
     * Remove a computer.
     */
    private void removeComputer() {
        String arg = args.poll();

        try {
            Integer.parseInt(arg);
        } catch (NumberFormatException e) {
            System.err.println("Please enter an ID; (ex : 123)");
            printHelp();
            return;
        }

        System.out.println(cliController.removeComputer(arg));

    }

    /**
     * Remove a company.
     */
    private void removeCompany() {
        String arg = args.poll();

        try {
            Integer.parseInt(arg);
        } catch (NumberFormatException e) {
            System.err.println("Please enter an ID; (ex : 123)");
            printHelp();
            return;
        }

        System.out.println(cliController.removeCompany(arg));

    }

    /**
     * List computers or companies.
     */
    private void list() {

        String arg = args.poll();

        switch (arg) {
        case "--computer":
            listComputer();
            break;
        case "--company":
            listCompany();
            break;
        default:
            printHelp();
            break;
        }

    }

    /**
     * List computers.
     */
    private void listComputer() {

        String choice = "";
        int currentPage = 0;
        Page<Computer> page = null;
        List<Computer> computers = null;

        System.out.println("Page mode (enter 'n' for next and 'p' for previous page : ");

        Scanner sc = new Scanner(System.in);
        page = cliController.listComputer(currentPage);
        do {

            if (choice.equals("n") && !page.isEmpty()) {
                currentPage++;
            } else if (choice.equals("p") && currentPage > 0) {
                currentPage--;
            }

            page = cliController.listComputer(currentPage);
            computers = page.getPage();

            for (Computer computer : computers) {
                System.out.println(computer);
            }

            System.out.println("enter 'n' for next and 'p' for previous page :");
            choice = sc.next();

        } while (!choice.equals("q"));

        sc.close();

    }

    /**
     * List companies.
     */
    private void listCompany() {

        String choice = "";
        int currentPage = 0;
        Page<Company> page = null;
        List<Company> companies = null;

        page = cliController.listCompany(currentPage);

        System.out.print("Page mode (enter 'n' for next and 'p' for previous page : ");

        Scanner sc = new Scanner(System.in);
        page = cliController.listCompany(currentPage);
        do {

            if (choice.equals("n") && !page.isEmpty()) {
                currentPage++;
            } else if (choice.equals("p") && currentPage > 0) {
                currentPage--;
            }

            page = cliController.listCompany(currentPage);
            companies = page.getPage();

            for (Company company : companies) {
                System.out.println(company);
            }

            System.out.print("enter 'n' for next and 'p' for previous page :");
            choice = sc.next();

        } while (!choice.equals("q"));

        sc.close();

    }

    /**
     * Show informations about the Computer with the given id.
     */
    private void showComputer() {
        String arg = args.poll();

        try {
            Integer.parseInt(arg);
        } catch (NumberFormatException e) {
            System.err.println("Please enter an ID; (ex : 123)");
            printHelp();
            return;
        }

        System.out.println(cliController.showComputer(arg));

    }

    /**
     * Parse arguments into Map.
     * @return Map of arguments
     */
    private Map<String, String> parseParameters() {

        final Map<String, String> parameters = new HashMap<>();

        for (final String arg : args) {
            if (!arg.contains("=")) {
                System.err.println("Bad parameter: " + arg);
                printHelp();
                System.exit(2);
            }

            final String[] tab = arg.split("=");
            parameters.put(tab[0], tab[1]);
        }
        return parameters;
    }

    /**
     * Print the help for the user.
     */
    private void printHelp() {
        System.out.println("Help :");
        System.out.println("cdbCli --add name=[value] company=[value] [field=value]");
        System.out.println("cdbCli --update (id) [field=value]");
        System.out.println("cdbCli --remove --computer (id)");
        System.out.println("cdbCli --remove --company (id)");
        System.out.println("cdbCli --show (id)");
        System.out.println("cbdCli --list --computer");
        System.out.println("cbdCli --list --company");
    }

    

}
