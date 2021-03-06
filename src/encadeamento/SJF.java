package encadeamento;

import includes.Funcoes;
import includes.Lista;
import includes.No;

public class SJF {


	static private Funcoes func;
	static private Lista cores;

	static private Lista Priori0;
	static private Lista Priori1;
	static private Lista Priori2;
	static private Lista Priori3;
	static private int numCores = 0, numProcs;
	
	public Funcoes getFunc() {
		return func;
	}
	public static Lista getCores() {
		return cores;
	}
	public static Lista getPrioridade(int pri) {
		if (pri == 0) {
			return Priori0;
		} else if (pri == 1) {
			return Priori1;
		} else if (pri == 2) {
			return Priori2;
		} else if (pri == 3) {
			return Priori3;
		}
		return Priori0;
	}
	public static Lista getPriori0() {
		return Priori0;
	}
	
	public static int getNumCores() {
		return numCores;
	}
	public void setNumCores(int numCores) {
		this.numCores = numCores;
	}
	public static int getNumProcs() {
		return numProcs;
	}
	public void setNumProcs(int numProcs) {
		this.numProcs = numProcs;
	}
	
	public SJF(int numCores, int numProcs) {
		// TODO Auto-generated constructor stub
		func = new Funcoes();
		cores = new Lista();
		cores.setNomeLista("Cores");
		
		cores.setDeadline(3);
		
		setNumCores(numCores);
		setNumProcs(numProcs);
		
		Lista temp = new Lista();
		temp.populaLista(getNumProcs(), true);
		System.out.println("-Processos iniciais-");
//		temp.imprime();
		Priori0 = temp;
		Priori0.setNomeLista("Aptos");
		
//		getPriori0().imprime();
		System.out.println("----");
		getPriori0().quickSort();
//		getPriori0().imprime();
		
		iniciaSJF();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SJF sjf = new SJF(4, 10);
	}
	private void iniciaSJF() {
		
		No temp = null, aux_aptos = null;
		for (int i = 0; i < getNumCores(); i++) {
			temp = getPriori0().pop_ini();
			getCores().push_fim(temp);
			getPriori0().quickSort();
		}
		
		
		while (	!getCores().isEmpty() && !getPriori0().isEmpty()
		) {
			Funcoes.waitSec(1);
			System.out.println("----------------");
			getCores().imprime();
			//tem novo proce�o?
			getCores().removeTempoExec(1);
			getCores().adicionaRelogio(1);
			

			for (int i = 0; i < getNumCores(); i++) {
//				System.out.println("-->-");
//				Funcoes.waitSec(1);
				temp = getCores().returnPos(i);
//				if(temp != null)
//					System.out.println("Nao nulo");
//				temp = getCores().returnPos(i);
//				if(temp.getRelogio() == getCores().getDeadline())
//					System.out.println("Igual");
//				System.out.println("Relogio: " + temp.getRelogio()
//								   + " e Deadline: " + getCores().getDeadline());
				if(temp!= null && (	temp.getTempExec() <= 0
									|| temp.getRelogio() == getCores().getDeadline()
								  )
				) {
//					System.out.println("Pri: "+ temp.getPriori());
					aux_aptos = temp.Entrega();
					Funcoes.repassaFila(getPriori0(), getCores(), i);
					getPriori0().push_fim(aux_aptos);
					getPriori0().removeTempoExecIgualZero();
				}
			}
			while(getCores().returnPos(getNumCores()) != null) {
				getCores().pop_fim();
			}
		}/**/
		System.out.println("Saiu");
		
	}

}
