package org.project.example;

import static org.junit.Assert.assertTrue;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class AppTest {

    private Logger logger = LogManager.getLogger(AppTest.class);

    // Get the standard System.out
    private PrintStream stdout = System.out;

    @Test
    public void shouldShowHelp() throws IOException {
    
        // Redirect System.out to buffer
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));

        // Execute test
        App.main(new String[] { "--help" });

        // Get the System.out and log
        bo.flush();
        String allWrittenLines = new String(bo.toByteArray());
        logger.info("\n"+allWrittenLines);

        // Return System.out to console
        System.setOut(stdout);

        // Check results
        assertTrue(allWrittenLines.startsWith("usage:"));
    }

    @Test
    public void shouldShowHelpIfEmpty() throws IOException {

        // Redirect System.out to buffer
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));

        // Execute test
        App.main(new String[] {});

        // Get the System.out and log
        bo.flush();
        String allWrittenLines = new String(bo.toByteArray());
        logger.info("\n"+allWrittenLines);

        // Return System.out to console
        System.setOut(stdout);

        // Check results
        assertTrue(allWrittenLines.startsWith("usage:"));

    }

    @Test
    public void shouldShowVersion() throws IOException {
        // Redirect System.out to buffer
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));

        // Execute test
        App.main(new String[] {"--version"});

        // Get the System.out and log
        bo.flush();
        String allWrittenLines = new String(bo.toByteArray());
        logger.info("\n"+allWrittenLines);

        // Return System.out to console
        System.setOut(stdout);

        // Check results
        assertTrue(allWrittenLines.startsWith("hello-java-shell version"));
    }

    @Test
    public void shouldShowRequidedParams() throws IOException {
        // Redirect System.out to buffer
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));

        // Execute test
        App.main(new String[] {"-p1", "param1", "-p2", "param2"});

        // Get the System.out and log
        bo.flush();
        String allWrittenLines = new String(bo.toByteArray());
        logger.info("\n"+allWrittenLines);

        // Return System.out to console
        System.setOut(stdout);

        // Check results
        assertTrue(allWrittenLines.startsWith("Param 1 value: param1"));
    }

    @Test
    public void shouldShowWrongParameters() throws IOException {
        // Redirect System.out to buffer
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));

        // Execute test
        App.main(new String[] { "--notvalid"});

        // Get the System.out and log
        bo.flush();
        String allWrittenLines = new String(bo.toByteArray());
        logger.info("\n"+allWrittenLines);

        // Return System.out to console
        System.setOut(stdout);

        // Check results
        assertTrue(allWrittenLines.startsWith("Wrong arguments"));
    }


}
