package LexicalAndSyntacticAnalyzer.objectAnalyzer;

import java.util.List;

import com.google.gson.Gson;

public class ConveterUserToJson {
    public String ConveterUserToJson(List<User> listaUser){
        String returJson = "";
        // Convertir la lista de objetos a JSON
        Gson gson = new Gson();
        String json = gson.toJson(listaUser);
        return json;
    }
}
