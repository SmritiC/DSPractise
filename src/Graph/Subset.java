package Graph;

public class Subset {
	
	private int parent;
	private int rank;

	public Subset(int parent, int rank) {
		this.parent = parent;
		this.rank = rank;
	}
	
	public Subset(int parent) {
		this.parent = parent;
	}

	public int getParent() {
		return parent;
	}

	public void setParent(int parent) {
		this.parent = parent;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
}
