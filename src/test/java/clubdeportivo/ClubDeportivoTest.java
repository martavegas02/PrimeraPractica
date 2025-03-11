package clubdeportivo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ClubDeportivoTest {
    
    @Test
    @DisplayName("Comprobar el constructor que el grupo no sea <=0")
    public void ClubDeportivo_nGrupos_menor0() throws ClubException {
        //Arrange
        //Act
        //Assert
        assertThrows(ClubException.class, () -> new ClubDeportivo("Club", 0));
    }
    @Test
    @DisplayName("el nombre sea correcto en el constructor")
    public void ClubDeportivo_nombre_correcto() throws ClubException {
        //Arrange
        ClubDeportivo cd = new ClubDeportivo("Club", 1);
        //Act
        String nombre = cd.toString();
        String nombreDefinitvo = nombre.split(" --> ")[0];
        //Assert
        assertEquals("Club", nombreDefinitvo);
    }
   
    @Test
    @DisplayName("Comprobar que el constructor se forma correctamente con el tamaño TAM por defecto")
    public void ClubDeportivo_constructor_tam_defecto() throws ClubException {
        //Arrange
        ClubDeportivo cd = new ClubDeportivo("Club");
        //Act
        //Assert
        assertEquals(0, cd.plazasLibres("Club"));
    }

    @Test
    @DisplayName("Comprobar que el nombre del constructor no sea nulo")
    public void ClubDeportivo_nombre_nulo() throws ClubException {
        //Arrange
        //Act
        //Assert
        assertThrows(ClubException.class, () -> new ClubDeportivo(null, 1));
    }

    @Test
    @DisplayName("Comprobar que al añadir una actividad se añada correctamente")
    public void anyadirActividad_test() throws ClubException {
        //Arrange
        ClubDeportivo cd = new ClubDeportivo("Club", 1);
        Grupo g = new Grupo("123A","zumba",10,10,25.0);
        //Act
        cd.anyadirActividad(g);
        //Assert
        assertEquals(0, cd.plazasLibres("zumba"));
    }
    @Test
    @DisplayName("Comprobar que al añadir una actividad con un grupo nulo")
    public void anyadirActividad_grupo_nulo() throws ClubException {
        //Arrange
        ClubDeportivo cd = new ClubDeportivo("Club", 1);
        //Act
        //Assert
        assertThrows(ClubException.class, () -> cd.anyadirActividad(new Grupo(null, null, 0, 0, 0)));
    }   
    @Test  
    @DisplayName("Comprobar que al añadir una actividad con array se añade")
    public void anyadirActividad_array_test() throws ClubException {
        //Arrange
        ClubDeportivo cd = new ClubDeportivo("Club", 1);
        String [] grupo1 = {"123A","Kizomba","10","10","25.0"};
        //Act
        cd.anyadirActividad(grupo1);
        //Assert
        assertEquals(0, cd.plazasLibres("Kizomba"));
    }
    @Test
    @DisplayName("Comprobar que si el grupo es nullo salte una excepcion")
    public void anyadirActividad_grupo_nulo_array() throws ClubException {
        //Arrange
        ClubDeportivo cd = new ClubDeportivo("Club", 1);
        Grupo g = null;
        //Act
        //Assert
        assertThrows(ClubException.class, () -> cd.anyadirActividad(g));
    }
    @Test
    @DisplayName("comprobar que añado una actividad ya existente se actualizan las plazqas")
    public void anyadirActividad_grupo_existente() throws ClubException {
        //Arrange
        ClubDeportivo cd = new ClubDeportivo("Club", 1);
        Grupo g = new Grupo("123A","zumba",10,10,25.0);
        Grupo g2 = new Grupo("123A","zumba",10,10,25.0);
        //Act
        cd.anyadirActividad(g);
        cd.anyadirActividad(g2);
        //Assert
        assertEquals(0, cd.plazasLibres("zumba"));
    }
    @Test
    @DisplayName("Comprobar que al añadir una actividad con un formato incorrecto")
    public void anyadirActividad_formato_incorrecto() throws ClubException {
        //Arrange
        ClubDeportivo cd = new ClubDeportivo("Club", 1);
        String [] grupo1 = {"123A","Kizomba","10","diez","25.0"};
        //Act
        //Assert
        assertThrows(ClubException.class, () -> cd.anyadirActividad(grupo1));
    }
    @Test
    @DisplayName("Comprobar plazas libres para múltiples actividades")
    public void plazasLibres_multiplesActividades() throws ClubException {
        //Arrange
        ClubDeportivo cd = new ClubDeportivo("Club", 3);
        Grupo g1 = new Grupo("123A", "zumba", 10, 5, 25.0);
        Grupo g2 = new Grupo("123B", "pilates", 5, 3, 25.0);
        Grupo g3 = new Grupo("123C", "zumba", 8, 2, 25.0);
        cd.anyadirActividad(g1);
        cd.anyadirActividad(g2);
        cd.anyadirActividad(g3);
        //Act
        int plazasLibresZumba = cd.plazasLibres("zumba");
        int plazasLibresPilates = cd.plazasLibres("pilates");
        int total = plazasLibresPilates + plazasLibresZumba;
        //Assert
        assertEquals(13, total);
    }
    @Test
    @DisplayName("Matricular test")
    void matricular_test() throws ClubException {
        ClubDeportivo club = new ClubDeportivo("Club", 2);
        Grupo gTest = new Grupo("123A", "Pilates", 8, 5, 50.0);
        club.anyadirActividad(gTest);
        Grupo gTest2 = new Grupo("123B", "Zumba", 12, 5, 50.0);
        club.anyadirActividad(gTest2);

        club.matricular("Pilates", 3);

        int plazasLibres = club.plazasLibres("Pilates");
        assertTrue(plazasLibres == 8-5-3);
    }

    @Test
    @DisplayName("Matricular personas distintos grupos test")
    void matricular_personasDistintosGrupos_test() throws ClubException {
        ClubDeportivo club = new ClubDeportivo("Club", 3);
        Grupo gTest = new Grupo("456B", "Pilates", 8, 5, 50.0);
        club.anyadirActividad(gTest);
        Grupo gTest2 = new Grupo("4789B", "Zumba", 12, 5, 50.0);
        club.anyadirActividad(gTest2);
        Grupo gTest3 = new Grupo("3978B", "Pilates", 12, 6, 50.0);
        club.anyadirActividad(gTest3);

        club.matricular("Pilates", 5);

        int plazasLibres = club.plazasLibres("Pilates");
        assertTrue(plazasLibres == 8+12-5-6-5);
    }

    @Test
    @DisplayName("Matricular test error")
    void matricular_test_error() throws ClubException {
        ClubDeportivo club = new ClubDeportivo("Club", 1);
        Grupo gTest = new Grupo("456B", "Pilates", 8, 5, 50.0);
        club.anyadirActividad(gTest);

        assertThrows(ClubException.class, () -> club.matricular("Pilates", 10));
    }
    
    @Test
    @DisplayName("comprobar que los ingresos son correctos")
    public void ingresos_test() throws ClubException {
        //Arrange
        ClubDeportivo cd = new ClubDeportivo("Club", 1);
        Grupo g = new Grupo("123A","zumba",10,0,25.0);
        //Act
        cd.anyadirActividad(g);
        cd.matricular("zumba", 5);
        //Assert
        assertEquals(125.0, cd.ingresos());
    }
    @Test
    @DisplayName("comprobar que los ingresos son correctos con varios grupos")
    public void ingresos_test2() throws ClubException {
        //Arrange
        ClubDeportivo cd = new ClubDeportivo("Club", 2);
        Grupo g = new Grupo("123A","zumba",10,5,25.0);
        Grupo g2 = new Grupo("123B","zumba",5,4,25.0);
        //Act
        cd.anyadirActividad(g);
        cd.anyadirActividad(g2);
        //Assert
        assertEquals(225.0, cd.ingresos());
    }
    @Test
    @DisplayName("comprobar que el toString es correcto")
    public void toString_test() throws ClubException {
        //Arrange
        ClubDeportivo cd = new ClubDeportivo("Club", 3);
        Grupo g = new Grupo("123A","zumba",10,0,25.0);
        Grupo g2 = new Grupo("123B","zumba",5,0,25.0);
        //Act
        cd.anyadirActividad(g);
        cd.anyadirActividad(g2);
        //Assert
        assertEquals("Club --> [ (123A - zumba - 25.0 euros - P:10 - M:0), (123B - zumba - 25.0 euros - P:5 - M:0) ]", cd.toString());
    }

}