package org.project.example;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class AppTest {
    @Test
    public void shouldShowHelp() {
        App.main(new String[] { "--help" });
        assertTrue(true);
    }

    @Test
    public void shouldShowVersion() {
        App.main(new String[] { "--version" });
        assertTrue(true);
    }

    @Test
    public void shouldShowParam() {
        App.main(new String[] { "--param", "name" });
        assertTrue(true);
    }

}
