package ru.interns.mock.demo.cheat;

import org.springframework.stereotype.Component;
import ru.interns.cheater_web_service.UserDTO;

import javax.annotation.PostConstruct;
import javax.xml.datatype.DatatypeConfigurationException;
import java.util.HashMap;
import java.util.Map;

@Component
public class CheaterRepo {
    private static final Map<Long, UserDTO> cheaters = new HashMap<>();

    @PostConstruct
    public void initData() throws DatatypeConfigurationException {
        UserDTO user1 = new UserDTO();
        user1.setName("Diego");
        user1.setMiddleName("Vanila");
        user1.setSureName("Mantoia");
        user1.setPassport(1111212121L);

        cheaters.put(user1.getPassport(), user1);

        UserDTO user2 = new UserDTO();
        user2.setName("Eldar");
        user2.setMiddleName("Purpur");
        user2.setSureName("Djarahov");
        user2.setPassport(1111222222L);

        cheaters.put(user2.getPassport(), user2);

    }

    public Long findUser(Long userPassport) {
        if (userPassport == null || cheaters.containsKey(userPassport)){
            return 1L;
        }
        return 0L;
    }

}
