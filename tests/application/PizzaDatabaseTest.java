package application;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PizzaDatabaseTest {
    private final String[] student = new String[3];
    String[] test = PizzaDatabase.getStudent(31517);

    @Before
    public void setUp() {
        student[0] = "31517";
        student[1] = "Cecilia Stone";
        student[2] = "100";
    }

    @Test
    public void getStudent() {
        assertArrayEquals(test, student);
    }
}