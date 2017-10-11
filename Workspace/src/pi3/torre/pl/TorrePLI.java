package pi3.torre.pl;

import pi3.torre.Bloque;
import pi3.torre.ProblemaTorre;
import us.lsi.pl.AlgoritmoPLI;

public class TorrePLI {
	
	//TODO
	public static String getConstraints(int numBloques){
		String ret ="max: ";
		
		//Funcion a maximizar
		for(Bloque b : ProblemaTorre.listaBloques){
			if(ProblemaTorre.listaBloques.indexOf(b)==ProblemaTorre.listaBloques.size()-1){
				ret +=b.getBeneficio()+"*"+AlgoritmoPLI.getVariable("x", ProblemaTorre.listaBloques.indexOf(b))+";";
			}else{
				ret +=b.getBeneficio()+"*"+AlgoritmoPLI.getVariable("x", ProblemaTorre.listaBloques.indexOf(b))+"+ ";
		
			}
		}
		ret += "\n\n";
		
		//Restriccion altura
		for(Bloque b : ProblemaTorre.listaBloques){
			if(ProblemaTorre.listaBloques.indexOf(b)==ProblemaTorre.listaBloques.size()-1){
				ret +=b.getAltura()+"*"+AlgoritmoPLI.getVariable("x", ProblemaTorre.listaBloques.indexOf(b));
			}else{
				ret +=b.getAltura()+"*"+AlgoritmoPLI.getVariable("x", ProblemaTorre.listaBloques.indexOf(b))+"+ ";
		
			}
		}
		ret += "="+ProblemaTorre.h+";\n\n";
		
		//Restriccion variables
		for(Bloque b : ProblemaTorre.listaBloques){
			ret += AlgoritmoPLI.getVariable("x", ProblemaTorre.listaBloques.indexOf(b))+">="+b.getNumMinDeUnidades()+";\n";
		}
		ret += "\n";
		
		for(Bloque b : ProblemaTorre.listaBloques){
			ret += AlgoritmoPLI.getVariable("x", ProblemaTorre.listaBloques.indexOf(b))+"<="+b.getNumMaxDeUnidades()+";\n";
		}
		ret += "\n";
		
		boolean res = true;
		int imin = 0;
		int imax = 0;
		int min = 0;
		int max = 0;
		for(Bloque b : ProblemaTorre.listaBloques){
			imin = ProblemaTorre.listaBloques.indexOf(b);
			min = b.getAltura();
			res = false;
			if(res = false){
				if(b.getAltura()>min){
					max= b.getAltura();
					imax = ProblemaTorre.listaBloques.indexOf(b);
				}else{
					if(b.getAltura()<min){
						min = b.getAltura();
						imin = ProblemaTorre.listaBloques.indexOf(b);
					}
				}
			}
		}
		ret += 2+"*"+AlgoritmoPLI.getVariable("x", imin)+"-"+AlgoritmoPLI.getVariable("x", imax)+"<=0;\n\n";
		
		
		ret += "int ";
		for(Bloque b : ProblemaTorre.listaBloques){
			if(ProblemaTorre.listaBloques.indexOf(b)==ProblemaTorre.listaBloques.size()-1){
				ret +=AlgoritmoPLI.getVariable("x", ProblemaTorre.listaBloques.indexOf(b))+";";
			}else{
				ret +=AlgoritmoPLI.getVariable("x", ProblemaTorre.listaBloques.indexOf(b))+", ";
		
			}
		}
		return ret;
	}	
}
