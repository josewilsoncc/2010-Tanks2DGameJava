package tanques;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.Vector;
import javax.swing.ImageIcon;

public class util
{
    public static Dimension tamañoPantalla = Toolkit.getDefaultToolkit().getScreenSize();
    public static ConfiguracionModoJuego configuracionModoJuego = new ConfiguracionModoJuego();

    public static int estado = Pantalla.INTRO1;
    public static int seleccion = 0;

    public static boolean mostrandoConfiguracion = false;
    public static boolean terminarForzoso = false;

    public final static int MUERTES = 0;
    public final static int TIEMPO = 1;
    public final static int GOLPES = 2;

    public final static int PVDEFECTO = 30;

    public static int cantidadMuertes = 3;
    public static int cantidadMinutos = 5;
    public static int cantidadGolpes = 3;

    public static int modo = MUERTES;

    public static int decimas = 0;
    public static int segundos = 0;
    public static int minutos = 0;

    //Estaticas de los 2 jugadores
    public static int xJugador1;
    public static int yJugador1;
    public static int pvJugador1 = PVDEFECTO;
    public static int golpesJugador1 = 0;
    public static int muertesJugador1 = 0;
    public static int baseJugador1 = Jugador.baseVertical;
    public static int superiorJugador1 = Jugador.superiorAbajo;
    public static boolean moverCañon1 = false;
    public static Color colorJugador1 = new Color(255,0,0);

    public static int xJugador2;
    public static int yJugador2;
    public static int pvJugador2 = PVDEFECTO;
    public static int golpesJugador2 = 0;
    public static int muertesJugador2 = 0;
    public static int baseJugador2 = Jugador.baseVertical;
    public static int superiorJugador2 = Jugador.superiorArriba;
    public static boolean moverCañon2 = false;
    public static Color colorJugador2 = new Color(0,0,255);

    public static Mapa mapa = new Mapa();

    public static Jugador jugador1;
    public static Jugador jugador2;

    public static Vector<Bala> balas = new Vector<Bala>();

    public static ImageIcon fondojuego = imagen("img/extra/fondojuego.png");
    public static ImageIcon menuVacio = imagen("img/menu/mm.png");
    public static ImageIcon menu1 = imagen("img/menu/m1.png");
    public static ImageIcon menu2 = imagen("img/menu/m2.png");
    public static ImageIcon menu3 = imagen("img/menu/m3.png");
    public static ImageIcon menu4 = imagen("img/menu/m4.png");
    public static ImageIcon menu5 = imagen("img/menu/m5.png");
    public static ImageIcon barra1 = imagen("img/extra/barra1.png");
    public static ImageIcon pvfondo = imagen("img/extra/pvfondo.png");
    public static ImageIcon pvface = imagen("img/extra/pvface.png");
    public static ImageIcon pv1 = imagen("img/extra/pv1.png");
    public static ImageIcon pv2 = imagen("img/extra/pv2.png");
    public static ImageIcon fondoclock = imagen("img/extra/fondoclock.png");

    public static ImageIcon imagen(String url)
    {
        return new ImageIcon(Toolkit.getDefaultToolkit().createImage(url));
    }

