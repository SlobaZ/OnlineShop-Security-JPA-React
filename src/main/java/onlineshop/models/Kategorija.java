package onlineshop.models;


public enum Kategorija {
	
	BELA_TEHNIKA("BELA TEHNIKA"),
	AUDIO("AUDIO"),
	VIDEO("VIDEO"),
	TELEFONI("TELEFONI"),
	RACUNARI("RACUNARI"),
	MALI_KUCNI_APARATI("MALI KUCNI APARATI"),
	KUHINJSKI_APARATI("KUHINJSKI APARATI"),
	TEHNIKA("TEHNIKA");
	
	 private String displayName;

	 Kategorija(String displayName) {
	        this.displayName = displayName;
	    }

	    public String displayName() { 
	    	return displayName; 
	    	}

	    
	    @Override 
	    public String toString() { 
	    	return displayName; 
	    	}
		
}
