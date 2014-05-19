package org.gradle;

import org.junit.Test;
import static org.junit.Assert.*;

public class PersonWrapperTest {
    @Test
    public void canConstructAPersonWithAName() {
        PersonWrapper person = new PersonWrapper("Larry");
        assertEquals("Larry", person.getName());
    }
}
