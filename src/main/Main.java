package main;

import excecoes.ClienteNaoEncontradoException;
import excecoes.EspacoIndisponivelException;
import excecoes.FalhaPersistenciaException;
import fronteira.MenuPrincipal;

public class Main {
	public static void main(String[] args) throws FalhaPersistenciaException, ClienteNaoEncontradoException, EspacoIndisponivelException {
		MenuPrincipal menu = new MenuPrincipal();
		menu.iniciaOperacao();
	}
}
