package clubdeportivo;

import java.util.StringJoiner;

public class ClubDeportivo {
	private String nombre;
	private int ngrupos;
	private Grupo[] grupos;
	private static final int TAM = 10;

	public ClubDeportivo(String nombre) throws ClubException {
		this(nombre, TAM);
	}

	public ClubDeportivo(String nombre, int n) throws ClubException {
		if (n <= 0) {
			throw new ClubException("ERROR: el club no puede crearse con un número de grupos 0 o negativo");
		}
		//
		//
		//ERROR 
		// NOMBRE TIENE QUE TENER COMPROBACION DE NULL 
		if (nombre == null) {
			throw new ClubException();
		}

		this.nombre = nombre;
		//posible error porque grupos empieza en 0 y no esta permitido por la excepcion
		grupos = new Grupo[n];
	}

	private int buscar(Grupo g) {
		int i = 0;
		while (i < ngrupos && !g.equals(grupos[i])) {
			i++;
		}
		if (i == ngrupos) {
			i = -1;
		}
		return i;
	}

	public void anyadirActividad(String[] datos) throws ClubException {
		try {
			int plazas = Integer.parseInt(datos[2]);
			int matriculados = Integer.parseInt(datos[3]);
			double tarifa = Double.parseDouble(datos[4]);
			Grupo g = new Grupo(datos[0], datos[1], plazas, matriculados, tarifa);
			anyadirActividad(g);
		} catch (NumberFormatException e) {
			throw new ClubException("ERROR: formato de número incorrecto");
		}
	}

	public void anyadirActividad(Grupo g) throws ClubException {
		if (g==null){ // ADDME: anaydido para comprobar los grupos nulos
			throw new ClubException("ERROR: el grupo es nulo");
		}
		int pos = buscar(g);
		//
		//
		//ERROR
		//si el numero de grupo es menor que grupo.length añade sino no 
		//
		//
		if (pos == -1 && this.grupos.length > this.ngrupos) { // El grupo es nuevo
			grupos[ngrupos] = g;
			ngrupos++;
		} else { // El grupo ya existe --> modificamos las plazas
			grupos[pos].actualizarPlazas(g.getPlazas());
		}
	}

	public int plazasLibres(String actividad) {
		int p = 0;
		int i = 0;
		while (i < ngrupos) {
			System.out.println("Revisando grupo: " + grupos[i].getActividad() + actividad);
        	System.out.println("Plazas: " + grupos[i].getPlazas() + ", Matriculados: " + grupos[i].getMatriculados());
			int plazasLibresGrupo = grupos[i].plazasLibres();
        	System.out.println("Plazas libres en este grupo: " + plazasLibresGrupo);
			if (grupos[i].getActividad().equals(actividad)) {
				p += grupos[i].plazasLibres();
			}
			i++;
		}
		System.out.println("Total plazas libres para " + actividad + ": " + p);
		return p;
	}

	public void matricular(String actividad, int npersonas) throws ClubException {
		int plazas = plazasLibres(actividad);
		if (plazas < npersonas) {
			throw new ClubException("ERROR: no hay suficientes plazas libres para esa actividad en el club.");
		}
		int i = 0;
		while (i < ngrupos && npersonas > 0) {
			if (actividad.equals(grupos[i].getActividad())) {
				int plazasGrupo = grupos[i].plazasLibres();
				if (npersonas >= plazasGrupo) {
					grupos[i].matricular(plazasGrupo);
					npersonas -= plazasGrupo;
				} else {
					grupos[i].matricular(npersonas);
					//
					//
					//ERROR
					//HAY QUE ACTUALIZAR EL NUMERO DE PERSONAS A 0 
					npersonas = 0;
				}
			}
			i++;
		}
	}

	public double ingresos() {
		double cantidad = 0.0;
		int i = 0;
		while (i < ngrupos) {
			cantidad += grupos[i].getTarifa() * grupos[i].getMatriculados();
			i++;
		}
		return cantidad;
	}

	public String toString() {
		StringJoiner sj = new StringJoiner(", ", "[ ", " ]");
		int i = 0;
		while (i < ngrupos) {
			sj.add(grupos[i].toString());
			i++;
		}
		return nombre + " --> " + sj.toString();
	}
}
