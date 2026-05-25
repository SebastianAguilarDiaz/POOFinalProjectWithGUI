package com.github.sym.UnoBasicGame;

public class Reverse extends Card {
    public Reverse(Board b) {
        super(b);
        this.cardType=Card.REVERSE;

        this.setFront();
    }

    public void changeFlow() {
        b.changeFlow();
    }

    @Override
    public void throwIt() {
        this.changeFlow();
    }

    @Override
    public void setFront() {
        String name="Rev";
        for(int j=0;j<Card.HEIGHT;j++){
            for(int i=0;i<Card.WIDTH;i++){
                if(j==3 && i>=3 && i<3+name.length()) front[j][i]=name.charAt(i-3);
                else if(j==4 && i==4)    this.front[j][i]=Card.COLORS[this.getColor()-1];
                else front[j][i]='-';
            }
        }
    }
}