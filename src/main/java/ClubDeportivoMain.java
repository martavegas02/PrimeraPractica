
import clubdeportivo.ClubDeportivoAltoRendimiento;
import clubdeportivo.ClubException;

public class ClubDeportivoMain {
	public static void main(String[] args) {
		String [] grupo1 = {"123A","Kizomba","10","10","25.0"};
		
		try {// 🔹 Crear un club con máximo 10 plazas por grupo
            ClubDeportivoAltoRendimiento club = new ClubDeportivoAltoRendimiento("Club Fitness", 10, 5);

            // 🔹 Intentar añadir una actividad con 15 plazas (debe ajustarse a 10)
            String[] actividad1 = {"Zumba", "Medio", "15", "3", "30.0"};
            club.anyadirActividad(actividad1);

            // 🔹 Intentar añadir otra actividad con 8 plazas (debe mantenerse en 8)
            String[] actividad2 = {"Pilates", "Bajo", "8", "1", "25.0"};
            club.anyadirActividad(actividad2);

            // 🔹 Comprobar las plazas libres después de añadir las actividades
            System.out.println("Plazas libres en Zumba (esperado 5): " + club.plazasLibres("Zumba"));
            System.out.println("Plazas libres en Pilates (esperado 5): " + club.plazasLibres("Pilates"));
		

			
		} catch (ClubException e) {
			System.out.println(e.getMessage());
		}
		
	}
}
