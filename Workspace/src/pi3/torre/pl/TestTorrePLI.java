package pi3.torre.pl;

import java.util.HashMap;
import java.util.Map;

import pi3.torre.Bloque;
import pi3.torre.ProblemaTorre;
import pi3.torre.SolucionTorre;
import us.lsi.algoritmos.Algoritmos;
import us.lsi.pl.AlgoritmoPLI;


public class TestTorrePLI {
	
	public static void main(String[] args) {
		ProblemaTorre.create("bloques.txt");
		ProblemaTorre.h = 41;
		System.out.println("------");
		System.out.println("Altura total: " + ProblemaTorre.h);
		System.out.println("------");
		System.out.println("Bloques:\n" + ProblemaTorre.listaBloques);
		System.out.println("------");
		
		String r = TorrePLI.getConstraints(ProblemaTorre.listaBloques.size());
		//TODO
		AlgoritmoPLI a = AlgoritmoPLI.create();
		a.setConstraints(r);
		a.ejecuta();
		String res="";
		Double ben = 0.;
		for(int pos = 0; pos < a.getNumVar();pos++){
			res += a.getSolucion(pos)+" Bloques de altura "+ProblemaTorre.listaBloques.get(pos).getAltura()+"\n";
			ben += a.getSolucion(pos)*ProblemaTorre.listaBloques.get(pos).getBeneficio();
		}
		
		System.out.println("Especificación LPSolve:");
		System.out.println(r);
		System.out.println("Objetivo Solución Óptima = \n" + res);
		System.out.println("Solución Óptima = "+ben);
	}

}
