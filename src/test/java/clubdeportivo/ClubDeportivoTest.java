package clubdeportivo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClubDeportivoTest {
    private ClubDeportivo clubDeportivo; 

    @BeforeEach
    public void initTest() throws ClubException {
        clubDeportivo = new ClubDeportivo(null);
    }
}
