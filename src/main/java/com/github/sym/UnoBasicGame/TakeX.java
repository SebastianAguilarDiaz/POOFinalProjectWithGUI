package com.github.sym.unobasicgame;
import java.util.Random;

public class TakeX extends Card {
    private int x=1;

    public TakeX(Board b) {
        super(b);
        Random r= new Random();
        
        this.cardType=Card.TAKE_X;


        // we need to choose one of this randomly {0,2,4}
        // while its odd it chooses another number
        while(this.x%2!=0)
            this.x=r.nextInt(5);

        this.setFront();
    }

    public int getX(){
        return this.x;
    }
    public void takeX() {
        b.addCardsToAPlayer(this.x);
        
    }

    @Override
    public void throwIt() {
        takeX();
        this.b.setActualPlayerIndex((( this.b.getActualPlayerIndex() + b.getFlow()) % this.b.getNumberOfPlayers() + this.b.getNumberOfPlayers())%this.b.getNumberOfPlayers());
    }

    @Override
    public void setFront() {
        String name="TOMA " + this.x;
        if (this.x==0) name="SALTO";
        for(int j=0;j<Card.HEIGHT;j++){
            for(int i=0;i<Card.WIDTH;i++){
                if(j==3 && i>=1 && i<1+name.length()) front[j][i]=name.charAt(i-1);
                else if(j==4 && i==4)    this.front[j][i]=Card.COLORS[this.getColor()-1];
                else front[j][i]=Card.background;
            }
        }
    

    }
}