import org.flywaydb.core.Flyway;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.rybaltovskij.weather.dto.UserRegisterDto;
import ru.rybaltovskij.weather.mapper.UserMapperImpl;
import ru.rybaltovskij.weather.repository.UserRepository;
import ru.rybaltovskij.weather.service.SessionService;
import ru.rybaltovskij.weather.service.UserService;

import java.util.Arrays;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class RegistrationTest {


    @Autowired
    private UserService userService;

    @Autowired
    private Flyway flyway;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void registrationTest() {
        UserRegisterDto user =
                new UserRegisterDto("asd", "asd", "asd");
        userService.register(user);
        Long count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM weather.users", Long.class);
        assertNotEquals(Long.valueOf(0), count);
    }

    @Test
    public void nonUniqueUserRegisterTest() {
        UserRegisterDto user =
                new UserRegisterDto("123", "123", "123");
        userService.register(user);
        assertThrows(Exception.class, () -> userService.register(user));
        Long count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM weather.users", Long.class);
        assertNotEquals(Long.valueOf(0), count);
    }
}
