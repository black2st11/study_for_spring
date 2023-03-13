package hello.jdbc.domain;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class UserDaoConnectionCountingTest {

    @Test
    void countingTest() throws SQLException {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(CountingDaoFactory.class);

        UserDao dao = ac.getBean("userDao", UserDao.class);

        User userA = new User("userA", "userA", "user1234"); //1
        User userB = new User("userB", "userB", "user1234"); //2
        User userC = new User("userC", "userC", "user1234"); //3

        dao.add(userA);
        dao.add(userB);
        dao.add(userC);


        CountingConnectionMaker cm = ac.getBean("connectionMaker", CountingConnectionMaker.class);
        log.info("counting = " + cm.getCounter());


        Assertions.assertThat(cm.getCounter()).isEqualTo(3);
    }
}