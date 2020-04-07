package soloviev.perez.vicente.ritmos;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AccesoFichero {

    public AccesoFichero(){
        File file = new File("/data/data/soloviev.perez.vicente.ritmos/files/fichero_marcas.txt");
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public ArrayList<Marca> mostrar() throws IOException{
        ArrayList<Marca> array = new ArrayList<>();
        Scanner sc;
        BufferedReader b = new BufferedReader(new FileReader("/data/data/soloviev.perez.vicente.ritmos/files/fichero_marcas.txt"));
        String readLine = "";
        Marca m;

        while ((readLine = b.readLine()) != null) {
            String[] arr = readLine.split(" ");

            String distancia = arr[1];
            String tiempo = arr[2];
            String desc = "";
            if (arr.length>=4) {
                //desc = arr[3];

                for (int i=3; i<arr.length; i++) {
                    desc += arr[i] + " ";
                }
            }

            Deporte d;
            if (arr[0].equals("Carrera")) d = Deporte.carrera;
            else d = Deporte.natacion;

            m = new Marca(d, desc, distancia, tiempo);
            array.add(m);
        }

        b.close();

        return array;
    }

    public void eliminarMarca(Marca marca) throws FileNotFoundException, IOException {
        String texto = marca.getTipo().toString() + " " + marca.getDistancia() + " " + marca.getTiempo() + " " + marca.getDescripcion();

        String fichero = "/data/data/soloviev.perez.vicente.ritmos/files/fichero_marcas.txt";
        String fichero_copia = "/data/data/soloviev.perez.vicente.ritmos/files/fichero_marcas_.txt";
        BufferedReader b = new BufferedReader(new FileReader(fichero));
        String readLine = "";

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(fichero_copia);     //crea fichero si no existe
        } catch (FileNotFoundException e) {
            Log.e("Ritmos", e.getMessage(),e);
        } catch (IOException e) {
            Log.e("Ritmos", e.getMessage(),e);
        }

        while ((readLine = b.readLine()) != null) {
            if (!(readLine).equals(texto)) {
                fos.write((readLine + "\n").getBytes());
            }else {
                Log.e("igual" , texto);
            }
            Log.e("EIII" , "--" + texto + "--" + readLine + "--");
            Log.e("EIII", String.valueOf(texto.equals(readLine)));
        }

        fos.close();

        File file = new File (fichero);
        if (file.exists()) file.delete();
        file = new File (fichero_copia);
        file.renameTo(new File (fichero));

    }


}
