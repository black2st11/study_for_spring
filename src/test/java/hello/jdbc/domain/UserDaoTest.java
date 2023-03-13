package hello.jdbc.domain;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import java.sql.SQLException;

import static hello.jdbc.connection.ConnectionConst.*;
import static org.assertj.core.api.Assertions.assertThat;

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

        assertThat(user).isEqualTo(result);

        log.info("조회 성공, {}", result);
    }

    @Test
    void crudDUser() throws SQLException {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(URL);
        ds.setUsername(USERNAME);
        ds.setPassword(PASSWORD);
        UserDao dao = new UserDao(ds);
        User user = new User();
        user.setId("daum");
        user.setName("다음");
        user.setPassword("daum1234");

        dao.add(user);

        log.info("등록 성공, {}", user);

        User result = dao.get(user.getId());

        assertThat(user).isEqualTo(result);

        log.info("조회 성공, {}", result);
    }

    @Test
    void checkDao() {
        DaoFactory daoFactory = new DaoFactory();
        UserDao userDao1 = daoFactory.userDao();
        UserDao userDao2 = daoFactory.userDao();
        log.info("daoFactory dao1 = {}", userDao1);
        log.info("daoFactory dao2 = {}", userDao2);
        assertThat(userDao1 == userDao2).isEqualTo(false);

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao userDao3 = context.getBean("userDao", UserDao.class);
        UserDao userDao4 = context.getBean("userDao", UserDao.class);

        log.info("ac dao3 = {}", userDao3);
        log.info("ac dao4 = {}", userDao4);
        assertThat(userDao3 == userDao4).isEqualTo(true);


    }
}