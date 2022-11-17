package entities;

import java.time.LocalDateTime;
import java.time.YearMonth;

public class RelatorioFinanceiro {

  private Integer id;
  private YearMonth anoMes;
  private Float entradas;
  private Float saidas;
  private Float orcado;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private Boolean notificado;

  public Integer getId() { return id; }
  public YearMonth getAnoMes() { return anoMes; }
  public Float getEntradas() { return entradas; }
  public Float getSaidas() { return saidas; }
  public Float getOrcado() { return orcado; }
  public LocalDateTime getCreatedAt() { return createdAt; }
  public LocalDateTime getUpdatedAt() { return updatedAt; }
  public Boolean getNotificado() { return notificado; }

  public void setId(Integer id) { this.id = id; }
  public void setAnoMes(YearMonth anoMes) { this.anoMes = anoMes; }
  public void setEntradas(Float entradas) { this.entradas = entradas; }
  public void setSaidas(Float saidas) { this.saidas = saidas; }
  public void setOrcado(Float orcado) { this.orcado = orcado; }
  public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
  public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
  public void setNotificado(Boolean notificado) { this.notificado = notificado; }


}
