package com.m2i.tp.web;

import java.util.List;

import javax.faces.component.UIParameter;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.m2i.tp.entity.Operation;
import com.m2i.tp.service.ServiceCompte;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Named //ou bien @Component
//NB: @Component n'est pas bien interprété par assistant eclipse --> pas autocompletion du cote .xhtml
//  @Named est bien interprété partout (Spring , JSF , eclipse , ....) mais
//  nécessite javax.inject dans pom.xml
@Scope("request")
@Getter @Setter @NoArgsConstructor
public class OperationMBean {
	
	@Inject //ou bien @Autowired
	private ServiceCompte serviceCompte;   //service métier "spring" vers lequel on va déléguer
	
	
	private UIParameter paramNumCptSel;

	
	private Long numCpt;
	private List<Operation> listeOperations;
	
		
	public String listerDernieresOperations(){
		String suite="operations";//.jsp
		try {
			this.numCpt=(Long) paramNumCptSel.getValue();
			listeOperations=serviceCompte.operationsDuCompte(numCpt);
		} catch (Exception e) {
			e.printStackTrace();
			suite=null;
		}
		return suite;
	}
	

	public Long getNumCpt() {
		return numCpt;
	}

	public void setNumCpt(Long numCpt) {
		this.numCpt = numCpt;
	}

	public List<Operation> getListeOperations() {
		return listeOperations;
	}

	public void setListeOperations(List<Operation> listeOperations) {
		this.listeOperations = listeOperations;
	}

	public UIParameter getParamNumCptSel() {
		return paramNumCptSel;
	}

	public void setParamNumCptSel(UIParameter paramNumCptSel) {
		this.paramNumCptSel = paramNumCptSel;
	}

	
	
	

}
