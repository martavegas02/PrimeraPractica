package clubdeportivo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GrupoTest {

    @Test
    @DisplayName("Comprobar la excepción de numero de plazas")
    public void Grupo_nplazas_menor0() throws ClubException {
        //Arrange
        //Act
        //Assert
        assertThrows(ClubException.class, () -> new Grupo(null, null, 0, 0, 1));
    }

    @Test
    @DisplayName("Comprobar la excepcion de codigo nulo")
    public void Grupo_codigo_nulo() throws ClubException {
        //Arrange
        //Act
        //Assert
        assertThrows(ClubException.class, () -> new Grupo(null, "zumba", 1, 1, 1));
    }

    @Test
    @DisplayName("Comprobar la excepcion de actividad nula")
    public void Grupo_actividad_nula() throws ClubException {
        //Arrange
        //Act
        //Assert
        assertThrows(ClubException.class, () -> new Grupo("123A", null, 1, 1, 1));
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
    @Test
    public void actualizarPlazas_correcto() throws ClubException{
        //Arrange
        Grupo g = new Grupo("123A","zumba",10,10,25.0);
        //Act
        g.actualizarPlazas(15);
        //Assert
        assertEquals(15, g.getPlazas());
    }
    @Test 
    public void actualizarPlazas_NmenorIgual0() throws ClubException{
        //Arrange
        Grupo g = new Grupo("123A","zumba",10,10,25.0);
        //Act
        //Assert
        assertThrows(ClubException.class, () -> g.actualizarPlazas(0));
    }
    @Test
    public void actualizarPlazas_NmenorMatriculados() throws ClubException{
        //Arrange
        Grupo g = new Grupo("123A","zumba",10,10,25.0);
        //Act
        //Assert
        assertThrows(ClubException.class, () -> g.actualizarPlazas(9));
    }
    @Test
    public void matricular_correcto() throws ClubException{
        //Arrange
        Grupo g = new Grupo("123A","zumba",20,10,25.0);
        //Act
        g.matricular(5);
        //Assert
        assertEquals(15, g.getMatriculados());
    }
    @Test
    public void matricular_NmenorIgual0() throws ClubException{
        //Arrange
        Grupo g = new Grupo("123A","zumba",10,10,25.0);
        //Act
        //Assert
        assertThrows(ClubException.class, () -> g.matricular(0));
    }
    @Test
    public void matricular_NmayorPlazasLibres() throws ClubException{
        //Arrange
        Grupo g = new Grupo("123A","zumba",10,10,25.0);
        //Act
        //Assert
        assertThrows(ClubException.class, () -> g.matricular(1));
    }
    @Test
    public void toString_test() throws ClubException{
        //Arrange
        Grupo g = new Grupo("123A","zumba",10,10,25.0);
        //Act
        String toString = g.toString();
        //Assert
        assertEquals("(123A - zumba - 25.0 euros - P:10 - M:10)", toString);
    }
    @Test
    public void equals_trueCodigoYActividad() throws ClubException{
        //Arrange
        Grupo g1 = new Grupo("123A","zumba",10,10,25.0);
        Grupo g2 = new Grupo("123A","zumba",10,10,25.0);
        //Act
        boolean equals = g1.equals(g2);
        //Assert
        assertEquals(true, equals);
    }
    @Test
    public void equals_falseCodigoYActividad() throws ClubException{
        //Arrange
        Grupo g1 = new Grupo("123A","zumba",10,10,25.0);
        Grupo g2 = new Grupo("123B","zumba",10,10,25.0);
        //Act
        boolean equals = g1.equals(g2);
        //Assert
        assertEquals(false, equals);
    }
    @Test
    public void equals_CodigoTrue_ActividadFalse() throws ClubException{
        //Arrange
        Grupo g1 = new Grupo("123A","zumba",10,10,25.0);
        Grupo g2 = new Grupo("123A","pilates",10,10,25.0);
        //Act
        boolean equals = g1.equals(g2);
        //Assert
        assertEquals(false, equals);
    }
    @Test
    public void equals_CodigoFalse_ActividadTrue() throws ClubException{
        //Arrange
        Grupo g1 = new Grupo("123A","zumba",10,10,25.0);
        Grupo g2 = new Grupo("123B","zumba",10,10,25.0);
        //Act
        boolean equals = g1.equals(g2);
        //Assert
        assertEquals(false, equals);
    }
    @Test
    public void equals_notInstanceOfGrupo() throws ClubException {
        //Arrange
        Grupo g1 = new Grupo("123A", "zumba", 10, 10, 25.0);
        String notGrupo = "Not a Grupo instance";
        //Act
        boolean equals = g1.equals(notGrupo);
        //Assert
        assertEquals(false, equals);
    }
    @Test
    public void hashCode_correcto() throws ClubException{
        //Arrange
        Grupo g = new Grupo("123A","zumba",10,10,25.0);
        //Act
        int hashCode = g.hashCode();
        //Assert
        assertEquals(g.getCodigo().toUpperCase().hashCode()+g.getActividad().toUpperCase().hashCode(), hashCode);
    }
    
}
