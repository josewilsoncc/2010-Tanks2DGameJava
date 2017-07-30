package tanques;

import java.awt.Graphics;
import javax.swing.ImageIcon;

public class Mapa
{
    public int[][]mapa;

    ImageIcon mapa0 = util.imagen("img/mapa/0.png");
    ImageIcon mapa1 = util.imagen("img/mapa/1.png");
    ImageIcon mapa2 = util.imagen("img/mapa/2.png");
    ImageIcon mapa3 = util.imagen("img/mapa/3.png");
    ImageIcon mapa4 = util.imagen("img/mapa/4.png");
    ImageIcon mapa5 = util.imagen("img/mapa/5.png");
    ImageIcon mapa6 = util.imagen("img/mapa/6.png");
    ImageIcon mapa7 = util.imagen("img/mapa/7.png");
    ImageIcon mapa8 = util.imagen("img/mapa/8.png");
    ImageIcon mapa9 = util.imagen("img/mapa/9.png");

    public Mapa()
    {
        mapa = new int[][]{
            {1,2,2,2,2,2,2,2,2,3},
            {4,5,5,5,5,5,5,5,5,6},
            {4,5,0,5,5,5,5,0,5,6},
            {4,5,0,5,5,5,5,0,5,6},
            {4,5,0,5,0,0,5,0,5,6},
            {4,5,0,5,0,0,5,0,5,6},
            {4,5,0,5,5,5,5,0,5,6},
            {4,5,0,5,5,5,5,0,5,6},
            {4,5,5,5,5,5,5,5,5,6},
            {7,8,8,8,8,8,8,8,8,9}
        };
    }

    public void paint(Graphics gr, int numeroJugador)
    {
        gr.setClip(numeroJugador * 300, 0, 300, 300);
        int xJugador=0;
        int yJugador=0;
        if(numeroJugador==0)
        {
            xJugador = util.xJugador1;
            yJugador = util.yJugador1;
        }
        else
        {
            xJugador = util.xJugador2;
            yJugador = util.yJugador2;
        }
        for(int i = 0 ; i < mapa.length ; i++)
        {
            for(int j = 0 ; j < mapa[i].length ; j++)
            {
                switch(mapa[i][j])
                {
                    case 0:
                        gr.drawImage(mapa0.getImage(), (j*60)+((120 + (300*numeroJugador) )-(60*xJugador)), (i*60)+(120-(60*yJugador)), null);
                        break;
                    case 1:
                        gr.drawImage(mapa1.getImage(), (j*60)+((120 + (300*numeroJugador) )-(60*xJugador)), (i*60)+(120-(60*yJugador)), null);
                        break;
                    case 2:
                        gr.drawImage(mapa2.getImage(), (j*60)+((120 + (300*numeroJugador) )-(60*xJugador)), (i*60)+(120-(60*yJugador)), null);
                        break;
                    case 3:
                        gr.drawImage(mapa3.getImage(), (j*60)+((120 + (300*numeroJugador) )-(60*xJugador)), (i*60)+(120-(60*yJugador)), null);
                        break;
                    case 4:
                        gr.drawImage(mapa4.getImage(), (j*60)+((120 + (300*numeroJugador) )-(60*xJugador)), (i*60)+(120-(60*yJugador)), null);
                        break;
                    case 5:
                        gr.drawImage(mapa5.getImage(), (j*60)+((120 + (300*numeroJugador) )-(60*xJugador)), (i*60)+(120-(60*yJugador)), null);
                        break;
                    case 6:
                        gr.drawImage(mapa6.getImage(), (j*60)+((120 + (300*numeroJugador) )-(60*xJugador)), (i*60)+(120-(60*yJugador)), null);
                        break;
                    case 7:
                        gr.drawImage(mapa7.getImage(), (j*60)+((120 + (300*numeroJugador) )-(60*xJugador)), (i*60)+(120-(60*yJugador)), null);
                        break;
                    case 8:
                        gr.drawImage(mapa8.getImage(), (j*60)+((120 + (300*numeroJugador) )-(60*xJugador)), (i*60)+(120-(60*yJugador)), null);
                        break;
                    case 9:
                        gr.drawImage(mapa9.getImage(), (j*60)+((120 + (300*numeroJugador) )-(60*xJugador)), (i*60)+(120-(60*yJugador)), null);
                        break;
                }
            }
        }
        gr.setClip(0,0,600,300);
    }
}
