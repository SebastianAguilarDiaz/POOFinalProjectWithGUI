package com.github.sym.unobasicgame;

import java.util.Random;

public class Numeric extends Card {
    private int num;
    
    public Numeric(Board b) {
        super(b);
        Random r= new Random();
        this.num=r.nextInt(10);
        this.cardType=Card.NUMERIC;
        this.setFront();
    }

    @Override
    public void throwIt() {
    }

    @Override
    public void setFront() {
        for(int j=0;j<Card.HEIGHT;j++){
            for(int i=0;i<Card.WIDTH;i++){
                if(j==3 && i==3)    this.front[j][i]=((Integer)this.num).toString().charAt(0);
                else if(j==4 && i==4)    this.front[j][i]=Card.COLORS[this.getColor()-1];
                else this.front[j][i]=Card.background;
                
            }
        }
    }

    public int getNum(){
        return this.num;
    
    }
}