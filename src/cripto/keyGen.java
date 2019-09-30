package cripto;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Random;


public class keyGen {


    private char[] array;
    public keyGen(){

    }


    public static int criaNumero(int minimo, int maximo) {
        Random random = new Random();
        return random.nextInt((maximo - minimo) + 1) + minimo;
    }

    public static String getKey(){
        char[] array = new char[16];

        for(int i = 0; i < 16; i++){
            array[i] = (char) criaNumero(48,122);
        }

        String str = new String(array);

        return str;
    }

    @NotNull
    public static String getIdUser(@NotNull String nome){
        char[] array = nome.toCharArray();

        for(int k = 0; k < nome.length(); k++){
            array[k] = (char) criaNumero(48,122);
        }

        String str = new String(array);

        return str;
    }


}
