package org.project.example;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class App {

    public static void main(String[] args) {

        // Print help if no argument
        if(args.length == 0){
            printHelp(getAllOptions());
            return;
        }

        // First parse for Utility options (help, version)
        final CommandLineParser cmdLineParser = new DefaultParser();
        CommandLine commandLine;
		try {
			commandLine = cmdLineParser.parse(getUtilityOptions(), args,true);
		} catch (ParseException e) {
            System.out.println("Wrong arguments... see hello-java-shell usage");
            printHelp(getAllOptions());
            return;
        }

        if(commandLine.hasOption("help")){
            printHelp(getAllOptions());
            return;
        }

        if(commandLine.hasOption("version")){
            System.out.println("hello-java-shell version 0.0.1");
            return;
        }

        
        // Finally, parse for run options
		try {
			commandLine = cmdLineParser.parse(getRunOptions(), args);
		} catch (ParseException e) {
            System.out.println("Wrong arguments... see hello-java-shell usage");
            printHelp(getAllOptions());
            return;
        }

        // Now, all required fields to run are filled
        System.out.println("Param 1 value: " + commandLine.getOptionValue("p1"));
        System.out.println("Param 2 value: " + commandLine.getOptionValue("p2"));
        
        // Check param 3, as it is optional
        if(commandLine.hasOption("p3")){
            String p3 = commandLine.getOptionValue("p3");
            System.out.println("Param 3 value: " + p3);
        }

    }

    /**
     * Get options available for run the program
     */
    private static Options getRunOptions() {

        // Menu options required
        Option p1 = Option.builder("p1").longOpt("param1").desc("param 1 to print").required(true)
            .hasArg(true).argName("value").build();
        Option p2 = Option.builder("p2").longOpt("param2").desc("param 2 to print").required(true)
        .hasArg(true).argName("value").build();

        // Menu options optionals
        Option p3 = Option.builder("p3").longOpt("param3").desc("param 3 to print").required(false)
        .hasArg(true).argName("value").build();


        // Add to Options List       
        final Options options = new Options();
        options.addOption(p1);
        options.addOption(p2);
        options.addOption(p3);
        return options;
    }

    /**
     * Get utility options like help and version
     */
    private static Options getUtilityOptions() {

        // Menu options
        Option help = new Option("h", "help", false, "print this message");
        Option version = new Option("v", "version", false, "print the version information and exit");

        // Add to Options List       
        final Options options = new Options();
        options.addOption(help);
        options.addOption(version);
        return options;
    }

    /**
     * Get all options available
     */
    private static Options getAllOptions() {
        Options allOptions = new Options();
        getRunOptions().getOptions().forEach(opt -> allOptions.addOption(opt));
        getUtilityOptions().getOptions().forEach(opt -> allOptions.addOption(opt));
        return allOptions;
    }


    private static void printHelp(final Options options) {
        final HelpFormatter formatter = new HelpFormatter();
        final String header = "List of parameters:";
        final String footer = "See hello-java-shell documentation for further details.";
        
        // Disable sorting in order to use the list add order
        formatter.setOptionComparator(null);
        formatter.setSyntaxPrefix("usage: ");
        formatter.printHelp("java -jar hello-java-shell.jar",header, options, footer,true);
    }

}
