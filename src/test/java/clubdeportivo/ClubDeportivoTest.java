// Marta Vegas Cuevas 
//Guillermo Sanz Nieto
//Grupo G
package clubdeportivo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ClubDeportivoTest {
    
    @Test
    @DisplayName("Comprobar que el constructor no permite número de grupos <= 0")
    public void ClubDeportivo_nGrupos_menor0() {
        // Arrange & Act & Assert
        assertThrows(ClubException.class, () -> new ClubDeportivo("Club", 0),
            "Se esperaba una excepción cuando el número de grupos es menor o igual a 0");
    }
    
    @Test
    @DisplayName("Comprobar que el nombre es correcto en el constructor")
    public void ClubDeportivo_nombre_correcto() throws ClubException {
        // Arrange
        ClubDeportivo cd = new ClubDeportivo("Club", 1);
    
        // Act
        String nombre = cd.toString();
        String nombreDefinitvo = nombre.split(" --> ")[0];
    
        // Assert
        assertEquals("Club", nombreDefinitvo, "El nombre obtenido del toString() no coincide con el esperado");
    }
    
    @Test
    @DisplayName("Comprobar que el constructor se forma correctamente con el tamaño TAM por defecto")
    public void ClubDeportivo_constructor_tam_defecto() throws ClubException {
        // Arrange
        ClubDeportivo cd = new ClubDeportivo("Club");
        int expectedPlazas = 0; // Verificar que no hay grupos inicializados
    
        // Act
        int actualPlazas = cd.plazasLibres("Club");
    
        // Assert
        assertEquals(expectedPlazas, actualPlazas, "El número de plazas libres por defecto no es el esperado");
    }
    
    @Test
    @DisplayName("Comprobar que el nombre del constructor no sea nulo")
    public void ClubDeportivo_nombre_nulo() {
        // Arrange & Act & Assert
        assertThrows(ClubException.class, () -> new ClubDeportivo(null, 1),
            "Se esperaba una excepción cuando el nombre es nulo");
    }
    

    @Test
    @DisplayName("Comprobar que al añadir una actividad se añada correctamente")
    public void anyadirActividad_test() throws ClubException {
        // Arrange
        ClubDeportivo cd = new ClubDeportivo("Club", 1);
        Grupo g = new Grupo("123A", "zumba", 10, 10, 25.0);
        int expectedPlazas = 0;

        // Act
        cd.anyadirActividad(g);
        int actualPlazas = cd.plazasLibres("zumba");

        // Assert
        assertEquals(expectedPlazas, actualPlazas, "Las plazas libres no se actualizaron correctamente.");
    }

    @Test
    @DisplayName("Comprobar que el String[] de datos no falten datos")
    public void anyadirActividad_faltanDatos() throws ClubException {
        // Arrange
        ClubDeportivo cd = new ClubDeportivo("Club", 1);

        // Act & Assert
        assertThrows(ClubException.class, () -> cd.anyadirActividad(new String[] {"Futbol", "Entreno", "10", "4"}),
            "Se esperaba una excepción porque el array de actividad tiene menos de 5 elementos");
    }


    @Test
    @DisplayName("Comprobar que al añadir una actividad con un grupo nulo")
    public void anyadirActividad_grupo_nulo() throws ClubException {
        // Arrange
        ClubDeportivo cd = new ClubDeportivo("Club", 1);

        // Act & Assert
        assertThrows(ClubException.class, () -> cd.anyadirActividad(new Grupo(null, null, 0, 0, 0)),
            "Se esperaba una excepción al intentar añadir un grupo nulo.");
    }

    @Test
    @DisplayName("Comprobar que al añadir una actividad con array se añade correctamente")
    public void anyadirActividad_array_test() throws ClubException {
        // Arrange
        ClubDeportivo cd = new ClubDeportivo("Club", 1);
        String[] grupo1 = {"123A", "Kizomba", "10", "10", "25.0"};
        int expectedPlazas = 0;

        // Act
        cd.anyadirActividad(grupo1);
        int actualPlazas = cd.plazasLibres("Kizomba");

        // Assert
        assertEquals(expectedPlazas, actualPlazas, "Las plazas libres no se actualizaron correctamente.");
    }

    @Test
    @DisplayName("Comprobar que si el grupo es nulo salta una excepción")
    public void anyadirActividad_grupo_nulo_array() throws ClubException {
        // Arrange
        ClubDeportivo cd = new ClubDeportivo("Club", 1);
        Grupo g = null;

        // Act & Assert
        assertThrows(ClubException.class, () -> cd.anyadirActividad(g),
            "Se esperaba una excepción al intentar añadir un grupo nulo.");
    }

    @Test
    @DisplayName("Comprobar que al añadir una actividad ya existente se actualizan las plazas")
    public void anyadirActividad_grupo_existente() throws ClubException {
        // Arrange
        ClubDeportivo cd = new ClubDeportivo("Club", 1);
        Grupo g = new Grupo("123A", "zumba", 10, 10, 25.0);
        Grupo g2 = new Grupo("123A", "zumba", 10, 10, 25.0);
        int expectedPlazas = 0;

        // Act
        cd.anyadirActividad(g);
        cd.anyadirActividad(g2);
        int actualPlazas = cd.plazasLibres("zumba");

        // Assert
        assertEquals(expectedPlazas, actualPlazas, "Las plazas no se actualizaron correctamente al añadir una actividad ya existente.");
    }

    @Test
    @DisplayName("Comprobar que al añadir una actividad con un formato incorrecto salta una excepción")
    public void anyadirActividad_formato_incorrecto() throws ClubException {
        // Arrange
        ClubDeportivo cd = new ClubDeportivo("Club", 1);
        String[] grupo1 = {"123A", "Kizomba", "10", "diez", "25.0"};

        // Act & Assert
        assertThrows(ClubException.class, () -> cd.anyadirActividad(grupo1),
            "Se esperaba una excepción al proporcionar un formato incorrecto en los datos del grupo.");
    }

    @Test
    @DisplayName("Comprobar que al añadir un grupo si el grupo es nuevo pero el club está lleno salta una excepción")
    public void anyadirActividad_grupo_nuevo_club_lleno() throws ClubException {
        // Arrange
        ClubDeportivo cd = new ClubDeportivo("Club", 1);
        Grupo g = new Grupo("123A", "zumba", 10, 10, 25.0);
        Grupo g2 = new Grupo("123B", "zumba", 10, 10, 25.0);

        // Act
        cd.anyadirActividad(g);
        // Act & Assert
        assertThrows(ClubException.class, () -> cd.anyadirActividad(g2),
            "Se esperaba una excepción al intentar añadir un grupo cuando el club está lleno.");
    }


    @Test
    @DisplayName("Comprobar plazas libres para múltiples actividades")
    public void plazasLibres_multiplesActividades() throws ClubException {
        // Arrange
        ClubDeportivo cd = new ClubDeportivo("Club", 3);
        Grupo g1 = new Grupo("123A", "zumba", 10, 5, 25.0);
        Grupo g2 = new Grupo("123B", "pilates", 5, 3, 25.0);
        Grupo g3 = new Grupo("123C", "zumba", 8, 2, 25.0);
        cd.anyadirActividad(g1);
        cd.anyadirActividad(g2);
        cd.anyadirActividad(g3);

        int expectedPlazasLibresZumba = (10 - 5) + (8 - 2); // 5 + 6 = 11
        int expectedPlazasLibresPilates = 5 - 3; // 2
        int expectedTotalPlazasLibres = expectedPlazasLibresZumba + expectedPlazasLibresPilates; // 11 + 2 = 13

        // Act
        int actualPlazasLibresZumba = cd.plazasLibres("zumba");
        int actualPlazasLibresPilates = cd.plazasLibres("pilates");
        int actualTotalPlazasLibres = actualPlazasLibresZumba + actualPlazasLibresPilates;

     // Assert
        assertEquals(expectedTotalPlazasLibres, actualTotalPlazasLibres, "El cálculo de plazas libres para múltiples actividades es incorrecto.");
    }

    @Test
    @DisplayName("Matricular personas en un grupo con plazas disponibles")
    void matricular_test() throws ClubException {
        // Arrange: Configuración inicial
        ClubDeportivo club = new ClubDeportivo("Club", 2);
        Grupo gTest = new Grupo("123A", "Pilates", 8, 5, 50.0);
        club.anyadirActividad(gTest);
        Grupo gTest2 = new Grupo("123B", "Zumba", 12, 5, 50.0);
        club.anyadirActividad(gTest2);
        int expectedPlazasLibres = 8 - 5 - 3;
    
        // Act: Matricular 3 personas en Pilates
        club.matricular("Pilates", 3);
    
        // Assert: Verificar que las plazas libres se redujeron correctamente
        int plazasLibres = club.plazasLibres("Pilates");
        assertEquals(expectedPlazasLibres, plazasLibres, "El número de plazas libres después de matricular es incorrecto");
    }
    
    @Test
    @DisplayName("Matricular personas en distintos grupos con la misma actividad")
    void matricular_personasDistintosGrupos_test() throws ClubException {
        // Arrange: Configuración inicial con múltiples grupos de Pilates
        ClubDeportivo club = new ClubDeportivo("Club", 3);
        Grupo gTest = new Grupo("456B", "Pilates", 8, 5, 50.0);
        club.anyadirActividad(gTest);
        Grupo gTest2 = new Grupo("4789B", "Zumba", 12, 5, 50.0);
        club.anyadirActividad(gTest2);
        Grupo gTest3 = new Grupo("3978B", "Pilates", 12, 6, 50.0);
        club.anyadirActividad(gTest3);
        int expectedPlazasLibres = (8 + 12) - (5 + 6) - 5; // Suma de plazas de Pilates, menos matriculados
    
        // Act: Matricular 5 personas en Pilates (se reparten en los grupos)
        club.matricular("Pilates", 5);
    
        // Assert: Verificar que las plazas se redujeron correctamente
        int plazasLibres = club.plazasLibres("Pilates");
        assertEquals(expectedPlazasLibres, plazasLibres, "El número de plazas libres después de matricular en múltiples grupos es incorrecto");
    }
    
    @Test
    @DisplayName("No se pueden matricular más personas de las plazas disponibles")
    void matricular_test_error() throws ClubException {
        // Arrange: Club con un solo grupo con plazas insuficientes
        ClubDeportivo club = new ClubDeportivo("Club", 1);
        Grupo gTest = new Grupo("456B", "Pilates", 8, 5, 50.0);
        club.anyadirActividad(gTest);
    
        // Act & Assert: Se espera que lanzar una excepción al intentar matricular a más personas de las disponibles
        assertThrows(ClubException.class, () -> club.matricular("Pilates", 10),
            "Se esperaba una excepción al intentar matricular más personas de las disponibles");
    }
    
    @Test
    @DisplayName("Comprobar que los ingresos son correctos")
    public void ingresos_testCorrecto() throws ClubException {
        // Arrange: Configuración inicial
        ClubDeportivo cd = new ClubDeportivo("Club", 1);
        Grupo g = new Grupo("123A", "zumba", 10, 0, 25.0);
        cd.anyadirActividad(g);
        cd.matricular("zumba", 5);
        double expected = 5 * 25.0; // 125.0

        // Act: Obtener ingresos
        double actual = cd.ingresos();

        // Assert: Verificar que los ingresos sean los esperados
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Comprobar que los ingresos son correctos con varios grupos")
    public void ingresos_testCorrectoConVariosGrupos() throws ClubException {
        // Arrange: Configuración de datos y resultado esperado
        ClubDeportivo cd = new ClubDeportivo("Club", 2);
        Grupo g = new Grupo("123A", "zumba", 10, 5, 25.0);
        Grupo g2 = new Grupo("123B", "zumba", 5, 4, 25.0);
        cd.anyadirActividad(g);
        cd.anyadirActividad(g2);
        double expected = (5 * 25.0) + (4 * 25.0); // 125.0 + 100.0 = 225.0

        // Act: Ejecutar la acción bajo prueba
        double actual = cd.ingresos();

        // Assert: Comparar el resultado obtenido con el esperado
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Comprobar que el toString es correcto")
    public void toString_test() throws ClubException {
        // Arrange: Configurar los objetos y la expectativa esperada
        ClubDeportivo cd = new ClubDeportivo("Club", 3);
        Grupo g = new Grupo("123A", "zumba", 10, 0, 25.0);
        Grupo g2 = new Grupo("123B", "zumba", 5, 0, 25.0);
        cd.anyadirActividad(g);
        cd.anyadirActividad(g2);
        String expected = "Club --> [ (123A - zumba - 25.0 euros - P:10 - M:0), (123B - zumba - 25.0 euros - P:5 - M:0) ]";
    
        // Act: Obtener el resultado del método toString()
        String actual = cd.toString();
    
        // Assert: Comparar el resultado con lo esperado
        assertEquals(expected, actual);
    }
    

}