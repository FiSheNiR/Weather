import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class ConnectionDbTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testTableExistsAndHasData() {
        jdbcTemplate.execute("INSERT INTO weather.users (login,password) VALUES ('John','123456')");
        Long count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM weather.users", Long.class);
        assertNotEquals(Long.valueOf(0), count);
    }
}