    public static void pintar(Graphics gr)
    {
        switch(estado)
        {
            case Pantalla.INTRO1:
                gr.drawImage(imagen("img/fondos/intro1.png").getImage(), 0, 0, null);
                gr.setColor(new Color(255,255,255));
                gr.drawString("Presiona Enter", 260, 285);
                gr.drawString("Contador "+Ini.contador, 260, 270);
                Ini.contador++;
                gr.setColor(new Color(0));
                break;
            case Pantalla.INTRO2:
                gr.drawImage(imagen("img/fondos/intro2.png").getImage(), 0, 0, null);
                gr.setColor(new Color(255,255,255));
                gr.drawString("Presiona Enter", 260, 285);
                gr.setColor(new Color(0));
                break;
            case Pantalla.MENU:
                gr.drawImage(imagen("img/fondos/fondo1.png").getImage(), 0, 0, null);
                gr.setColor(new Color(0));
                gr.fillRect(1, 275, 147, 20);
                gr.setColor(new Color(255,255,255));
                gr.drawRect(1, 275, 147, 20);
                gr.drawString("JWCORPORACION 2010", 5, 290);
                gr.setColor(new Color(0));

                gr.drawImage(menuVacio.getImage(), 175, 0, null);
                gr.drawImage(menuVacio.getImage(), 175, 60, null);
                gr.drawImage(menuVacio.getImage(), 175, 120, null);
                gr.drawImage(menuVacio.getImage(), 175, 180, null);
                gr.drawImage(menuVacio.getImage(), 175, 240, null);

                if(seleccion == 0)gr.drawImage(menu1.getImage(), 175, 0, null);
                if(seleccion == 1)gr.drawImage(menu2.getImage(), 175, 60, null);
                if(seleccion == 2)gr.drawImage(menu3.getImage(), 175, 120, null);
                if(seleccion == 3)gr.drawImage(menu4.getImage(), 175, 180, null);
                if(seleccion == 4)gr.drawImage(menu5.getImage(), 175, 240, null);
                break;
            case Pantalla.MENUMULTIJUEGO:
                posicionarJugador();
                gr.drawImage(imagen("img/fondos/fondo1.png").getImage(), 0, 0, null);
                gr.setColor(new Color(0));
                gr.fillRect(1, 275, 147, 20);
                gr.setColor(new Color(255,255,255));
                gr.drawRect(1, 275, 147, 20);
                gr.drawString("JWCORPORACION 2010", 5, 290);
                pintarString(gr, "Cargando...", 185, 180, 40);
                gr.setColor(new Color(0));
                if(!mostrandoConfiguracion){configuracionModoJuego.setVisible(true);mostrandoConfiguracion=true;}
                break;
            case Pantalla.MULTIJUEGO:
                configuracionModoJuego.setVisible(false);
                mostrandoConfiguracion = false;
                gr.fillRect(0, 0, 600, 300);
                gr.drawImage(fondojuego.getImage(), 0, 0, null);
                mapa.paint(gr, 0);
                mapa.paint(gr, 1);
                gr.setClip(0,0,300,300);
                jugador2.paint(gr, (xJugador2*60)-(xJugador1*60)+120, (yJugador2*60)-(yJugador1*60)+120);
                gr.setClip(300, 0, 300, 300);
                jugador1.paint(gr, (xJugador1*60)-(xJugador2*60)+420, (yJugador1*60)-(yJugador2*60)+120);
                gr.setClip(0,0,600,300);
                jugador1.paint(gr, 120, 120);
                jugador2.paint(gr, 420, 120);
                gr.setColor(new Color(0));
                borrarBalas();
                pintarBalas(gr);
                pintarPV(gr);
                pintarMuertes(gr);
                gr.drawImage(barra1.getImage(), 297, 0, null);
                pintarClock(gr);
                if(finDeLaRonda()){balas.clear(); estado = Pantalla.GANADOR;}
                break;
            case Pantalla.GANADOR:
                balas.clear();
                gr.drawImage(imagen("img/fondos/fondo2.png").getImage(), 0, 0, null);
                if(!terminarForzoso)
                {
                    switch(modo)
                    {
                        case MUERTES:
                            if(muertesJugador1 >= cantidadMuertes)
                            {
                                pintarString(gr, "PERDEDOR", 15, 30, 30);
                                pintarString(gr, "GANADOR", 315, 30, 30);
                            }
                            else
                            {
                                pintarString(gr, "PERDEDOR", 315, 30, 30);
                                pintarString(gr, "GANADOR", 15, 30, 30);
                            }
                            break;
                        case GOLPES:
                            if(golpesJugador1 >= cantidadGolpes)
                            {
                                pintarString(gr, "PERDEDOR", 15, 30, 30);
                                pintarString(gr, "GANADOR", 315, 30, 30);
                            }
                            else
                            {
                                pintarString(gr, "PERDEDOR", 315, 30, 30);
                                pintarString(gr, "GANADOR", 15, 30, 30);
                            }
                            break;
                        case TIEMPO:
                            if(muertesJugador1 != muertesJugador2)
                            {
                                if(muertesJugador1 > muertesJugador2)
                                {
                                    pintarString(gr, "PERDEDOR", 15, 30, 30);
                                    pintarString(gr, "GANADOR", 315, 30, 30);
                                }
                                else
                                {
                                    pintarString(gr, "PERDEDOR", 315, 30, 30);
                                    pintarString(gr, "GANADOR", 15, 30, 30);
                                }
                            }
                            else
                            {
                                if(pvJugador1 >= pvJugador2)
                                {
                                    pintarString(gr, "PERDEDOR", 315, 30, 30);
                                    pintarString(gr, "GANADOR", 15, 30, 30);
                                }
                                else
                                {
                                    pintarString(gr, "PERDEDOR", 15, 30, 30);
                                    pintarString(gr, "GANADOR", 315, 30, 30);
                                }
                            }

                            break;
                    }
                }
                else
                {
                    if(golpesJugador1 > golpesJugador2)
                    {
                        pintarString(gr, "PERDEDOR", 15, 30, 30);
                        pintarString(gr, "GANADOR", 315, 30, 30);
                    }
                    else
                    {
                        pintarString(gr, "PERDEDOR", 315, 30, 30);
                        pintarString(gr, "GANADOR", 15, 30, 30);
                    }
                }
                gr.drawImage(barra1.getImage(), 297, 0, null);
                pintarString(gr, "Numero de muertes:  " + muertesJugador1, 15, 60, 12);
                pintarString(gr, "Numero de muertes:  " + muertesJugador2, 315, 60, 12);
                pintarString(gr, "Numero de asesinatos:  " + muertesJugador2, 15, 75, 12);
                pintarString(gr, "Numero de asesinatos:  " + muertesJugador1, 315, 75, 12);
                pintarString(gr, "Numero de golpes dados:  " + golpesJugador2, 15, 90, 12);
                pintarString(gr, "Numero de golpes dados:  " + golpesJugador1, 315, 90, 12);
                pintarString(gr, "Numero de golpes recibidos:  " + golpesJugador1, 15, 105, 12);
                pintarString(gr, "Numero de golpes recibidos:  " + golpesJugador2, 315, 105, 12);
                pintarString(gr, "Sangre derramada:  " + (golpesJugador1*2), 15, 120, 12);
                pintarString(gr, "Sangre derramada:  " + (golpesJugador2*2), 315, 120, 12);
                break;
        }
    }

