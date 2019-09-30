package KDC;
import cripto.*;
import usuario.usuario;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class KDC {
    private String keyUser1;
    private String keyUser2;
    private usuario usuario1;
    private usuario usuario2;
    private String userID1;
    private String userID2;
    private String nonceUser;
    private String KeySession;


    public KDC(){

    }

    public void armazenaUser1(usuario user1){
        this.usuario1 = user1;
        keyUser1 = user1.enviarKeyKdc();
        System.out.println("armazena key " + user1.getNome());
    }

    public void armazenaUser2(usuario user2){
        this.usuario2 = user2;
        keyUser2 = user2.enviarKeyKdc();
        System.out.println("armazena key " + user2.getNome());
    }


    public void pedirConversa(usuario user1 , usuario user2, byte[] nonce) throws BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, UnsupportedEncodingException, NoSuchPaddingException, InvalidKeyException {
        this.userID1 = user1.getId();
        this.userID2 = user2.getId();
        this.nonceUser = AES.decifra(nonce,keyUser1);
        System.out.println(user1.getNome() + " pede a conversa para kdc");
        System.out.println(user1.getNome() + " passa o nonce para o kdc");

    }

    public void devolveKdc() throws BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, UnsupportedEncodingException, NoSuchPaddingException, InvalidKeyException {
        System.out.println("KDC devolve as iformações para os usuarios");
        this.KeySession = keyGen.getKey();
        usuario1.getSessionKey(AES.cifra(KeySession,keyUser1));
        usuario1.getNonceKdc(AES.cifra(nonceUser,KeySession));
        usuario2.getSessionKey(AES.cifra(KeySession,keyUser2));
        usuario2.getIdFriend(AES.cifra(userID1, KeySession));

    }




}
