package br.edu.iftm.testes;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import br.edu.iftm.api.Api;
import br.edu.iftm.api.JsonParser;

public class ApiSimplesTeste {
	
	public static void main(String[] args) throws IOException {
		Api api = new Api("https://pokeapi.co/api/v2/pokemon/1");
		
		JSONObject resposta = JsonParser.parseToObject(api.executar());
		String name = resposta.getString("name");
		int weight = resposta.getInt("weight");
		
		JSONArray habilidades = resposta.getJSONArray("abilities");
		
		for (int indice = 0; indice < habilidades.length(); indice++) {
			JSONObject habilidadeCompleta = habilidades.getJSONObject(indice);
			int slot = habilidadeCompleta.getInt("slot");
			boolean isHidden = habilidadeCompleta.getBoolean("is_hidden");
			
			
			JSONObject habilidade = habilidadeCompleta.getJSONObject("ability");
			String nomeHabilidade = habilidade.getString("name");
			String url = habilidade.getString("url");
			
			System.out.printf("Slot: %d \tIsHidden: %b \t", slot, isHidden);
			System.out.printf("Habilidade: %s \t Url: %s \n", nomeHabilidade, url);
			
			Api requisicaoHabilidade = new Api(url);
			JSONObject habilidadeDetalhada = JsonParser.parseToObject(requisicaoHabilidade.executar());
			int id = habilidadeDetalhada.getInt("id");
			System.out.println(id);
		}
	}

}
