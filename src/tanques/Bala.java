package tanques;

import java.awt.Graphics;
import javax.swing.ImageIcon;

public class Bala
{

    public int x = 0;
    public int y = 0;
    public int direccion = Jugador.superiorArriba;
    private int stopTempo = 0;
    private boolean mover = true;
    public boolean borrame = false;

    public ImageIcon bala1 = util.imagen("img/extra/bala1.png");
    public ImageIcon bala2 = util.imagen("img/extra/bala2.png");
    public ImageIcon bala3 = util.imagen("img/extra/bala3.png");
    public ImageIcon bala4 = util.imagen("img/extra/bala4.png");

    public Bala(int x, int y, int direccion)
    {
        this.x = x;
        this.y = y;
        this.direccion = direccion;
    }

    public void paint(Graphics gr)
    {
        if(stopTempo > 0)
        {
            mover = true;
            stopTempo = 0;
        }
        else
        {
            mover = false;
            stopTempo++;
        }
        moverBala();
        gr.setClip(0, 0, 300, 300);
        switch(direccion)
        {
            case Jugador.superiorArriba:
                gr.drawImage(bala1.getImage(), (x*60)-(util.xJugador1*60)+120, (y*60)-(util.yJugador1*60)+120, null);
                break;
            case Jugador.superiorAbajo:
                gr.drawImage(bala3.getImage(), (x*60)-(util.xJugador1*60)+120, (y*60)-(util.yJugador1*60)+120, null);
                break;
            case Jugador.superiorIzquierda:
                gr.drawImage(bala4.getImage(), (x*60)-(util.xJugador1*60)+120, (y*60)-(util.yJugador1*60)+120, null);
                break;
            case Jugador.superiorDerecha:
                gr.drawImage(bala2.getImage(), (x*60)-(util.xJugador1*60)+120, (y*60)-(util.yJugador1*60)+120, null);
                break;
        }

        gr.setClip(300,0,300,300);
        switch(direccion)
        {
            case Jugador.superiorArriba:
                gr.drawImage(bala1.getImage(), (x*60)-(util.xJugador2*60)+420, (y*60)-(util.yJugador2*60)+120, null);
                break;
            case Jugador.superiorAbajo:
                gr.drawImage(bala3.getImage(), (x*60)-(util.xJugador2*60)+420, (y*60)-(util.yJugador2*60)+120, null);
                break;
            case Jugador.superiorIzquierda:
                gr.drawImage(bala4.getImage(), (x*60)-(util.xJugador2*60)+420, (y*60)-(util.yJugador2*60)+120, null);
                break;
            case Jugador.superiorDerecha:
                gr.drawImage(bala2.getImage(), (x*60)-(util.xJugador2*60)+420, (y*60)-(util.yJugador2*60)+120, null);
                break;
        }
        
        if(util.xJugador1 == x && util.yJugador1 == y){util.pvJugador1 -= 2; util.golpesJugador1++; borrame=true;}
        if(util.xJugador2 == x && util.yJugador2 == y){util.pvJugador2 -= 2; util.golpesJugador2++; borrame=true;}
        gr.setClip(0,0,600,300);
    }

    public void moverBala()
    {
        boolean noHayBalas = true;
        switch(direccion)
        {
            case Jugador.superiorArriba:
                for (int i = 0; i < util.balas.size() && noHayBalas; i++)
                {
                    if(util.balas.get(i).x == this.x && util.balas.get(i).y == this.y-1){noHayBalas=false;}
                }
                if(mover){if(util.mapa.mapa[y-1][x] == 5 && noHayBalas){y--;}else{direccion = Jugador.superiorAbajo;}}
                break;
            case Jugador.superiorAbajo:
                for (int i = 0; i < util.balas.size() && noHayBalas; i++)
                {
                    if(util.balas.get(i).x == this.x && util.balas.get(i).y == this.y+1){noHayBalas=false;}
                }
                if(mover){if(util.mapa.mapa[y+1][x] == 5 && noHayBalas){y++;}else{direccion = Jugador.superiorArriba;}}
                break;
            case Jugador.superiorIzquierda:
                for (int i = 0; i < util.balas.size() && noHayBalas; i++)
                {
                    if(util.balas.get(i).x == this.x-1 && util.balas.get(i).y == this.y){noHayBalas=false;}
                }
                if(mover){if(util.mapa.mapa[y][x-1] == 5 && noHayBalas){x--;}else{direccion = Jugador.superiorDerecha;}}
                break;
            case Jugador.superiorDerecha:
                for (int i = 0; i < util.balas.size() && noHayBalas; i++)
                {
                    if(util.balas.get(i).x+1 == this.x && util.balas.get(i).y == this.y){noHayBalas=false;}
                }
                if(mover){if(util.mapa.mapa[y][x+1] == 5 && noHayBalas){x++;}else{direccion = Jugador.superiorIzquierda;}}
                break;
        }
    }
}