package br.com.food_rescue_api.model;

public class User {
    private Long id;
    private String name;
    private String descricao;

    public User() {}

    public User(Long id, String name, String descricao) {
        this.id = id;
        this.name = name;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "User{id=" + id + ", name='" + name + "', descricao='" + descricao + "'}";
    }
}
