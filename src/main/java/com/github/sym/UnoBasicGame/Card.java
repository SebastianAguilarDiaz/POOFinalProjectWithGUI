package com.github.sym.unobasicgame;
import java.util.Random;
import com.github.sym.AppState;

public abstract class Card implements Showable{
    
    // TYPE OF CARD
    public static final int NUMERIC = 1;
    public static final int REVERSE = 2;
    public static final int TAKE_X = 3;

    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;


    // COLORS 
    public static final int RED = 1;
    public static final int YELLOW = 2;
    public static final int GREEN = 3;
    public static final int BLUE = 4;

    public static final char COLORS[]={'R','A','V','Z'};

    protected static final char background='-';






    private char[][] back=new char[Card.HEIGHT][Card.WIDTH];
    protected char[][] front=new char[Card.HEIGHT][Card.WIDTH];
    
    private boolean upwards=true;
    protected Board b;
    protected int cardType;
    protected int color; // Cambiado a protected para cumplir con el requisito de 4 atributos protegidos

    public Card(Board b) {
        this.b=b;




        // make backawards of the card
        for (int i = 0; i < Card.HEIGHT; i++) {
            for (int j = 0; j < Card.WIDTH; j++) {
                back[i][j] = '#';
            }
        }



        // set a random color for the card
        Random r= new Random ();
        this.color=r.nextInt(4)+1;
        
    }
    @Override
    public void show() {
        for (int j = 0; j < Card.HEIGHT; j++) {
            for (int i = 0; i < Card.WIDTH; i++) {
                AppState.getInstance().printTextOnScreen(this.getChar(i, j));
            }
            AppState.getInstance().printLineOnScreen();
        }
    }

    public boolean compatible(Card lastCard) {
        // if its the same color they are compatible
        if(this.color==lastCard.color) return true;

        // if they are from the same type and not numeric cards
        else if (this.getCardType() == lastCard.getCardType() && this.getCardType()!=Card.NUMERIC){
            return true;
        }   

        if(this.getCardType()==Card.NUMERIC && lastCard.getCardType()==Card.NUMERIC ){
                Numeric n1 =(Numeric) this;
                Numeric n2 =(Numeric) lastCard;
                if(n1.getNum()==n2.getNum())
                    return true;
        }
        

        return false;
    }
    public abstract void throwIt();

    public abstract void setFront();

    public int getCardType() {
        return this.cardType;
    }

    public void flipCard() {
        this.upwards=!this.upwards;
    }

    public boolean getUpwards() {
        return this.upwards;
    }

    public char getChar(int i, int j) {
        if(this.getUpwards())
            return front[j][i];
        else
            return back[j][i];
    }

    public static Card randomCard(Board b) {
        Card actualCard=null;
        Random r = new Random ();
        int x= r.nextInt(3)+1;
        switch (x) {
            case Card.REVERSE:
                actualCard=new Reverse(b);
                break;
            case Card.NUMERIC:
                actualCard=new Numeric(b);
                break;
            case Card.TAKE_X:
                actualCard=new TakeX(b);
                break;
        }
        return actualCard;
    }

    public int getColor(){
        return this.color;
    }
}