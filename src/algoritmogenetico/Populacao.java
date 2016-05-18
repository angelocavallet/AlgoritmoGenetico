
package algoritmogenetico;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Angelo
 */
public class Populacao {
    
    protected List<Individuo> populacao = new ArrayList();
    protected int nPopulacao;
    protected int fitObjetivo;
    
    public Populacao(int nPopulacao, int fitObjetivo){
        this.nPopulacao = nPopulacao;
        this.fitObjetivo = fitObjetivo;

        for(int i=0; i<nPopulacao; i++) {
            this.populacao.add(new Individuo());
        }
        
    }
    
    protected void mostraPopulacao(){        
        System.out.println("Populacao:");
        for (Individuo ind : this.populacao) {
            System.out.println("Genes: "+ind.getBinario()+"    Fitness: "+ind.getFitness());
        }
        
        System.out.println("Pressione ENTER para continuar...");
        new Scanner(System.in).nextLine();
    }
    
    protected void removeIndividuo(Individuo ind){
        this.populacao.remove(ind);
    }  
    
    protected void addIndividuo(Individuo ind){
        this.populacao.add(ind);
    } 
    
    protected List<Individuo> getIndReproducao(){
        List<Individuo> reprod = new ArrayList();
        
        for (int i=0; i<4; i++){
            int posicao = (int) Math.round(Math.random() * this.nPopulacao);
            reprod.add(this.populacao.get(posicao));
        }
        
        return reprod;
    } 
    
    protected int getFitnessPop(){
        int somaFit = 0;
        for (Individuo ind : this.populacao) {
            somaFit += ind.getFitness();
        }
        return (int) somaFit/this.nPopulacao;
    }
    
    protected void reproduzir(Individuo ind1, Individuo ind2){
        int partir = (int) Math.round(1 + Math.random() * 7);
        String i1p1 = ind1.getBinario().substring(0, partir),
               i1p2 = ind1.getBinario().substring(partir, ind1.getBinario().length()),
               i2p1 = ind2.getBinario().substring(0, partir),
               i2p2 = ind2.getBinario().substring(partir, ind2.getBinario().length());
        
        StringBuilder f1 = new StringBuilder(i1p1+i2p2);
        f1.setCharAt(partir, String.valueOf(Math.round(Math.random())).toCharArray()[0]);
        
        StringBuilder f2 = new StringBuilder(i2p1+i1p2);
        f2.setCharAt(partir, String.valueOf(Math.round(Math.random())).toCharArray()[0]);
        
        Individuo filho1 = new Individuo(Byte.parseByte(f1.toString(), 2)),
                  filho2 = new Individuo(Byte.parseByte(f2.toString(), 2));
        
        this.addIndividuo(filho1);
        this.addIndividuo(filho2);
        
        System.out.println("Filho1: "+filho1.getBinario()+"    Fitness: "+filho1.getFitness()+" Foi Criado!");
        System.out.println("Filho2: "+filho2.getBinario()+"    Fitness: "+filho2.getFitness()+" Foi Criado!");
        
        System.out.println("Pressione ENTER para continuar...");
        new Scanner(System.in).nextLine();

    }
    
    protected void evoluir(){
        System.out.println("Selecionando 4 individuos para reprodução: ");

        List<Individuo> reprod = this.getIndReproducao();
        
       
        for (Individuo ind : reprod) {
            System.out.println("Individuo: "+ind.getBinario()+"    Fitness: "+ind.getFitness());
        }
        
        System.out.println("Pressione ENTER para continuar...");
        new Scanner(System.in).nextLine();
        
        System.out.println("Avaliação dos individuos: ");
        
        Individuo apto1 = reprod.get(0),//5
                  apto2 = reprod.get(1),//1
                  inapto1 = reprod.get(2),//3
                  inapto2 = reprod.get(3);//4
        
        for (Individuo ind : reprod) {
            
            if(ind.getFitness() > apto1.getFitness()){
                apto1 = ind;
                
            }else if(!ind.equals(apto1) && ind.getFitness() > apto2.getFitness()){
                apto2 = ind;
            
            }else if(ind.getFitness() < inapto1.getFitness()){
                inapto1 = ind;
            
            }else if(!ind.equals(inapto1) && ind.getFitness() < inapto2.getFitness()){
                inapto2 = ind;
            }
        }

        System.out.println("Individuo: "+apto1.getBinario()+"    Fitness: "+apto1.getFitness()+" Foi selecionado!");
        System.out.println("Individuo: "+apto2.getBinario()+"    Fitness: "+apto2.getFitness()+" Foi selecionado!");
        System.out.println("Individuo: "+inapto1.getBinario()+"    Fitness: "+inapto1.getFitness()+" Sera removido!");
        System.out.println("Individuo: "+inapto2.getBinario()+"    Fitness: "+inapto2.getFitness()+" Sera removido!");
        
        this.removeIndividuo(inapto1);
        this.removeIndividuo(inapto2);
        
        this.reproduzir(apto1, apto2);
    }
    
}
