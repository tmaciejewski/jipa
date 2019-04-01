package japi;

import org.junit.*;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.jdbc.JdbcTestUtils;

@RunWith(SpringRunner.class)
@DataJdbcTest
public class TrainsDAOTest {

    @Autowired
    private TrainsDAO trainsDAO;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void test_getAllTrains() throws Exception {
        assertEquals(3, JdbcTestUtils.countRowsInTable(jdbcTemplate, "train"));
    }
}
