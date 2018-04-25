package org.project.example;

import java.io.PrintWriter;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class App {

    public static void main(String[] args) {
        
        final CommandLineParser cmdLineParser = new DefaultParser();
        CommandLine commandLine = null;
		try {
			commandLine = cmdLineParser.parse(generateOptions(), args);
		} catch (ParseException e) {
            System.out.println("Wrong arguments... see hello-java-shell usage");
            printHelp(generateOptions());
            System.exit(0);
        }
        
        if(commandLine.hasOption("help")){
            printHelp(generateOptions());
            System.exit(0);
        }

        if(commandLine.hasOption("version")){
            System.out.println("hello-java-shell version 0.0.1");
            System.exit(0);
        }

        if(commandLine.hasOption("param")){
            String paramName = commandLine.getOptionValue("param");
            System.out.println("Param value: " + paramName);
        }

    }

    private static Options generateOptions() {

        // Menu options
        Option help = new Option("h", "help", false, "print this message");
        Option version = new Option("v", "version", false, "print the version information and exit");
        Option buildfile = Option.builder("p").longOpt("param").desc("param to print").required(false)
            .hasArg(true).argName("name").build();

        // Add to Options List       
        final Options options = new Options();
        options.addOption(help);
        options.addOption(version);
        options.addOption(buildfile);
        return options;
    }


    private static void printHelp(final Options options) {
        final HelpFormatter formatter = new HelpFormatter();
        final String header = "List of parameters:";
        final String footer = "See hello-java-shell documentation for further details.";
        formatter.setSyntaxPrefix("usage: ");
        formatter.printHelp("java -jar hello-java-shell.jar",header, options, footer,true);
    }

}
