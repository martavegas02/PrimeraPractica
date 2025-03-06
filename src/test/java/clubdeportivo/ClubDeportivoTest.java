package clubdeportivo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    @DisplayName("Comprobar que al añadir una actividad con array con un grupo nulo")
    public void anyadirActividad_array_grupo_nulo() throws ClubException {
        //Arrange
        ClubDeportivo cd = new ClubDeportivo("Club", 1);
        String [] grupo1 = {null,null,"0","0","0"};
        //Act
        //Assert
        assertThrows(ClubException.class, () -> cd.anyadirActividad(grupo1));
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
        String [] grupo1 = {"123A","Kizomba","10","-10","25.0"};
        //Act
        //Assert
        assertThrows(ClubException.class, () -> cd.anyadirActividad(grupo1));
    }

    @Test
    @DisplayName("Comprobar que si el numero de personas es mayor que las plazas salte error")
    public void matricular_test() throws ClubException {
        //Arrange
        ClubDeportivo cd = new ClubDeportivo("Club", 1);
        Grupo g = new Grupo("123A","zumba",10,10,25.0);
        //Act
        cd.anyadirActividad(g);
        //Assert
        assertThrows(ClubException.class, () -> cd.matricular("zumba", 11));
    }
    @Test
    @DisplayName("Comprobar que si el numero de personas es menor que las plazas se matriculan")
    public void matricular_test2() throws ClubException {
        //Arrange
        ClubDeportivo cd = new ClubDeportivo("Club", 1);
        Grupo g = new Grupo("123A","zumba",10,0,25.0);
        //Act
        cd.anyadirActividad(g);
        cd.matricular("zumba", 5);
        //Assert
        assertEquals(5, cd.plazasLibres("zumba"));
    }

}