package com.example.android.databasetest;


public class Produtos {
    private String nome;
    private int quantidade;
    private String descricao;
    private double preco;

    public Produtos(String nome, int quantidade, String descricao, double preco) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "Produto: " + nome + "   Qtd: " + quantidade + "   Descricao: " + descricao +
                "   preco: " + preco + " brutis";
    }
}
