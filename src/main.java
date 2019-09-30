import KDC.KDC;
import usuario.usuario;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


public class main {

    public static void main(String[] args) throws BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, UnsupportedEncodingException, NoSuchPaddingException, InvalidKeyException {
        // cria os usuarios bob e alice
        usuario bob = new usuario("Bob");
        usuario alice = new usuario("Alice");

        KDC sessaoAtual = new KDC();
        // kdc é iniciado
        sessaoAtual.armazenaUser1(bob);
        sessaoAtual.armazenaUser2(alice);
        // é enviado seus keys para o kdc

        main.separa();
        sessaoAtual.pedirConversa(bob,alice,bob.gerarNonce());
        // bob pede a covnersao ao kdc

        sessaoAtual.devolveKdc();
        // kdc devolve as informações para bob e envia para a alice

        main.separa();

        alice.enviafriendnonce(bob);
        System.out.println("envia nonce para o bob");

        //alice envia seu nonce para bob com chave session key
        main.separa();
        System.out.println("bob recebe o nonce de alice e adiciona valor+1 a seu nonce criptografa de envia de volta");
        System.out.println("alice verifica o nonce e Sucesso!!");
        main.separa();
        alice.verificaNonce(bob.editaFriendNonce());
        //alice alice verifica se oque bob devolveu estava correto ( foi ultilizado valores random e somando 1 so para test)
        main.separa();

        byte[] msg = bob.enviarMsg("Ta induu", alice);

        alice.receberMsg(msg);

    }

    public static void separa(){
        System.out.println("------------------------------");
    }


}
