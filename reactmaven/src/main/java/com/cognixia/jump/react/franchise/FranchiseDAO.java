package com.cognixia.jump.react.franchise;
import java.util.List;

public interface FranchiseDAO {

	List<Franchise> getAllFranchise();

	Franchise getFranchisebyId(int id);
	Franchise getFranchisebyName(String name);
	
}
