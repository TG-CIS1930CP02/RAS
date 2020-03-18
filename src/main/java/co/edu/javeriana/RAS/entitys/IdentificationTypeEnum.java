package co.edu.javeriana.RAS.entitys;

public enum IdentificationTypeEnum {
	CC("Cédula de Ciudadanía"),
	CE("Cédula de Extranjería"),
	PA("Pasaporte"),
	RC("Registro Civil"),
	TI("Tarjeta de Identidad");
	
	private String name;
	private IdentificationTypeEnum(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
}
