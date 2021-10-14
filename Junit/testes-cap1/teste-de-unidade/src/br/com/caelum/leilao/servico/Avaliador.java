package br.com.caelum.leilao.servico;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;

import java.util.ArrayList;
import java.util.List;

public class Avaliador {

    private Double maiorDeTodos = Double.NEGATIVE_INFINITY;
    private Double menorDeTodos = Double.POSITIVE_INFINITY;
    private Double mediaLances;
    private List<Lance> maiores;

    public void avalia(Leilao leilao) {

        if (leilao.getLances().size() == 0) {
            throw new RuntimeException("Erro!!!");
        }

        double valorTotalLances = 0;
        for (Lance lance : leilao.getLances()) {
            if (lance.getValor() > maiorDeTodos) {
                maiorDeTodos = lance.getValor();
            }
            if (lance.getValor() < menorDeTodos) {
                menorDeTodos = lance.getValor();
            }
            valorTotalLances += lance.getValor();
        }
        mediaLances = valorTotalLances / leilao.getLances().size();
        maioresLences(leilao);
    }

    private void maioresLences(Leilao leilao) {
        maiores = new ArrayList<>(leilao.getLances());
        maiores.sort((o1, o2) -> Double.compare(o2.getValor(), o1.getValor()));
        maiores = maiores.subList(0, Math.min(maiores.size(), 3));
    }

    public Double getMaiorDeTodos() {
        return maiorDeTodos;
    }

    public Double getMenorDeTodos() {
        return menorDeTodos;
    }

    public Double getMediaLances() {
        return mediaLances;
    }

    public List<Lance> getMaiores() {
        return maiores;
    }
}
