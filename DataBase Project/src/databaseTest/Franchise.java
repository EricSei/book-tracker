package databaseTest;

public class Franchise {
	public int getFranchiseID() {
		return franchiseID;
	}

	public void setFranchiseID(int franchiseID) {
		this.franchiseID = franchiseID;
	}

	public String getFranchiseName() {
		return franchiseName;
	}

	public void setFranchiseName(String franchiseName) {
		this.franchiseName = franchiseName;
	}

	private int franchiseID;
	private String franchiseName;
	
	public Franchise() {
		// TODO Auto-generated constructor stub
		
	}

	@Override
	public String toString() {
		return "Franchise [franchiseID=" + franchiseID + ", franchiseName=" + franchiseName + "]";
	}

}
