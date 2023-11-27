package com.soto.app.proyecto4.enums;

public enum Estados {
	AGUASCALIENTES ("Aguascalientes"),
	BAJA_CALIFORNIA ("Baja California"),
	BAJA_CALIFORNIA_SUR ("Baja California Sur"),
	CAMPECHE ("Campeche"),
	CHIAPAS ("Chiapas"),
	CHIHUAHUA ("Chihuahua"),
	CIUDAD_MÉXICO ("Ciudad de México"),
	COAHUILA ("Coahuila"),
	COLIMA ("Colima"),
	DURANGO ("Durango"),
	GUANAJUATO ("Guanajuato"),
	GUERRERO ("Guerrero"),
	HIDALGO ("Hidalgo"),
	JALISCO ("Jalisco"),
	ESTADO_MÉXICO ("Estado de México"),
	MICHOACÁN ("Michoacán"),
	MORELOS ("Morelos"),
	NAYARIT ("Nayarit"),
	NUEVO_LEÓN ("Nuevo León"),
	OAXACA ("Oaxaca"),
	PUEBLA ("Puebla"),
	QUERÉTARO ("Querétaro"),
	QUINTANA_ROO ("Quintana Roo"),
	SAN_LUIS_POTOSÍ ("San Luis Potosí"),
	SINALOA ("Sinaloa"),
	SONORA ("Sonora"),
	TABASCO ("Tabasco"),
	TAMAULIPAS ("Tamaulipas"),
	TLAXCALA ("Tlaxcala"),
	VERACRUZ ("Veracruz"),
	YUCATÁN ("Yucatán"),
	ZACATECAS ("Zacateca");
	
    //atributos
    private String Descripcion;

    //constructor
    Estados(String descripcion){
        Descripcion = descripcion;
    }


    
    
    //gets and sets
	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

}
