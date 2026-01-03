package controle;

import java.util.HashMap;
import java.util.Map;

import entidades.Espaco;

public class RepositorioEspacos {
	private Map<Integer, Espaco> espacos = new HashMap<>(); //id
	private final String F = "espacos.dat";
}
