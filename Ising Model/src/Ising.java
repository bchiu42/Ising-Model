import java.util.Random;

public class Ising{
    private boolean[][] table;
    private int temp;
    private Random rand;
    private final int LIKE_ENERGY = 10;
    private final int OPPOSITE_ENERGY = 32;
    private final int MAX_NODE_ENERGY = OPPOSITE_ENERGY*4;
    private int totalEnergy;
    public Ising(int temp, int xLength, int yLength){
        table = new boolean[xLength][yLength];
        this.temp = temp;
        rand = new Random();
        for(int k =0; k< table.length; k++){
            for(int z =0; z< table[k].length; z++){
                if(rand.nextInt(2) == 1){
                    table[k][z] = false;
                }else{
                    table[k][z] = true;
                }
            }
        }
        int energy = 0;
        for(int k =0; k <table.length; k++){
            for(int z =0; z< table[k].length; z++){
                energy += nodeEnergy(k,z);
            }
        }
    totalEnergy = energy;
    }

    public int nodeEnergy(int x, int y){
        int sum = 0;
        if(x != 0 ){
            if(table[x][y] ^ table[x-1][y]){
                sum += OPPOSITE_ENERGY;
            }else{
                sum += LIKE_ENERGY;
            }


        }
        if(x != table.length -1){
            if(table[x][y] ^ table[x+1][y]){
                sum += OPPOSITE_ENERGY;
            }else{
                sum += LIKE_ENERGY;
            }
        }
        if(y != 0){
            if(table[x][y] ^ table[x][y-1]){
                sum += OPPOSITE_ENERGY;
            }else{
                sum += LIKE_ENERGY;
            }
        }
        if(y != table[x].length -1){
            if(table[x][y] ^ table[x][y+1]){
                sum += OPPOSITE_ENERGY;
            }else sum += LIKE_ENERGY;
        }
        return sum;
    }

    //
    public boolean flip(int x, int y){
    int xVal = rand.nextInt(table.length);
    int yVal = rand.nextInt(table[xVal].length);
    x = xVal;
    y= yVal;
    int curEnergy = nodeEnergy(xVal,yVal);
    table[xVal][yVal] = !table[xVal][yVal];
    int afterEnergy = nodeEnergy(xVal,yVal);
    if(afterEnergy < curEnergy){
        totalEnergy = totalEnergy - curEnergy + afterEnergy;
        return true;
    }
    if(rand.nextDouble() < (double)afterEnergy/(double)MAX_NODE_ENERGY){
        table[xVal][yVal] = !table[xVal][yVal];
        return false;
    }
        totalEnergy = totalEnergy - curEnergy + afterEnergy;
        return true;
    }

    public boolean[][] getTable() {
        return table;
    }

    public int getTemp() {
        return temp;
    }

    public int getTotalEnergy() {
        return totalEnergy;
    }
}