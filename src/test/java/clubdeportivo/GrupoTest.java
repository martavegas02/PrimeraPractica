package clubdeportivo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GrupoTest {
    @Test
    @DisplayName("Test Grupo")
    public void Grupo_nplazas_menor0() throws ClubException {
        //Arrange
        //Act
        //Assert
        assertThrows(ClubException.class, () -> new Grupo(null, null, 0, 0, 1));
    }

    @Test
    public void Grupo_matriculados_menor0() throws ClubException {
        //Arrange
        //Act
        //Assert
        assertThrows(ClubException.class, () -> new Grupo(null, null, 1, -4, 1));
    }
    
    @Test
    public void Grupo_tarifa_menor0() throws ClubException {
        //Arrange
        //Act
        //Assert
        assertThrows(ClubException.class, () -> new Grupo(null, null, 1, 1, 0));
    }

    @Test
    public void Grupo_nmatriculados_mayorNumPlazas() throws ClubException {
        //Arrange
        //Act
        //Assert
        assertThrows(ClubException.class, () -> new Grupo(null, null, 3, 4, 1));
    }
    @Test
    public void getCodigo_test() throws ClubException{
        //Arrange
        Grupo g = new Grupo("123A","zumba",10,10,25.0);
        //Act
        String codigo = g.getCodigo();
        //Assert
        assertEquals("123A", codigo);
    }
    @Test
    public void getActividad_test() throws ClubException{
        //Arrange
        Grupo g = new Grupo("123A","zumba",10,10,25.0);
        //Act
        String actividad = g.getActividad();
        //Assert
        assertEquals("zumba", actividad);
    }
    @Test
    public void getPlazas_test() throws ClubException{
        //Arrange
        Grupo g = new Grupo("123A","zumba",10,10,25.0);
        //Act
        int plazas = g.getPlazas();
        //Assert
        assertEquals(10, plazas);
    }
    @Test  
    public void getMatriculados_test() throws ClubException{
        //Arrange
        Grupo g = new Grupo("123A","zumba",10,10,25.0);
        //Act
        int matriculados = g.getMatriculados();
        //Assert
        assertEquals(10, matriculados);
    }
    @Test
    public void getTarifa_test() throws ClubException{
        //Arrange
        Grupo g = new Grupo("123A","zumba",10,10,25.0);
        //Act
        double tarifa = g.getTarifa();
        //Assert
        assertEquals(25.0, tarifa);
    }
    @Test
    public void plazasLibres_test() throws ClubException{
        //Arrange
        Grupo g = new Grupo("123A","zumba",10,10,25.0);
        //Act
        int plazasLibres = g.plazasLibres();
        //Assert
        assertEquals(0, plazasLibres);
    }
    
}