    public static void pintarString(Graphics gr, String texto, int x, int y, int tamaño)
    {
        Font letra = gr.getFont();
        gr.setFont(new Font("",1,tamaño));
        gr.setColor(new Color(255,255,255));
        gr.drawString(texto, x-1, y-1);
        gr.drawString(texto, x-1, y);
        gr.drawString(texto, x-1, y+1);
        gr.drawString(texto, x, y-1);
        gr.drawString(texto, x, y+1);
        gr.drawString(texto, x+1, y-1);
        gr.drawString(texto, x+1, y);
        gr.drawString(texto, x+1, y+1);
        gr.setColor(new Color(0));
        gr.drawString(texto, x, y);
        gr.setFont(letra);
    }

    public static void moverJugador(int jugador, int direccion)
    {
        if(jugador == 0)
        {
            if(direccion == Jugador.superiorArriba && mapa.mapa[util.yJugador1-1][util.xJugador1] == 5 &&
            (util.yJugador1-1 != util.yJugador2 || util.xJugador1 != util.xJugador2))
            {
                util.yJugador1--;
                util.baseJugador1 = Jugador.baseVertical;
            }
            if(direccion == Jugador.superiorAbajo && mapa.mapa[util.yJugador1+1][util.xJugador1] == 5 &&
            (util.yJugador1+1 != util.yJugador2 || util.xJugador1 != util.xJugador2))
            {
                util.yJugador1++;
                util.baseJugador1 = Jugador.baseVertical;
            }
            if(direccion == Jugador.superiorIzquierda && mapa.mapa[util.yJugador1][util.xJugador1-1] == 5 &&
            (util.yJugador1 != util.yJugador2 || util.xJugador1-1 != util.xJugador2))
            {
                util.xJugador1--;
                util.baseJugador1 = Jugador.baseHorizontal;
            }
            if(direccion == Jugador.superiorDerecha && mapa.mapa[util.yJugador1][util.xJugador1+1] == 5 &&
            (util.yJugador1 != util.yJugador2 || util.xJugador1+1 != util.xJugador2))
            {
                util.xJugador1++;
                util.baseJugador1 = Jugador.baseHorizontal;
            }
        }
        if(jugador == 1)
        {
            if(direccion == Jugador.superiorArriba && mapa.mapa[util.yJugador2-1][util.xJugador2] == 5 &&
            (util.yJugador2-1 != util.yJugador1 || util.xJugador2 != util.xJugador1))
            {
                util.yJugador2--;
                util.baseJugador2 = Jugador.baseVertical;
            }
            if(direccion == Jugador.superiorAbajo && mapa.mapa[util.yJugador2+1][util.xJugador2] == 5 &&
            (util.yJugador2+1 != util.yJugador1 || util.xJugador2 != util.xJugador1))
            {
                util.yJugador2++;
                util.baseJugador2 = Jugador.baseVertical;
            }
            if(direccion == Jugador.superiorIzquierda && mapa.mapa[util.yJugador2][util.xJugador2-1] == 5 &&
            (util.yJugador2 != util.yJugador1 || util.xJugador2-1 != util.xJugador1))
            {
                util.xJugador2--;
                util.baseJugador2 = Jugador.baseHorizontal;
            }
            if(direccion == Jugador.superiorDerecha && mapa.mapa[util.yJugador2][util.xJugador2+1] == 5 &&
            (util.yJugador2 != util.yJugador1 || util.xJugador2+1 != util.xJugador1))
            {
                util.xJugador2++;
                util.baseJugador2 = Jugador.baseHorizontal;
            }
        }
    }

