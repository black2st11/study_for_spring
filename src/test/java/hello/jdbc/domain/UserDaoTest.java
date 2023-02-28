package hello.jdbc.domain;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

@Slf4j
class UserDaoTest {

    @Test
    void crudNUser() throws SQLException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao dao = context.getBean("userDao", UserDao.class);
        User user = new User();
        user.setId("naver");
        user.setName("네이버");
        user.setPassword("naver1234");

        dao.add(user);

        log.info("등록 성공, {}", user);

        User result = dao.get(user.getId());

        Assertions.assertThat(user).isEqualTo(result);

        log.info("조회 성공, {}", result);
    }

    @Test
    void crudDUser() throws SQLException {
        UserDao dao = new UserDao(new DConnectionMaker());
        User user = new User();
        user.setId("daum");
        user.setName("다음");
        user.setPassword("daum1234");

        dao.add(user);

        log.info("등록 성공, {}", user);

        User result = dao.get(user.getId());

        Assertions.assertThat(user).isEqualTo(result);

        log.info("조회 성공, {}", result);
    }
}