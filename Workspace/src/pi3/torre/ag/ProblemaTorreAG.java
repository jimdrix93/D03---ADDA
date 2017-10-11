package pi3.torre.ag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import pi3.torre.Bloque;
import pi3.torre.ProblemaTorre;
import pi3.torre.SolucionTorre;
import us.lsi.ag.ProblemaAGIndex;
import us.lsi.ag.agchromosomes.IndexChromosome;

//TODO
public class ProblemaTorreAG implements ProblemaAGIndex<List<Bloque>>{

	private List<Bloque> bloques;
	private Integer altura;
	public ProblemaTorreAG(List<Bloque> bloques, Integer altura){
		this.bloques = bloques;
		this.altura = altura;
	}
	@Override
	public Integer getObjectsNumber() {
		// TODO Auto-generated method stub
		return bloques.size();
	}
	
	@Override
	public Integer getMax(int i) {
		return bloques.get(i).getNumMaxDeUnidades();
	}

	@Override
	public Double fitnessFunction(IndexChromosome cr) {
		Double res = 0.0;
		SolucionTorre ls = getSolucion(cr);
		List<Bloque> bq = new ArrayList<Bloque>(ls.getBloques().keySet());
		if(2*bq.get(0).getNumMinDeUnidades()-bq.get(bq.size()-1).getNumMinDeUnidades()>0){
			res = (double) ls.getBeneficio() - this.bloques.size()*this.bloques.size()*(Math.abs(altura-ls.getAltura()))*(2*bq.get(0).getNumMinDeUnidades()-bq.get(bq.size()-1).getNumMinDeUnidades());
		}else{
			res = (double) ls.getBeneficio() - this.bloques.size()*this.bloques.size()*(Math.abs(altura-ls.getAltura()));
		}
		
		return res;
	}

	@Override
	public SolucionTorre getSolucion(IndexChromosome cr) {
		// TODO Auto-generated method stub
		List<Integer> ls = cr.decode();
		Map<Bloque, Integer> bs = new HashMap<Bloque, Integer>();
		IntStream.range(0, ls.size()).boxed()
		.forEach(i -> bs.put(bloques.get(i), ls.get(i)));
		return SolucionTorre.create(bs);
	}
}
