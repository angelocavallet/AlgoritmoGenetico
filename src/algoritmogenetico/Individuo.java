
package algoritmogenetico;


/**
 *
 * @author Angelo
 */
public class Individuo {
    
    protected byte genes;
    
    public Individuo(byte genes) {
        this.genes = genes;
    }
    
    public Individuo() {
        //max 127
        //min -128
        this.genes = (byte) Math.round(-128 + Math.random() * 255);
    }
    
    
    protected int getFitness(){
        return (int) this.genes;
    }
    
    protected String getBinario(){
        return String.format("%8s", Integer.toBinaryString(this.genes & 0xFF)).replace(' ', '0');
    }  
}
