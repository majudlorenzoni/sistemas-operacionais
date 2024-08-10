package trabalhoFinal;

import java.util.Queue;
import java.util.LinkedList;

public class Gerenciador {
    private Queue<Cliente> filaCapacetes;
    private Queue<Cliente> filaKarts;
    private int kartsDisponiveis;
    private int capacetesDisponiveis;

    public Gerenciador(int kartsIniciais, int capacetesIniciais) {
        filaCapacetes = new LinkedList<>();
        filaKarts = new LinkedList<>();
        kartsDisponiveis = kartsIniciais;
        capacetesDisponiveis = capacetesIniciais;
    }

    public synchronized boolean atribuirRecursos(Cliente cliente) {
        boolean temKart = false;
        boolean temCapacete = false;

        // Adiciona o cliente na fila correspondente
        if (cliente instanceof Crianca) {
            filaCapacetes.add(cliente);
        } else {
            filaKarts.add(cliente);
        }

        // Tenta alocar um capacete
        while (filaCapacetes.peek() != null && capacetesDisponiveis > 0) {
            Cliente c = filaCapacetes.poll();
            capacetesDisponiveis--;
            if (c.equals(cliente)) {
                temCapacete = true;
            }
        }

        // Tenta alocar um kart
        while (filaKarts.peek() != null && kartsDisponiveis > 0) {
            Cliente c = filaKarts.poll();
            kartsDisponiveis--;
            if (c.equals(cliente)) {
              temKart = true;
            }
        }

        // Se o cliente conseguiu ambos os recursos
        if (temKart && temCapacete) {
          return true;
        } else {
          // Se não conseguiu, deve liberar os recursos alocados
          if (temKart) {
            liberarRecursos();
          }
            if (temCapacete) {
                liberarRecursos();
              }
            return false;
          }
    }

    public synchronized void liberarRecursos() {
        kartsDisponiveis++;
        capacetesDisponiveis++;
        System.out.println("Recursos liberados. Karts disponíveis: " + kartsDisponiveis + ", Capacetes disponíveis: " + capacetesDisponiveis);
    }
    
    public int getKartsDisponiveis() {
        return kartsDisponiveis;
    }

    public int getCapacetesDisponiveis() {
      return capacetesDisponiveis;
    }
  }
  
