
package algoritmogenetico;

import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Angelo
 */
public class AlgoritmoGenetico {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner s = new Scanner( System.in );
        
        System.out.print("Digite o numero da populacao: ");
        int nPopulacao = s.nextInt();
        
        System.out.print("Digite o fitness objetivo (-127 a 126): ");
        int fitObj = s.nextInt();
        
        
        Populacao pop = new Populacao(nPopulacao, fitObj);
        
        pop.mostraPopulacao();
        
        while(pop.getFitnessPop() < pop.fitObjetivo){
            pop.evoluir();
        }


    }
    
}
