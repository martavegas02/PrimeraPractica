package clubdeportivo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ClubDeportivoAltoRendimientoTest {

    @BeforeEach
    public void setUp() {
        
    }
    
    @Test
    @DisplayName("Comprobar el constructor que el numero maximo de personas por grupo no sea <=0")
    public void ClubDeportivoAltoRendimiento_maximo_menor0() throws ClubException {
        //Arrange
        //Act
        //Assert
        assertThrows(ClubException.class, () -> new ClubDeportivoAltoRendimiento("Club", 0, 1));
    }

    @Test
    @DisplayName("Comprobar el constructor que el incremento no sea <=0")
    public void ClubDeportivoAltoRendimiento_incremento_menor0() throws ClubException {
        //Arrange
        //Act
        //Assert
        assertThrows(ClubException.class, () -> new ClubDeportivoAltoRendimiento("Club", 1, 0));
    }

    @Test
    @DisplayName("Comprobar el constructor que el numero maximo de personas por grupo no sea <=0")
    public void ClubDeportivoAltoRendimiento_maximo_tam_menor0() throws ClubException {
        //Arrange
        //Act
        //Assert
        assertThrows(ClubException.class, () -> new ClubDeportivoAltoRendimiento("Club", 1, 0, 1));
    }

    @Test
    @DisplayName("Comprobar el constructor que el incremento no sea <=0")
    public void ClubDeportivoAltoRendimiento_incremento_tam_menor0() throws ClubException {
        //Arrange
        //Act
        //Assert
        assertThrows(ClubException.class, () -> new ClubDeportivoAltoRendimiento("Club", 1, 1, -1));
    }

    @Test
    @DisplayName("Comprobar que el lenght de los datos de la actividad no son < 5")
    public void anyadirActividad_test() throws ClubException {
        //Arrange
        ClubDeportivoAltoRendimiento cd = new ClubDeportivoAltoRendimiento("Club", 1, 1, 1.5);
        //Act
        //Assert
        assertThrows(ClubException.class, () -> cd.anyadirActividad(new String[] {"Futbol", "Entreno", "10", "4"}));
    }   

    @Test
    @DisplayName("Comprobar que el formato de los datos de la actividad es correcto")
    public void anyadirActividad_formato_incorrecto() throws ClubException {
        //Arrange
        ClubDeportivoAltoRendimiento cd = new ClubDeportivoAltoRendimiento("Club", 1, 1);
        String [] datos = {"Zumba", "Medio", "diez", "10", "25.0"};
        //Act
        //Assert
        assertThrows(ClubException.class, () -> cd.anyadirActividad(datos));
    }

    @Test
    @DisplayName("Comprobar que pasa cuando las plazas son menores que el maximo")
    public void anyadirActividad_plazas_menores_maximo() throws ClubException {
        //Arrange
        ClubDeportivoAltoRendimiento cd = new ClubDeportivoAltoRendimiento("Club", 10, 5);
        String [] plazasMenores = {"Zumba", "Medio", "4", "2", "25.0"};
        //Act
        //Assert
        assertDoesNotThrow(() -> cd.anyadirActividad(plazasMenores));
        //como no podemos comprobarlo, ponemos para que no nos salte la excepcion
    }

    @Test
    @DisplayName("Comprobar que al añadir una actividad las plazas superiores al maximo se establecen al maximo")
    public void anyadirActividad_plazas_superiores_maximo() throws ClubException {
        //Arrange
        ClubDeportivoAltoRendimiento cd = new ClubDeportivoAltoRendimiento("Club", 10, 5);
        String [] superaMaximo = {"Zumba", "Medio", "20", "10", "25.0"};
        //Act
        //Assert
        assertDoesNotThrow(() -> cd.anyadirActividad(superaMaximo));
        //como no podemos comprobarlo, ponemos para que no nos salte la excepcion
    }


    @Test
    @DisplayName("Comprobar los ingresos")
    public void ingresos_test() throws ClubException {
        //Arrange
        ClubDeportivoAltoRendimiento cd = new ClubDeportivoAltoRendimiento("Club", 1, 1);
        //Act
        //Assert
        assertEquals(0, cd.ingresos());
    }
}
