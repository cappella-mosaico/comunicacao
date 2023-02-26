package entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Optional;

public class Pastoral {

    private Integer id;
    private String titulo;
    private String autor;
    private String descricao;
    private Boolean notificado;
    private String pequenoTitulo;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public Boolean getNotificado() { return notificado; }
    public void setNotificado(Boolean notificado) { this.notificado = notificado; }
    public String getPequenoTitulo() { return pequenoTitulo; }
    public void setPequenoTitulo(String pequenoTitulo) { this.pequenoTitulo = pequenoTitulo; }


    @Override
    public String toString() {
        String desc = Optional.ofNullable(descricao)
            .map((d) -> descricao.length() > 10 ? descricao.substring(0, 10) : descricao)
            .orElse("");

        return "Pastoral{" +
                "titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", descricao='" + desc + '\'' +
                ", pequenoTitulo='" + pequenoTitulo + '\'' +
                '}';
    }
}
