package clubdeportivo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ClubDeportivoAltoRendimientoTest {

    @Test
    @DisplayName("Comprobar que el constructor no permite número máximo de personas <= 0")
    public void ClubDeportivoAltoRendimiento_maximo_menor0() {
        assertThrows(ClubException.class, () -> new ClubDeportivoAltoRendimiento("Club", 0, 1),
            "Se esperaba una excepción cuando el número máximo de personas es menor o igual a 0");
    }
    
    @Test
    @DisplayName("Comprobar que el constructor no permite nombre null")
    public void ClubDeportivoAltoRendimiento_nombre_null() {
        assertThrows(ClubException.class, () -> new ClubDeportivoAltoRendimiento(null, 1, 1),
            "Se esperaba una excepción cuando el nombre es null");
    }
    
    @Test
    @DisplayName("Comprobar que el constructor no permite incremento <= 0")
    public void ClubDeportivoAltoRendimiento_incremento_menor0() {
        assertThrows(ClubException.class, () -> new ClubDeportivoAltoRendimiento("Club", 1, 0),
            "Se esperaba una excepción cuando el incremento es menor o igual a 0");
    }
    
    @Test
    @DisplayName("Comprobar que el constructor con tamaño no permite número máximo de personas <= 0")
    public void ClubDeportivoAltoRendimiento_maximo_tam_menor0() {
        assertThrows(ClubException.class, () -> new ClubDeportivoAltoRendimiento("Club", 1, 0, 1),
            "Se esperaba una excepción cuando el número máximo de personas con tamaño es menor o igual a 0");
    }
    
    @Test
    @DisplayName("Comprobar que el constructor con tamaño no permite incremento <= 0")
    public void ClubDeportivoAltoRendimiento_incremento_tam_menor0() {
        assertThrows(ClubException.class, () -> new ClubDeportivoAltoRendimiento("Club", 1, 1, -1),
            "Se esperaba una excepción cuando el incremento con tamaño es menor o igual a 0");
    }
    
    @Test
    @DisplayName("Comprobar que el constructor con tamaño no permite nombre null")
    public void ClubDeportivoAltoRendimiento_incremento_nombre_null() {
        assertThrows(ClubException.class, () -> new ClubDeportivoAltoRendimiento(null, 2, 1, 1),
            "Se esperaba una excepción cuando el nombre es null");
    }
    
    @Test
    @DisplayName("Comprobar que el tamaño del array de actividad no es menor a 5")
    public void anyadirActividad_test() throws ClubException {
        ClubDeportivoAltoRendimiento cd = new ClubDeportivoAltoRendimiento("Club", 1, 1, 1.5);
        assertThrows(ClubException.class, () -> cd.anyadirActividad(new String[] {"Futbol", "Entreno", "10", "4"}),
            "Se esperaba una excepción porque el array de actividad tiene menos de 5 elementos");
    }
    
    @Test
    @DisplayName("Comprobar que el formato de los datos de la actividad es incorrecto")
    public void anyadirActividad_formato_incorrecto() throws ClubException {
        ClubDeportivoAltoRendimiento cd = new ClubDeportivoAltoRendimiento("Club", 1, 1);
        String[] datos = {"Medio", "Zumba", "diez", "10", "25.0"};
        assertThrows(ClubException.class, () -> cd.anyadirActividad(datos),
            "Se esperaba una excepción porque el formato de los datos es incorrecto");
    }
    
    @Test
    @DisplayName("Comprobar que se puede añadir una actividad con plazas menores que el máximo permitido")
    public void anyadirActividad_plazas_menores_maximo() throws ClubException {
        // Arrange
        ClubDeportivoAltoRendimiento cd = new ClubDeportivoAltoRendimiento("Club", 10, 5);
        String[] plazasMenores = {"Medio", "Zumba", "4", "2", "25.0"};
    
        // Act
        cd.anyadirActividad(plazasMenores);
        int plazasLibres = cd.plazasLibres("Zumba");
    
        // Assert
        assertEquals(2, plazasLibres, "Las plazas disponibles después de añadir la actividad no son las esperadas.");
    }
    
    @Test
    @DisplayName("Comprobar que al añadir una actividad con plazas superiores al máximo se ajusta correctamente")
    public void anyadirActividad_plazas_superiores_maximo() throws ClubException {
        // Arrange
        ClubDeportivoAltoRendimiento cd = new ClubDeportivoAltoRendimiento("Club", 12, 5);
        String[] superaMaximo = {"123A", "natacion", "20", "10", "25.0"};
    
        // Act
        cd.anyadirActividad(superaMaximo);
        int plazasLibres = cd.plazasLibres("natacion");
    
        // Assert
        assertEquals(2, plazasLibres, "Las plazas disponibles no fueron ajustadas al máximo permitido.");
    }
    
    @Test
    @DisplayName("Comprobar que los ingresos iniciales son cero")
    public void ingresos_test() throws ClubException {
        // Arrange
        ClubDeportivoAltoRendimiento cd = new ClubDeportivoAltoRendimiento("Club", 1, 1);
        double expectedIngresos = 0.0;
    
        // Act
        double actualIngresos = cd.ingresos();
    
        // Assert
        assertEquals(expectedIngresos, actualIngresos, "Los ingresos iniciales deberían ser cero.");
    }
    
}
