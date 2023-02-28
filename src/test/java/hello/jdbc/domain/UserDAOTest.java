package hello.jdbc.domain;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class UserDAOTest {

    @Test
    void crud() throws SQLException {
        UserDAO dao = new UserDAO();
        User user = new User();
        user.setId("moon");
        user.setName("문정훈");
        user.setPassword("coupled");

        dao.add(user);

        log.info("등록 성공, {}", user);

        User result = dao.get(user.getId());

        Assertions.assertThat(user).isEqualTo(result);

        log.info("조회 성공, {}", result);
    }
}