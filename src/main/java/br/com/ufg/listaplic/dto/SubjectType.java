package br.com.ufg.listaplic.dto;

public enum SubjectType {

    INF0233("Engenharia Econômica para Software", "INF0233"),
    INF0137("Mercado Interno e Externo de Software", "INF0137"),
    INF0089("Integração 2", "INF0089"),
    INF0150("Prática em Engenharia de Software", "INF0150"),
    INF0207("Técnicas Avançadas de Construção de Software", "INF0207"),
    FAL0214("Introdução a Linguagem de Sinais", "FAL0214");

    private String name;
    private String code;

    SubjectType(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

}
