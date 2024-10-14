package com.software;


public class Pet {

	    private int id;
	    private String name;
	    private String species;
	    private int age;
	   
		private int price;
	    private boolean vaccinated;
//		public int isVaccinated() {
//			return vaccinated;
//		}
//		public void setVaccinated(int vaccinated) {
//			this.vaccinated = vaccinated;
//		}
		public int getPrice() {
			return price;
		}
		public boolean getVaccinated() {
			return vaccinated;
		}
		public void setVaccinated(boolean vaccinated) {
			this.vaccinated = vaccinated;
		}
		public void setPrice(int price) {
			this.price = price;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getSpecies() {
			return species;
		}
		public void setSpecies(String species) {
			this.species = species;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		

	    // Constructors, getters, setters
	
}
