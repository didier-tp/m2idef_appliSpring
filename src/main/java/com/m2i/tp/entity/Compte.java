package com.m2i.tp.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Compte {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)//auto_increment mysql
	private Long numero;
	
	@Column(length=32) //VARCHAR(32) si table générée en fonction de java
	private String label;
	
	private Double solde;


	public Compte(Long numero, String label, Double solde) {
		super();
		this.numero = numero;
		this.label = label;
		this.solde = solde;
	}
	
	@ManyToMany
	@JoinTable(name="ClientCompte",joinColumns={@JoinColumn(name="numCpt")},
	inverseJoinColumns={@JoinColumn(name="numCli")})
	private List<Client> proprietaires;
	
	@OneToMany(mappedBy="compte",cascade={CascadeType.ALL})
	private List<Operation> operations;
	
		
	public void addOperation(Operation op){
		if(operations==null)
			operations=new ArrayList<Operation>();
		op.setCompte(this);
		operations.add(op);
	}

	@Override
	public String toString() {
		return "Compte [numero=" + numero + ", label=" + label + ", solde=" + solde + "]";
	}

	

}
