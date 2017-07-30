package tanques;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main
{
    public static Ini ini;

    public static FileInputStream archivoEntrada;
    public static FileOutputStream archivoSalida;
    public static ObjectInputStream objetoSerializadoObtenido;
    public static ObjectOutputStream objetoSerializadoAGuardar;

    public static void main(String[] args)
    {
        try
        {
            archivoEntrada = new FileInputStream("bd.jwc");
            objetoSerializadoObtenido = new ObjectInputStream(archivoEntrada);
            ini = (Ini) objetoSerializadoObtenido.readObject();
            archivoEntrada.close();
        }
        catch(Exception e)
        {
            ini = new Ini();
        }
        try
        {
            archivoSalida = new FileOutputStream("bd.jwc");
            objetoSerializadoAGuardar = new ObjectOutputStream(archivoSalida);
        }
        catch(Exception e)
        {
            System.out.println("Error Grave");
        }
        ini.setVisible(true);
    }

    public static void guardar() throws IOException
    {
        objetoSerializadoAGuardar.writeObject(ini);
        objetoSerializadoAGuardar.close();
    }
}