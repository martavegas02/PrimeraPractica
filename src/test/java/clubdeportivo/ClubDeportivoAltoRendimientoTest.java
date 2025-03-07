package clubdeportivo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ClubDeportivoAltoRendimientoTest {
    
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
        assertThrows(ClubException.class, () -> new ClubDeportivoAltoRendimiento("Club", 1, 1, 0));
    }

    @Test
    @DisplayName("Comprobar que el lenght de los datos de la actividad no son < 5")
    public void anyadirActividad_test() throws ClubException {
        //Arrange
        ClubDeportivoAltoRendimiento cd = new ClubDeportivoAltoRendimiento("Club", 1, 1);
        //Act
        //Assert
        assertThrows(ClubException.class, () -> cd.anyadirActividad(new String[] {"1", "2", "3", "4"}));
    }

    @Test
    @DisplayName("Comprobar que las plazas sean mayores que el maximo de personas por grupo")
    public void anyadirActividad_plazas_mayor_maximo() throws ClubException {
        //Arrange
        ClubDeportivoAltoRendimiento cd = new ClubDeportivoAltoRendimiento("Club", 5, 1);
        //Act
        cd.anyadirActividad(new String[] {"Zumba", "Mañana", "15", "5", "20.0"});
        //Assert
        Grupo g = cd.getActividad();
        assertEquals(10, g.getPlazas());
        
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