    public static void disparar(int numeroJugador)
    {
        if( balas.size() < 200 )
        {
            boolean noHayBalas = true;
            if(numeroJugador == 0)
            {
                switch(superiorJugador1)
                {
                    case Jugador.superiorArriba:
                        if(mapa.mapa[util.yJugador1-1][xJugador1] == 5)
                        {
                            for (int i = 0; i < balas.size() && noHayBalas; i++)
                            {
                                if(balas.get(i).x == xJugador1 && balas.get(i).y == yJugador1-1){noHayBalas=false;}
                            }
                            if(noHayBalas){balas.add(new Bala(xJugador1,yJugador1-1,Jugador.superiorArriba));}
                        }
                        break;
                    case Jugador.superiorAbajo:
                        if(mapa.mapa[yJugador1+1][xJugador1] == 5)
                        {
                            for (int i = 0; i < balas.size() && noHayBalas; i++)
                            {
                                if(balas.get(i).x == xJugador1 && balas.get(i).y == yJugador1+1){noHayBalas=false;}
                            }
                            if(noHayBalas){balas.add(new Bala(xJugador1,yJugador1+1,Jugador.superiorAbajo));}
                        }
                        break;
                    case Jugador.superiorIzquierda:
                        if(mapa.mapa[yJugador1][xJugador1-1] == 5)
                        {
                            for (int i = 0; i < balas.size() && noHayBalas; i++)
                            {
                                if(balas.get(i).x == xJugador1-1 && balas.get(i).y == yJugador1){noHayBalas=false;}
                            }
                            if(noHayBalas){balas.add(new Bala(xJugador1-1,yJugador1,Jugador.superiorIzquierda));}
                        }
                        break;
                    case Jugador.superiorDerecha:
                        if(mapa.mapa[yJugador1][xJugador1+1] == 5)
                        {
                            for (int i = 0; i < balas.size() && noHayBalas; i++)
                            {
                                if(balas.get(i).x == xJugador1+1 && balas.get(i).y == yJugador1){noHayBalas=false;}
                            }
                            if(noHayBalas){balas.add(new Bala(xJugador1+1,yJugador1,Jugador.superiorDerecha));}
                        }
                        break;
                }
            }
            else
            {
                switch(superiorJugador2)
                {
                    case Jugador.superiorArriba:
                        if(mapa.mapa[util.yJugador2-1][util.xJugador2] == 5)
                        {
                            balas.add(new Bala(util.xJugador2,util.yJugador2-1,Jugador.superiorArriba));
                        }
                        break;
                    case Jugador.superiorAbajo:
                        if(mapa.mapa[util.yJugador2+1][util.xJugador2] == 5)
                        {
                            balas.add(new Bala(util.xJugador2,util.yJugador2+1,Jugador.superiorAbajo));
                        }
                        break;
                    case Jugador.superiorIzquierda:
                        if(mapa.mapa[util.yJugador2][util.xJugador2-1] == 5)
                        {
                            balas.add(new Bala(util.xJugador2-1,util.yJugador2,Jugador.superiorIzquierda));
                        }
                        break;
                    case Jugador.superiorDerecha:
                        if(mapa.mapa[util.yJugador2][util.xJugador2+1] == 5)
                        {
                            balas.add(new Bala(util.xJugador2+1,util.yJugador2,Jugador.superiorDerecha));
                        }
                        break;
                }
            }
        }
    }

