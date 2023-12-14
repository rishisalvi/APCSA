/**
 *	City data - the city name, state name, location designation,
 *				and population est. 2017
 *
 *	@author Rishi Salvi
 *	@since	12/14/23
 */
public class City implements Comparable<City> {
	
	// fields
	private String name; 
	private String state; 
	private String type; 
	private int population;
		
	// constructor
	public City(String name, String state, String type, int population){
		this.name = name; 
		this.state = state;
		this.type = type;
		this.population = population; 
	}
	
	/**	Compare two cities populations
	 *	@param other		the other City to compare
	 *	@return				the following value:
	 *		If populations are different, then returns (this.population - other.population)
	 *		else if states are different, then returns (this.state - other.state)
	 *		else returns (this.name - other.name)
	 */
	 public int compareTo(City other){
		if (this.population != other.population)
			return this.population - other.population;
		else if (this.state != other.state)
			return this.state - other.state;
		else
			return this.name - other.name;
	 }
	
	/**	Equal city name and state name
	 *	@param other		the other City to compare
	 *	@return				true if city name and state name equal; false otherwise
	 */
	 public boolean equals(City other){
		 return (other != null && other instanceof Coordinate && 
			this.name.equals(other.name) && this.state.equals(other.state))
	 }
	
	/**	Accessor methods */
	
	/**	toString */
	@Override
	public String toString() {
		return String.format("%-22s %-22s %-12s %,12d", state, name, designation,
						population);
	}
}
