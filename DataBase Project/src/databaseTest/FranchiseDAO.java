package databaseTest;

import java.util.List;

public interface FranchiseDAO {

	List<Franchise> getAllFranchise();

	Franchise getFranchisebyId(int id);
	Franchise getFranchisebyName(String name);
	boolean addFranchise(Franchise Franchise);
	boolean deleteFranchisebyId(int id);
	boolean deleteFranchise(Franchise Franchise);
	boolean updateFranchise(Franchise Franchise);
}