    public static void pintarBalas(Graphics gr)
    {
        for(int i = 0; i < balas.size(); i++)
        {
            balas.get(i).paint(gr);
        }
    }

    public static void borrarBalas()
    {
        for(int i = 0; i<balas.size();i++)
        {
            if(balas.get(i).borrame)
            {
                balas.remove(i);
            }
        }
    }

    public static void pintarPV(Graphics gr)
    {
        if(!(pvJugador1>0)){pvJugador1=PVDEFECTO;muertesJugador1++;}
        if(!(pvJugador2>0)){pvJugador2=PVDEFECTO;muertesJugador2++;}
        gr.drawImage(pv1.getImage(), 178, 0, null);
        gr.drawImage(pvfondo.getImage(), 235, 5, null);
        gr.setClip(235, 5, pvJugador1*2 ,10);
        gr.drawImage(pvface.getImage(), 235, 5, null);
        gr.setClip(0, 0, 600, 300);
        gr.drawImage(pv2.getImage(), 302, 0, null);
        gr.drawImage(pvfondo.getImage(), 305, 5, null);
        gr.setClip(305, 5, pvJugador2*2 ,10);
        gr.drawImage(pvface.getImage(), 305, 5, null);
        gr.setClip(0, 0, 600, 300);
    }

    public static void pintarClock(Graphics gr)
    {
        if(decimas < 9){decimas++;}
        else
        {
            decimas=0;
            if(segundos < 59){segundos++;}
            else
            {
                segundos=0;
                if(minutos<99){minutos++;}else{minutos=0;}
            }
        }
        gr.drawImage(fondoclock.getImage(), 275, 282, null);
        gr.setColor(new Color(255,255,255));
        if(minutos<10)
        {gr.drawString("0"+minutos+":"+segundos+","+decimas, 279, 295);}
        else
        {gr.drawString(minutos+":"+segundos+","+decimas, 279, 295);}
    }

    public static void pintarMuertes(Graphics gr)
    {
        gr.setColor(new Color(255,255,255));
        gr.drawString(""+muertesJugador1, 282, 28);
        gr.drawString(""+muertesJugador2, 304, 28);
        gr.setColor(new Color(0));
    }

    public static boolean finDeLaRonda()
    {
        if(terminarForzoso){return true;}
        if(muertesJugador1 >= 99 || muertesJugador2 >= 99){ return true;}
        switch(modo)
        {
            case MUERTES:
                if(muertesJugador1 >= cantidadMuertes || muertesJugador2 >= cantidadMuertes){ return true;}
                break;
            case TIEMPO:
                if(minutos >= cantidadMinutos){ return true; }
                break;
            case GOLPES:
                if(golpesJugador1 >= cantidadGolpes || golpesJugador2 >= cantidadGolpes){ return true;}
                break;
        }
        return false;
    }

    public static void reiniciarTodo()
    {
        decimas = 0;
        segundos = 0;
        minutos = 0;
        pvJugador1 = PVDEFECTO;
        golpesJugador1 = 0;
        muertesJugador1 = 0;
        baseJugador1 = Jugador.baseVertical;
        superiorJugador1 = Jugador.superiorAbajo;
        moverCañon1 = false;
        pvJugador2 = PVDEFECTO;
        golpesJugador2 = 0;
        muertesJugador2 = 0;
        baseJugador2 = Jugador.baseVertical;
        superiorJugador2 = Jugador.superiorArriba;
        moverCañon2 = false;
        terminarForzoso = false;
        posicionarJugador();
    }

    public static void posicionarJugador()
    {
        jugador1 = new Jugador(0, 2, 1);
        jugador2 = new Jugador(1, 3, 3);
    }
}
