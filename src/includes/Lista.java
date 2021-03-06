package includes;

import java.util.Random;

public class Lista {

	No head, tail;
	int qtdNos, Deadline;
	String nomeLista;
	boolean ativaLogs = false;
	private int tamanho = 0;
	private int espacoDesalocado = 0 ;


	public Lista() {
		// TODO Auto-generated constructor stub
		setHead(null);
		setTail(null);
		setQtdNos(0);
		setNomeLista("");
	}

	public String getNomeLista() {
		return nomeLista;
	}
	public void setNomeLista(String nomeLista) {
		this.nomeLista = nomeLista;
	}
	public int getTamanho() {
		return tamanho;
	}
	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
		setDesalocado(tamanho);
	}
	public int getDesalocado() {
		return espacoDesalocado;
	}
	public void setDesalocado(int Desalocado) {
		this.espacoDesalocado = Desalocado;
	}

	public int getDeadline() {
		return this.Deadline;
	}
	public void setDeadline(int deadline) {
		this.Deadline = deadline;
	}

	public int getQtdNos() {
		return this.qtdNos;
	}
	public void setQtdNos(int qtdNos) {
		this.qtdNos = qtdNos;
	}

	public boolean getAtivaLogs() {
		return this.ativaLogs;
	}
	public void setAtivaLogs(boolean ativaLogs) {
		this.ativaLogs = ativaLogs;
	}

	public No getHead() {
		return this.head;
	}
	public void setHead(No head) {
		this.head = head;
	}
	public No getTail() {
		return this.tail;
	}
	public void setTail(No tail) {
		this.tail = tail;
	}

	public boolean isEmpty() {
		boolean temp = true;
		if (getHead() !=  null) {
			temp = false;
		}
		return temp;
	}

	public void imprime() {
		if (isEmpty()) {
			System.out.println("imprime: Lista Vazia");
		} else {
			No temp = getHead();
			System.out.println(getNomeLista());
			while (temp != null) {
				System.out.println("' ID: '"+ temp.getID() +"'"
								+ "' Cor: '"+ temp.getCor() +"'"
								+ "' Tamanho Ori: '"+ temp.getTamanhoOrig() +"'"
								+ "' Tamanho Usa: '"+ temp.getTamanhoUsado() +"'");

				if (temp.getNext() != null) {
					temp = temp.getNext();
				} else {
					break;
				}
			}
		}
	}
	public void updTamanho() {
		if (isEmpty()) {
			System.out.println("imprime: Lista Vazia");
		} else {
			No temp = getHead();
			System.out.println(getNomeLista());
			while (temp != null) {
				temp.setTamanhoOrig(temp.getTamanhoUsado());

				if (temp.getNext() != null) {
					temp = temp.getNext();
				} else {
					break;
				}
			}
		}
	}
	public void imprimeMem() {
		if (isEmpty()) {
			System.out.println("imprime: Lista Vazia");
		} else {
			System.out.println(getNomeLista());
			No temp = getHead();
			while (temp.getNext() != null) {
				System.out.println("ID: '" + temp.getID()
								+ "' Tamanho Usado: '"+ temp.getTamanhoUsado() +"'"
								+ "' Tamanho Original: '"+ temp.getTamanhoOrig() +"'");

				temp = temp.getNext();
			}
			System.out.println("ID: '" + temp.getID()
			+ "' Tamanho Usado: '"+ temp.getTamanhoUsado() +"'"
			+ "' Tamanho Original: '"+ temp.getTamanhoOrig() +"'");
		}
	}
	public void imprimeID() {
		if (isEmpty()) {
			System.out.println("imprime: Lista Vazia");
		} else {
			System.out.println(getNomeLista());
			No temp = getHead();
			while (temp.getNext() != null) {
				System.out.print("ID.: '" + temp.getID());
				System.out.print(" -> ");
				temp = temp.getNext();
			}
			System.out.println("ID.: '" + temp.getID());
		}
	}
	public No buscaID(double ID) {
		No ret = null;
		if (!isEmpty()) {
			No temp = getHead();
			while (temp.getNext() != null) {
				if(temp.getID() == ID) {
					break;
				}
				temp = temp.getNext();
			}
			if(temp.getID() == ID) {
				ret = temp;
			}
		}
		return ret;
	}

	public void up_estados() {
		if (isEmpty()) {
			if (ativaLogs) {
				System.out.println("up_estados: Lista Vazia");
			}
		} else if (getHead() == getTail()) {
			if (ativaLogs) {
				System.out.println("up_estados: Não atuaizado, Unico processo.");
			}
		} else {
			int estado = 1;  // 1- pronto, 2- Esperando, 3- Executando;
			up_estados_all(estado);
			getHead().setEstado(2);
		}
	}
	public void up_estados_all(int est) {
		int estado = 1;  // 1- pronto, 2- Esperando, 3- Executando;
		No temp = (getHead() != null) ? getHead() : null;
		while (temp != null && temp.getNext() != null) {
			temp.setEstado(estado);

			temp = temp.getNext();
		}
		if (temp != null) {
			temp.setEstado(estado);
		}
	}


	public void push_fim(int Quantum) {
		No novo = new No(Quantum);
//		No novo = new No(true, Quantum);
		if (isEmpty()) {
			if (getAtivaLogs()) {
				System.out.println("push_fim1: Lista Vazia, adicionando inicio.");
			}
			setHead(novo);
			setTail(novo);
		} else {
			No temp = getTail();

			temp.setNext(novo);;
			novo.setBack(temp);
			setTail(novo);
		}
		setQtdNos(getQtdNos() +1);
	}
	public void push_fim(No no) {
		if (no == null) {
			if (getAtivaLogs()) {
				System.out.println("push_fim2: No Vazio, não adicionado.");
			}
		} else {
			No novo = no.Entrega();
			if (isEmpty()) {
				if (getAtivaLogs()) {
					System.out.println("push_fim2: Lista Vazia, adicionando inicio.");
				}
				setHead(novo);
				setTail(novo);
			} else {
				No temp = getTail();

				temp.setNext(novo);;
				novo.setBack(temp);
				setTail(novo);
			}
			setQtdNos(getQtdNos() +1);
		}
	}
	public void push_fim(int Quantum, int Priori) {
		No novo = new No(Quantum, Priori);
		if (isEmpty()) {
			if (getAtivaLogs()) {
				System.out.println("push_fim3: Lista Vazia, adicionando inicio.");
			}
			setHead(novo);
			setTail(novo);
		} else {
			No temp = getTail();

			temp.setNext(novo);;
			novo.setBack(temp);
			setTail(novo);
		}
		setQtdNos(getQtdNos() +1);
	}
	public void push_fim(int Quantum, int tempExec, int Priori) {
		No novo = new No(Quantum, tempExec, Priori);
		if (isEmpty()) {
			if (getAtivaLogs()) {
				System.out.println("push_fim3: Lista Vazia, adicionando inicio.");
			}
			setHead(novo);
			setTail(novo);
		} else {
			No temp = getTail();

			temp.setNext(novo);;
			novo.setBack(temp);
			setTail(novo);
		}
		setQtdNos(getQtdNos() +1);
	}
	public void push_ini(int Quantum) {
		No novo = new No(Quantum);
		if (isEmpty()) {
			if (getAtivaLogs()) {
				System.out.println("push_ini1: Lista Vazia, adicionando inicio.");
			}
			setHead(novo);
			setTail(novo);
		} else {
			No temp = getHead();

			novo.setNext(temp);;
			temp.setBack(novo);
			setHead(novo);;
		}
		setQtdNos(getQtdNos() +1);
	}
	public void push_ini_no(No node) {
		No novo = node;
		if (isEmpty()) {
			if (getAtivaLogs()) {
				System.out.println("push_ini2: Lista Vazia, adicionando inicio.");
			}
			setHead(novo);
			setTail(novo);
		} else {
			No temp = getHead();

			novo.setNext(temp);;
			temp.setBack(novo);
			setHead(novo);;
		}
		setQtdNos(getQtdNos() +1);
	}
	public void push_fim_no(No node) {
		No novo = node;
		if (isEmpty()) {
			if (getAtivaLogs()) {
				System.out.println("push_ini2: Lista Vazia, adicionando inicio.");
			}
			setHead(novo);
			setTail(novo);
		} else {
			No temp = getTail();

			novo.setBack(temp);
			temp.setNext(novo);
			setTail(novo);
		}
		setQtdNos(getQtdNos() +1);

	}
	public void push_pos(int index, int Quantum) {
		if(isEmpty() || index < 1) {
			push_ini(Quantum);
		} else {
			No novo = new No(Quantum);
			No temp = getHead();
			int cont = 0;
			while(cont < index && temp != null) {
				temp = temp.getNext();
				cont++;
			}
			if(temp == null) {
				push_fim(Quantum);
			} else {
				novo.setNext(temp);
				temp.getBack().setNext(novo);
				novo.setBack(temp.getBack());
				temp.setBack(novo);
			}
			setQtdNos(getQtdNos() +1);
		}
	}
	public void push_pos_no(int index, No node) {
		No novo = node;
		if(isEmpty() || index < 1) {
			push_ini_no(novo);
		} else {
			No temp = getHead();
			int cont = 0;
			while(cont < index && temp != null) {
				temp = temp.getNext();
				cont++;
			}
			if(temp == null) {
				push_fim(novo);
			} else {
				novo.setNext(temp);
				temp.getBack().setNext(novo);
				novo.setBack(temp.getBack());
				temp.setBack(novo);
			}
			setQtdNos(getQtdNos() +1);
		}
	}

	public No pop_fim() {
		No retorno  = null;
		if (isEmpty()) {
			if (getAtivaLogs()) {
				System.out.println("pop_fim1: Não removido, lista vazia.");
			}
		} else if (getHead() == getTail()) {
			retorno = getHead();
			setHead(null);
			setTail(null);

			setQtdNos(getQtdNos() -1);
		} else {
			retorno = getTail();

			setTail(getTail().getBack());
			getTail().setNext(null);
			setQtdNos(getQtdNos() -1);
		}
		return retorno;
	}
	public No pop_ini() {
		No retorno  = null;
		if (isEmpty()) {
			if (getAtivaLogs()) {
				System.out.println("pop_fim2: Não removido, lista vazia.");
			}
		} else if (getHead() == getTail()) {
			if (getAtivaLogs()) {
				System.out.println("pop_fim2: Removendo unico.");
			}
			retorno = getHead().Entrega();
			setHead(null);
			setTail(null);

			setQtdNos(getQtdNos() -1);
		} else {
			if (getAtivaLogs()) {
				System.out.println("pop_fim2: Removido primeiro.");
			}
			retorno = getHead().Entrega();

			setHead(getHead().getNext());


			setQtdNos(getQtdNos() -1);
		}
		return retorno;
	}
	public No pop_pos(int index) {
		No rem = null;
		if(isEmpty() || index < 1) {
			rem = pop_ini();
		} else {
			No aux = getHead();
			rem = aux;
			int cont = 0;
			while(cont < index && rem != null) {
				aux = rem;
				rem = rem.getNext();
				cont++;
			}
			if(rem == null) {
				rem = pop_fim();
			} else {
				aux.setNext(rem.getNext());
				if (rem.getNext() != null) {
					rem.getNext().setBack(aux);
				}
			}
		}

		return rem;

	}
	public No pop_ID(double ID) {
		No ret = null;
		if(!isEmpty() && getHead() == getTail()) {
			pop_fim();
		} else if (!isEmpty()) {
			No temp = getHead();
			while (temp.getNext() != null) {
				if(temp.getID() == ID) {
					break;
				}
				temp = temp.getNext();
			}
			if(temp.getID() == ID) {
				ret = temp;
			}
			
			if (ret != null) {
				No ant = ret.getBack();
				No post = ret.getNext();
				
				if (ant != null && post != null) {
					ant.setNext(post);
					post.setBack(ant);
				} else if(ant == null && post == null) {
					//
				} else {
					if (ant == null) {
						post.setBack(ant);
						setHead(post);
					}
					if (post == null) {
						ant.setNext(post);
						setTail(ant);
					}
				}
				ret.setNext(null);
				ret.setBack(null);
			}
		}
		return ret;

	}


	public int populaLista(int numNos) {
		int auto_increment = 0;

		if(!isEmpty()) {
			if (getAtivaLogs()) {
				System.out.println("populaLista 1: Já existe algo na arvore");
			}
		} else {
			Random random = new Random();
			int valor;
			auto_increment = 1;
			for (int i = 0; i < numNos; i++) {
				valor = random.nextInt(21);
				valor = valor < 4 ? 4 : valor;

				push_fim(valor);
				getTail().setID(auto_increment);
				auto_increment++;
			}
		}
		return auto_increment;
	}
	public int populaLista(int numNos, boolean usaPrioridade) {
		int auto_increment = 0;
		if(!usaPrioridade) {
			auto_increment++;
//			push_fim(6);
//			getTail().setID(auto_increment++);
//			getTail().setTamanhoUsado(100);
//			push_fim(8);
//			getTail().setID(auto_increment++);
//			getTail().setTamanhoUsado(200);
//			push_fim(8);
//			getTail().setID(auto_increment++);
//			getTail().setTamanhoUsado(300);
//			push_fim(8);
//			getTail().setID(auto_increment++);
//			getTail().setTamanhoUsado(400);
//			push_fim(8);
//			getTail().setID(auto_increment++);
//			getTail().setTamanhoUsado(500);
//			push_fim(8);
//			getTail().setID(auto_increment++);
//			getTail().setTamanhoUsado(500);
//			push_fim(8);
//			getTail().setID(auto_increment++);
//			getTail().setTamanhoUsado(500);
//			push_fim(8);
//			getTail().setID(auto_increment++);
//			getTail().setTamanhoUsado(500);
			push_fim(8);
			getTail().setID(auto_increment++);
			getTail().setTamanhoUsado(500);
			//push_fim(16);
			//getTail().setID(auto_increment++);
			//getTail().setTamanhoUsado(50);
			
//			push_fim(12);
//			push_fim(12);
//			push_fim(12);
//			push_fim(9);
//			push_fim(9);
//			push_fim(9);

//			push_fim(3, 0);
//			push_fim(5, 0);
//			push_fim(6, 0);
//			push_fim(7, 0);
//			push_fim(3, 1);
//			push_fim(6, 1);
//			push_fim(6, 1);
//			push_fim(7, 1);
//			push_fim(3, 2);
//			push_fim(7, 2);
//			push_fim(6, 2);
//			push_fim(7, 2);
//			push_fim(3, 3);
//			push_fim(8, 3);
//			push_fim(6, 3);
//			push_fim(7, 3);


//			push_fim(7, 0);
//			push_fim(3, 1);
//			push_fim(6, 1);
//			push_fim(8, 3);
//			push_fim(6, 3);
//			push_fim(7, 3);
//			push_fim(2, 3);
//			push_fim(2, 3);
//			push_fim(2, 3);
		} else {
			if(!isEmpty()) {
				System.out.println("populaLista 2: Já existe algo na arvore");
			} else {
				Random random = new Random();
				int valor, priori;
				for (int i = 0; i < numNos; i++) {
					valor = random.nextInt(21);
					valor = valor < 4 ? 4 : valor;

					if(usaPrioridade) {
						priori = random.nextInt(4);
						push_fim(valor, priori);
					} else {
						push_fim(valor);
					}

				}
			}
		}
		return auto_increment;
	}
	public int populaLista(int numNos, boolean usaPrioridade, int inicCom) {
		if(false) {

			push_fim(12);
			push_fim(12);
			push_fim(12);
			push_fim(9);
			push_fim(9);
			push_fim(9);

//			push_fim(3, 0);
//			push_fim(5, 0);
//			push_fim(6, 0);
//			push_fim(7, 0);
//			push_fim(3, 1);
//			push_fim(6, 1);
//			push_fim(6, 1);
//			push_fim(7, 1);
//			push_fim(3, 2);
//			push_fim(7, 2);
//			push_fim(6, 2);
//			push_fim(7, 2);
//			push_fim(3, 3);
//			push_fim(8, 3);
//			push_fim(6, 3);
//			push_fim(7, 3);


//			push_fim(7, 0);
//			push_fim(3, 1);
//			push_fim(6, 1);
//			push_fim(8, 3);
//			push_fim(6, 3);
//			push_fim(7, 3);
//			push_fim(2, 3);
//			push_fim(2, 3);
//			push_fim(2, 3);
		} else {
			if(!isEmpty()) {
				System.out.println("populaLista 2: Já existe algo na arvore");
			} else {
				Random random = new Random();
				int valor, priori;
				numNos = 1;
				int tam = 32;
				for (int i = 0; i < numNos; i++) {
					valor = random.nextInt(21);
					valor = valor < 4 ? 4 : valor;
					tam = 32 + random.nextInt(993);

					if(usaPrioridade) {
						priori = random.nextInt(4);
						push_fim(valor, priori);
						getTail().setID(inicCom);
						getTail().setTamanhoUsado(tam);
					} else {
						push_fim(valor);
						getTail().setID(inicCom);
						getTail().setTamanhoUsado(tam);
					}
					inicCom++;
				}
			}
		}
		return inicCom;
	}

	
	public Lista retornaPriori(int prioridade) {
		Lista ret = null;
		if(isEmpty()) {
			if (getAtivaLogs()) {
				System.out.println("retornaPriori: Arvore vazia");
			}
		} else {
			ret = new Lista();
			No temp = getHead();
			while (temp.getNext() != null) {
				if(temp.getPriori() == prioridade) {
					ret.push_fim_no(temp.Entrega());
				}
				temp = temp.getNext();
			}
			if(temp.getPriori() == prioridade) {
				ret.push_fim_no(temp.Entrega());
			}
		}
		return ret;
	}
	public Lista retornaPriori2(int prioridade) {
		Lista ret = null;
		if(isEmpty()) {
			if (getAtivaLogs()) {
				System.out.println("retornaPriori2: Arvore vazia");
			}
		} else {
			ret = new Lista();
			for (int i = 0; i < getQtdNos(); i++) {
				if(returnPos(i).getPriori() == prioridade) {
					ret.push_fim_no(returnPos(i).Entrega());
				}
			}

		}
		return ret;
	}

	public void removeTempoExec(int i) {
		// TODO Auto-generated method stub
		if (isEmpty()) {
			if (getAtivaLogs()) {
				System.out.println("removeTempoExec: Lista Vazia");
			}
		} else {
			No temp = getHead();
			while (temp.getNext() != null) {
				temp.setTempExec(temp.getTempExec() -i);

				temp = temp.getNext();
			}
			temp.setTempExec(temp.getTempExec() -i);
		}
	}
	public No removeTempoExecIgualZero() {
		No ret = null;
		if (isEmpty()) {
			System.out.println("removeTempoExecIgual: Lista Vazia");
		} else {
			No temp = getHead();
			int cont = 0;
			while (temp.getNext() != null) {
				if(temp.getTempExec() <= 0) {
					pop_pos(cont);
				}
				temp = temp.getNext();
				cont++;
			}
			if(temp.getTempExec() <= 0) {
				pop_pos(cont);
			}
		}
		return ret;
	}
	public No temNoTempoZerado() {
		No Ret = null;
		if (isEmpty()) {
			if (getAtivaLogs()) {
				System.out.println("temNoTempoZerado: Lista Vazia");
			}
		} else {
			No temp = getHead();
			while (temp.getNext() != null) {
				if(temp.getTempExec() == 0) {
					Ret = temp;
					return Ret;
				}

				temp = temp.getNext();
			}
			if(Ret == null && temp.getTempExec() == 0) {
				Ret = temp;
				return Ret;
			}
		}
		return Ret;
	}

	public No returnPos(int index) {
		No ret = null;
		if (isEmpty()) {
			if (getAtivaLogs()) {
				System.out.println("returnPos: Lista Vazia");
			}
		} else {
			No temp = getHead();
			int cont = 0;
			while (temp.getNext() != null && cont != index) {
				temp = temp.getNext();
				cont++;
			}

			if(cont == index) {
				if (ativaLogs) {
					System.out.println("returnPos: Retornando pos correta.");
				}
				ret = temp.Entrega();
				return ret;
			} else {
				if (ativaLogs) {
					System.out.println("returnPos: posição incorreta/não existe..");
				}
			}
		}
		return ret;
	}
	public No returnContadorIrrisorio(int contador) {
		No ret = null;
		if (isEmpty()) {
			if (getAtivaLogs()) {
				System.out.println("getContadorIrrisorio: Lista Vazia");
			}
		} else {
			No temp = getHead();
			while (temp.getNext() != null && temp.getContadorIrrisorio() != contador) {
				temp = temp.getNext();
			}

			if(temp.getContadorIrrisorio() == contador) {
				if (ativaLogs) {
					System.out.println("getContadorIrrisorio: Retornando pos correta.");
				}
				return temp.Entrega();
			} else {
				if (ativaLogs) {
					System.out.println("getContadorIrrisorio: posição incorreta/não existe..");
				}
			}
		}
		return ret;
	}

	public boolean removeApartir(int index) {
		boolean ret = false;
		if (isEmpty()) {
			if (getAtivaLogs()) {
				System.out.println("removeApartir: Lista Vazia");
			}
		} else if (getQtdNos() < index){
			if (getAtivaLogs()) {
				System.out.println("removeApartir: indice inexistente");
			}
		} else {
			No temp = getTail();
			int cont = getQtdNos();

			while (temp.getBack() != null && (cont-1) != index) {
				pop_fim();
				temp = temp.getBack();
				cont--;
			}
			pop_fim();
			ret = true;
		}
		return ret;
	}

	public void adicionaRelogio(int i) {
		if(!isEmpty()) {
			No temp = getHead();
			while (temp.getNext() != null) {
				temp.setRelogio(temp.getRelogio() +i);
				
				temp = temp.getNext();
			}
			temp.setRelogio(temp.getRelogio() +i);
		}
	}

	public Lista BubbleSort(Lista l) {
		int tam = l.getQtdNos();
		No prim = null;
		No ultm = null;
//		l.setAtivaLogs(true);

		try {
			for (int i = 0; i < 2; i++) {
			}
			for (int fim = tam-1; fim > 0; --fim) {
				for (int index = 0; index < fim; ++index) {
				System.out.println("-- pos rem: " +index);
					prim = l.returnPos(index);
					ultm = l.returnPos(index +1);
					if (prim.getTempExec() > ultm.getTempExec()) {
						System.out.println(prim.getTempExec() + " > "+ ultm.getTempExec());
						No aux = ultm.Entrega();
						l.pop_pos(index +1);
						l.push_pos_no(index, aux);
					}
				l.imprime();
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return (l != null)? l : null;
	}

	public void quickSort() {
		Lista vetor = this;
		quickSort(vetor, 0, vetor.getQtdNos() -1);
	}
	private void quickSort(Lista vetor, int inicio, int fim) {
		if (inicio < fim) {
			int posicaoPivo = separar(vetor, inicio, fim);
			quickSort(vetor, inicio, posicaoPivo - 1);
			quickSort(vetor, posicaoPivo + 1, fim);
		}
	}
	private int separar(Lista vetor, int inicio, int fim) {
		No [] novo = new No[vetor.getQtdNos()];
		for (int j = 0; j < vetor.getQtdNos(); j++) {
			novo[j] = vetor.returnPos(j);
		}

		No pivo = novo[inicio];
        int i = inicio + 1, f = fim;
        while (i <= f) {
               if (novo[i].getTempExec() <= pivo.getTempExec())
                      i++;
               else if (pivo.getTempExec() < novo[f].getTempExec())
                      f--;
               else {
                      No troca = novo[i];
                      novo[i] = novo[f];
                      novo[f] = troca;
                      i++;
                      f--;
               }
        }
        novo[inicio] = novo[f];
        novo[f] = pivo;

        vetor.removeApartir(0);
        for (int j = 0; j < novo.length; j++) {

			vetor.push_fim(novo[j]);
		}
        return f;
	}

	public void quickSort2() {
		Lista vetor = this;
		quickSort2(vetor, 0, vetor.getQtdNos() -1);
	}
	private void quickSort2(Lista vetor, int inicio, int fim) {
		if (inicio < fim) {
			int posicaoPivo = separar2(vetor, inicio, fim);
			quickSort2(vetor, inicio, posicaoPivo - 1);
			quickSort2(vetor, posicaoPivo + 1, fim);
		}
	}
	private int separar2(Lista vetor, int inicio, int fim) {
		No [] novo = new No[vetor.getQtdNos()];
		for (int j = 0; j < vetor.getQtdNos(); j++) {
			novo[j] = vetor.returnPos(j);
		}

		No pivo = novo[inicio];
        int i = inicio + 1, f = fim;
        while (i <= f) {
               if (novo[i].getDeadLineTotal() <= pivo.getDeadLineTotal())
                      i++;
               else if (pivo.getDeadLineTotal() < novo[f].getDeadLineTotal())
                      f--;
               else {
                      No troca = novo[i];
                      novo[i] = novo[f];
                      novo[f] = troca;
                      i++;
                      f--;
               }
        }
        novo[inicio] = novo[f];
        novo[f] = pivo;

        vetor.removeApartir(0);
        for (int j = 0; j < novo.length; j++) {

			vetor.push_fim(novo[j]);
		}
        return f;
	}

	public boolean GeraContador() {
		boolean ret = false;
		if (isEmpty()) {
			System.out.println("GeraContador: Lista Vazia");
		} else {
			No temp = getHead();
			int cont = 0;
			while (temp.getNext() != null) {
				temp.setContadorIrrisorio(cont);

				temp = temp.getNext();
				cont++;
			}
			temp.setContadorIrrisorio(cont);
			ret = true;
		}
		return ret;
	}
	public Lista organizaFila(Lista lista) {
		// TODO Auto-generated method stub
		Lista l = null;
		if (lista.isEmpty()) {
			if (ativaLogs) {
				System.out.println("organizaFila: Lista Vazia");
			}
		} else if (lista.getHead() == lista.getTail()) {
			if (ativaLogs) {
				System.out.println("organizaFila: Não atuaizado, Unico processo.");
			}
			l = lista;
		} else {
			l = new Lista();



		}
		return l;
	}

	/*
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Funcoes f = new Funcoes();
		Lista l = new Lista();
		l.setAtivaLogs(true);

//		l.push_fim(10, 0, 0);
//		l.push_fim(20, 0, 1);
//		l.push_fim(30, 0, 1);
//		l.push_fim(40, 0, 2);
//		l.push_fim(50, 0, 1);
//		l.retornaPriori(1).imprime();
		No n1 = new No(10, 10, 0);
		No n2 = new No(20, 20, 1);
		No n3 = new No(30, 30, 1);
		No n4 = new No(40, 40, 2);
		No n5 = new No(50, 50, 1);
		No n6 = new No(80, 80, 1);
		l.push_fim(n1);
		l.push_fim(n2);
		l.push_fim(n3);
		l.push_fim(n4);
		l.push_fim(n5);
//		l.push_pos(0, 2);
//		l.push_pos(0, 3);
//		l.push_pos(0, 8);
//		l.push_pos_no(2, n6);
//		l.pop_pos(8);
		l.removeApartir(8);
		l.imprime();

		System.out.println("--");
//		System.out.println(l.temNoTempoZerado().getQuantum());
//		System.out.println(l.pop_ini().getQuantum());;
//		l.pop_ini().getQuantum();

		System.out.println("--");
//		System.out.println(l.temNoTempoZerado().getQuantum());


		for (int i = 0; i < 5; i++) {
//			System.out.println(i + 1);
//			f.waitSec(1);
		}
	}
	*/

	public Lista inexistentesEm2(Lista lLivre) {
		Lista ret = new Lista();
		No temp = getHead();
		No tempMem;
		int count;
		for (int i = 0; i < getQtdNos(); i++) {
			tempMem = lLivre.getHead();
			count = 0;
			for (int j = 0; j < getQtdNos(); j++) {
				if(tempMem.getID() == temp.getID()) {
					count++;
					break;
				}
				
				if(tempMem.getNext() != null) {
					tempMem = tempMem.getNext();
				}
			}
			if(count == 0) {
				ret.push_fim(temp);
			}
			
			if(temp.getNext() != null) {
				temp = temp.getNext();
			}
		}
		if(ret.isEmpty())
			ret = null;
		return ret;
	}
	public Lista[] inexistentesEm(Lista lLivre) {
		Lista[] ret = new Lista[2];
		ret[0] = new Lista();
		ret[1] = new Lista();
		No temp = getHead();
		No tempMem;
		int count;
		
		while (temp != null) {
			count = 0;
			tempMem = lLivre.getHead();

			while (tempMem != null) {
				if(tempMem.getID() == temp.getID()) {
					count++;
					break;
				}
				if(tempMem.getNext() != null) {
					tempMem = tempMem.getNext();
				} else {
					break;
				}
			}
			if(count == 0) {
				ret[0].push_fim(temp);
				ret[1].push_fim(tempMem);
			}
			if(temp.getNext() != null) {
				temp = temp.getNext();
			} else {
				break;
			}
		}

		if(ret[0].isEmpty())
			ret[0] = null;
		if(ret[1].isEmpty())
			ret[1] = null;
		return ret;
	}
	
	public No BuscaBestFit(int tamanho) {
		No melhorPos = null;
		if(!isEmpty()) {
			melhorPos = null;
			int diffAnt = 999999, diff = 0, tamPos = 0;
			No temp = getHead();
			
			while (temp.getNext() != null) {
				tamPos = temp.getTamanhoOrig();
				diff = tamPos - tamanho;
				diff = diff < 0 ? diff*(-1) : diff;
				
				if (diff == 0) {
					return temp;
				} else if (diff < diffAnt && tamPos >= tamanho) {
					melhorPos = temp;
					diffAnt = diff;
				}
				
				temp = temp.getNext();
			}
			
			tamPos = temp.getTamanhoOrig();
			diff = tamPos - tamanho;
			diff = diff < 0 ? diff*(-1) : diff;
			if (diff == 0) {
				return temp;
			} else if (diff < diffAnt && tamPos >= tamanho) {
				melhorPos = temp;
			}
		}
		return melhorPos;
	}
	
	
	public static void main(String args[]) {
		Lista l1 = new Lista();

		No no = new No();
		no.setTamanhoUsado(40);
		
		no.setID(1);
		l1.push_fim(no.Entrega());
		l1.getTail().imprime();
		System.out.println("--");
		no.setID(2);
		l1.push_fim(no.Entrega());
		l1.getTail().imprime();
		System.out.println("--");
		no.setID(3);
		l1.push_fim(no.Entrega());
		l1.getTail().imprime();
		System.out.println("----");

		l1.pop_ID(2);

		System.out.println();
		l1.imprimeID();
		
//		if (temp == null) {
//			System.out.println("nulo");
//		} else {
//			System.out.println("Retornado:");
//			temp.imprime();
//		}
	}

	public static void main3(String args[]) {
		Lista l = new Lista();
		l.push_fim(9);
		l.push_fim(8);
		l.push_fim(7);
		l.push_fim(6);
		l.push_fim(5);
//		l.imprime();
		l.quickSort2();
		l.imprime();





	}

	public boolean libera(No posMem) {
		boolean ret = false;
		if (isEmpty()) {
			System.out.println("imprime: Lista Vazia");
		} else {
			No temp = getHead();
			while (temp.getNext() != null && !ret) {
				if(temp == posMem) {
					ret = !ret;
					break;
				}

				temp = temp.getNext();
			}
			if(temp == posMem && !ret) {
				ret = !ret;
			}
			
			if(ret) {
				if(!isEmpty()) {
					if(getHead() != getTail()) {
						pop_fim();
					} else {
						if(temp.getNext() == null){
							if(temp.getBack() == null){
								//
							} else {
								temp.getNext().setBack(null);
							}
						} else {
							if(temp.getBack() == null){
								temp.getNext().setBack(null);
							} else {
								temp.getNext().setBack(temp.getBack());
								temp.getNext().setBack(temp.getBack());
							}
						}
						
						temp.setNext(null);
						temp.setBack(null);
					}
				}
			}
		}
		return ret;
	}

	public No buscaCor(int cor) {
		No ret = null;
		if (!isEmpty()) {
			No temp = getHead();
			while (temp.getNext() != null) {
				if(temp.getCor() == cor) {
					break;
				}
				temp = temp.getNext();
			}
			if(temp.getCor() == cor) {
				ret = temp;
			}
		}
		return ret;
	}
}
