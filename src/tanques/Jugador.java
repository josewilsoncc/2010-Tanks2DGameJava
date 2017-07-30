package tanques;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.ImageIcon;

public class Jugador
{
    public final static int superiorArriba = 0;
    public final static int superiorAbajo = 1;
    public final static int superiorIzquierda = 2;
    public final static int superiorDerecha = 3;

    public final static int baseHorizontal = 4;
    public final static int baseVertical = 5;

    public int numeroJugador;

    public ImageIcon base1 = util.imagen("img/tanque/base1.png");
    public ImageIcon base2 = util.imagen("img/tanque/base2.png");
    public ImageIcon superior1 = util.imagen("img/tanque/superior1.png");
    public ImageIcon superior2 = util.imagen("img/tanque/superior2.png");
    public ImageIcon superior3 = util.imagen("img/tanque/superior3.png");
    public ImageIcon superior4 = util.imagen("img/tanque/superior4.png");

    public Jugador(int numeroJugador, int x, int y)
    {
        this.numeroJugador = numeroJugador;
        if(numeroJugador==0)
        {
            util.xJugador1 = x;
            util.yJugador1 = y;
        }
        else
        {
            util.xJugador2 = x;
            util.yJugador2 = y;
        }
    }

    public void paint(Graphics gr, int x, int y)
    {
        if(numeroJugador == 0)
        {
            gr.setColor(util.colorJugador1);
            gr.fillRect(x+17, y+17, 26, 26);
            gr.setColor(new Color(0));
            switch(util.baseJugador1)
            {
                case baseVertical:
                    gr.drawImage(base1.getImage(), x, y, null);
                    break;
                case baseHorizontal:
                    gr.drawImage(base2.getImage(), x, y, null);
                    break;
            }
            switch(util.superiorJugador1)
            {
                case superiorArriba:
                    gr.drawImage(superior1.getImage(), x, y, null);
                    break;
                case superiorDerecha:
                    gr.drawImage(superior2.getImage(), x, y, null);
                    break;
                case superiorAbajo:
                    gr.drawImage(superior3.getImage(), x, y, null);
                    break;
                case superiorIzquierda:
                    gr.drawImage(superior4.getImage(), x, y, null);
                    break;
            }
        }
        else
        {
            gr.setColor(util.colorJugador2);
            gr.fillRect(x+17, y+17, 26, 26);
            gr.setColor(new Color(0));
            switch(util.baseJugador2)
            {
                case baseVertical:
                    gr.drawImage(base1.getImage(), x, y, null);
                    break;
                case baseHorizontal:
                    gr.drawImage(base2.getImage(), x, y, null);
                    break;
            }
            switch(util.superiorJugador2)
            {
                case superiorArriba:
                    gr.drawImage(superior1.getImage(), x, y, null);
                    break;
                case superiorDerecha:
                    gr.drawImage(superior2.getImage(), x, y, null);
                    break;
                case superiorAbajo:
                    gr.drawImage(superior3.getImage(), x, y, null);
                    break;
                case superiorIzquierda:
                    gr.drawImage(superior4.getImage(), x, y, null);
                    break;
            }
        }
        
    }
}
