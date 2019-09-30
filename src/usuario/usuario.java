package usuario;
import cripto.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class usuario {

    private String nome;
    private String idUser;
    private String fixKey;
    private String keySession = null;
    private String friend;
    private String nonce;
    private String friendNonce;
    private byte[] friendNonceRecebido;
    private byte[] testNonce = null;

    public usuario(String nome){
        this.nome = nome;
        this.idUser = keyGen.getIdUser(nome);
        this.fixKey = keyGen.getKey();
    }

    public String enviarKeyKdc(){
        return fixKey;
    }


    public void getSessionKey(byte[] keysession) throws BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, UnsupportedEncodingException, NoSuchPaddingException, InvalidKeyException {
        this.keySession = AES.decifra(keysession, fixKey);
    }

    public String getNome(){
        return this.nome;
    }

    public String getId(){
        return idUser;
    }



    public void getIdFriend(byte[] friend) throws BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, UnsupportedEncodingException, NoSuchPaddingException, InvalidKeyException {
        this.friend = AES.decifra(friend, keySession);
    }

    public byte[] gerarNonce() throws BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, UnsupportedEncodingException, NoSuchPaddingException, InvalidKeyException {
        // sempre que gerar nonce for ativado criara um nonce diferente
        String nonce = keyGen.getIdUser(keyGen.getKey());
        this.nonce = nonce;
        return AES.cifra(nonce, fixKey);
    }

    public void getNonceKdc(byte[] test){
        this.testNonce = test;
        System.out.println("verifica o nonce do kdc");
    }

    public byte[] geraFriendNonce() throws BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, UnsupportedEncodingException, NoSuchPaddingException, InvalidKeyException {
        int friendNonce = keyGen.criaNumero(1500, 9999);
        this.friendNonce = Integer.toString(friendNonce);
        return AES.cifra(this.friendNonce, keySession);
    }


    public void enviafriendnonce(usuario user) throws BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, UnsupportedEncodingException, NoSuchPaddingException, InvalidKeyException {
        user.getfrindnonce(geraFriendNonce());

    }

    public void getfrindnonce(byte[] friendNonce){
        this.friendNonceRecebido = friendNonce;

    }

    public byte[] editaFriendNonce() throws BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, UnsupportedEncodingException, NoSuchPaddingException, InvalidKeyException {
        int nonceatt = Integer.parseInt(AES.decifra(friendNonceRecebido,keySession));
        int n = nonceatt + 1;
        return AES.cifra(Integer.toString(n), this.keySession);
    }

    public void verificaNonce(byte[] non) throws BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, UnsupportedEncodingException, NoSuchPaddingException, InvalidKeyException {
        String n = AES.decifra(non, this.keySession);
        int noncee = Integer.parseInt(n) - 1;
        if(Integer.toString(noncee).equals(friendNonce)){
            System.out.println("VEIFICAÇÂO COMPLETA A CONVERSA ESTA SEGURA");
        }

    }

    public byte[] enviarMsg(String msg, usuario para) throws BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, UnsupportedEncodingException, NoSuchPaddingException, InvalidKeyException {
        return AES.cifra(msg,keySession);
    }

    public void receberMsg(byte[] cript) throws BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, UnsupportedEncodingException, NoSuchPaddingException, InvalidKeyException {
        System.out.println(AES.decifra(cript,keySession));

    }




}
