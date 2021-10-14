package br.com.caelum.leilao.dominio;

import java.util.*;
import java.util.stream.Collectors;

public class Leilao {

	private String descricao;
	private Calendar data;
	private List<Lance> lances;
	private boolean encerrado;
	private int id;

	public Leilao(String descricao) {
		this(descricao, Calendar.getInstance());
	}

	public Leilao(String descricao, Calendar data) {
		this.descricao = descricao;
		this.data = data;
		this.lances = new ArrayList<Lance>();
	}
	
	public void propoe(Lance lance) {
		long total = lances.stream().filter(i -> Objects.equals(i.getUsuario(), lance.getUsuario())).count();
		if (lances.isEmpty() || !Objects.equals(lances.get(lances.size()-1).getUsuario(), lance.getUsuario()) && total < 5) {
			lances.add(lance);
		}
	}

	public void dobraLance(Usuario usuario) {
		List<Lance> lacesUsuario = lances.stream().filter(i -> Objects.equals(i.getUsuario(), usuario)).collect(Collectors.toList());
		Lance ultimolance = lacesUsuario.get(lacesUsuario.size()-1);
		propoe(new Lance(usuario, ultimolance.getValor()*2));
	}

	public void encerra() {
		this.encerrado = true;
	}

	public String getDescricao() {
		return descricao;
	}

	public List<Lance> getLances() {
		return Collections.unmodifiableList(lances);
	}

	public Calendar getData() {
		return data;
	}

	public boolean isEncerrado() {
		return encerrado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
