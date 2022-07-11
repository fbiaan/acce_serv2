package com.service2;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;


@Entity
@Table(name = "movis_bank")
public class MovisModel {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
	private Integer idMovi;
	
	private Date fecha;
	private Integer idcli;
	private String detalle;
	private Double monto;
	
	public MovisModel() {
		super();
	}
	
	public Integer getIdMovi() {
		return idMovi;
	}
	public void setIdMovi(Integer idMovi) {
		this.idMovi = idMovi;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Integer getIdCli() {
		return idcli;
	}
	public void setIdCli(Integer idcli) {
		this.idcli = idcli;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	public Double getMonto() {
		return monto;
	}
	public void setMonto(Double monto) {
		this.monto = monto;
	}
	
	
	
	
}
