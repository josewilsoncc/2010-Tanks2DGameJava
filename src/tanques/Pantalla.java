package tanques;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

public class Pantalla extends javax.swing.JLayeredPane implements KeyListener
{
    public final static int INTRO1 = -1;
    public final static int INTRO2 = 0;
    public final static int MENU = 1;
    public final static int JUEGO = 2;
    public final static int MENUMULTIJUEGO = 3;
    public final static int MULTIJUEGO = 4;
    public final static int GANADOR = 5;

    public Image ImagenParaPintar;
    public Graphics Graficos;

    boolean imagenInternaCreada = false;
    boolean actualizar = false;

    public Pantalla()
    {
        this.addKeyListener(this);
        new Timer().schedule(new Actualizacion(this), 100, 100);
    }

    public void paint(Graphics gr)
    {
        actualizar = false;
        update(gr);
        actualizar = true;
    }

    public void update(Graphics gr)
    {
        if(!imagenInternaCreada)
        {
            ImagenParaPintar = this.createImage(600, 300);
            Graficos = ImagenParaPintar.getGraphics();
            imagenInternaCreada = true;
        }
        util.pintar(Graficos);
        gr.drawImage(ImagenParaPintar, 0, 0, this);
    }

    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e)
    {
        switch(util.estado)
        {
            case INTRO1:
                if(e.getKeyCode() == KeyEvent.VK_ENTER){util.estado=INTRO2;}
                break;
            case INTRO2:
                if(e.getKeyCode() == KeyEvent.VK_ENTER){util.estado=MENU;}
                break;
            case MENU:
                if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_LEFT){if(util.seleccion==0){util.seleccion=4;}else{util.seleccion--;}}
                if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_RIGHT){if(util.seleccion==4){util.seleccion=0;}else{util.seleccion++;}}
                if(e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    switch(util.seleccion)
                    {
                        case 1:
                            util.estado = MENUMULTIJUEGO;
                            util.seleccion = 0;
                            break;
                        case 4:
                            try{Main.guardar();}catch(Exception ex){System.out.println(ex);}
                            System.exit(0);
                            break;
                    }
                }
                break;
            case MULTIJUEGO:
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE){util.terminarForzoso = true;}
                if(e.getKeyCode() == KeyEvent.VK_B){util.moverCañon1 = true;}
                if(e.getKeyCode() == KeyEvent.VK_COMMA){util.moverCañon2 = true;}

                if(e.getKeyCode() == KeyEvent.VK_W)
                {
                    if(util.moverCañon1){util.superiorJugador1 = Jugador.superiorArriba; util.moverCañon1 = false;}
                    else{util.moverJugador(0, Jugador.superiorArriba);}
                }
                if(e.getKeyCode() == KeyEvent.VK_S)
                {
                    if(util.moverCañon1){util.superiorJugador1 = Jugador.superiorAbajo; util.moverCañon1 = false;}
                    else{util.moverJugador(0, Jugador.superiorAbajo);}
                }
                if(e.getKeyCode() == KeyEvent.VK_A)
                {
                    if(util.moverCañon1){util.superiorJugador1 = Jugador.superiorIzquierda; util.moverCañon1 = false;}
                    else{util.moverJugador(0, Jugador.superiorIzquierda);}
                }
                if(e.getKeyCode() == KeyEvent.VK_D)
                {
                    if(util.moverCañon1){util.superiorJugador1 = Jugador.superiorDerecha; util.moverCañon1 = false;}
                    else{util.moverJugador(0, Jugador.superiorDerecha);}
                }
                
                if(e.getKeyCode() == KeyEvent.VK_UP)
                {
                    if(util.moverCañon2){util.superiorJugador2 = Jugador.superiorArriba; util.moverCañon2 = false;}
                    else{util.moverJugador(1, Jugador.superiorArriba);}
                }
                if(e.getKeyCode() == KeyEvent.VK_DOWN)
                {
                    if(util.moverCañon2){util.superiorJugador2 = Jugador.superiorAbajo; util.moverCañon2 = false;}
                    else{util.moverJugador(1, Jugador.superiorAbajo);}
                }
                if(e.getKeyCode() == KeyEvent.VK_LEFT)
                {
                    if(util.moverCañon2){util.superiorJugador2 = Jugador.superiorIzquierda; util.moverCañon2 = false;}
                    else{util.moverJugador(1, Jugador.superiorIzquierda);}
                }
                if(e.getKeyCode() == KeyEvent.VK_RIGHT)
                {
                    if(util.moverCañon2){util.superiorJugador2 = Jugador.superiorDerecha; util.moverCañon2 = false;}
                    else{util.moverJugador(1, Jugador.superiorDerecha);}
                }

                if(e.getKeyCode() == KeyEvent.VK_V)
                {
                    if(util.moverCañon1){util.moverCañon1 = false;}
                    else{util.disparar(0);}
                }
                if(e.getKeyCode() == KeyEvent.VK_MINUS)
                {
                    if(util.moverCañon2){util.moverCañon2 = false;}
                    else{util.disparar(1);}
                }

                break;
            case GANADOR:
                if(e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    util.reiniciarTodo();
                    util.estado = Pantalla.MENU;
                }
                break;
        }
    }

    public void keyReleased(KeyEvent e) {}

    public class Actualizacion extends TimerTask
    {
        Pantalla pantallaso;
        public Actualizacion(Pantalla pantalla)
        {
            pantallaso = pantalla;
        }
        public void run()
        {
            if(actualizar){pantallaso.paint(pantallaso.getGraphics());}
        }
    }
}