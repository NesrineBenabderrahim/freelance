package tn.essat.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
	import tn.essat.model.Proposition;

	public interface IProposition extends JpaRepository<Proposition, Integer> {
	    List<Proposition> findByProjClientId(Integer clientId);

	}