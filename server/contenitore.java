import java.util.HashMap;

public class contenitore {
    HashMap<String,ServerThread> m = new HashMap<String, ServerThread>();

    public contenitore(){

    }

    public void aggiungi(String nome, ServerThread server){
        m.put(nome,server);
        for(String i:m.keySet()){
            m.get(i).invia(lista());
        }
    }

    public void messaggio(String utente,String messaggio){
        for(String i:m.keySet()){
            if(i.equals(utente))
            continue;
            m.get(i).invia(utente + messaggio);
        }
    }

    public String lista(){
    String lista = "lista:";
    for(String i:m.keySet()){
        lista+=i + ";";
    }
    return lista;
    }
}
