import processing.core.PApplet;
public class Main extends PApplet{
    private Ising ising;
    private double nodeWidth;

    public void settings(){
        size(600,600);
    }
    public void setup() {
     ising = new Ising(298,200,200);
     nodeWidth = width/ising.getTable().length;
    }
    public void draw() {
        boolean[][] curState = ising.getTable();
        for(int k=0; k< curState.length; k++){
            for(int z =0; z < curState[k].length; z++){
                if(curState[k][z]){
                    fill(0,0,255);
                    stroke(0,0,255);
                }
                else{
                    fill(255,0,0);
                    stroke(255,0,0);
                }

                rect((float)nodeWidth*k,(float)nodeWidth*z, (float)nodeWidth, (float)nodeWidth);
            }
        }

    }

    public void mouseClicked(){
        int trues = 0;

        for(int k =0; k < 50000; k++){
            int xVal = 0;
            int yVal = 0;
            if(ising.flip(xVal,yVal)){
                trues++;
            }
            if(ising.getTable()[xVal][yVal]){
                fill(0,0,255);
                stroke(0,0,255);
            }
            else{
                fill(255,0,0);
                stroke(255,0,0);
            }

            rect((float)nodeWidth*xVal,(float)nodeWidth*yVal, (float)nodeWidth, (float)nodeWidth);
        }
        System.out.println(trues);
    }




    public static void main(String[] Args){
        PApplet.main("Main");
    }
}
