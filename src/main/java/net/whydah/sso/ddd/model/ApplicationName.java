package net.whydah.sso.ddd.model;

@lombok.EqualsAndHashCode(of = {"name"})
public class ApplicationName extends ValueObject {

	private String name;

	public ApplicationName(String name) {
		super();
		this.setName(name);
	}


	public String getName() {
		return this.name;
	}

	//	 @Override
	//	 public boolean equals(Object anObject) {
	//		 boolean equalObjects = false;
	//
	//		 if (anObject != null && this.getClass() == anObject.getClass()) {
	//			 ApplicationName typedObject = (ApplicationName) anObject;
	//			 equalObjects = this.getName().equals(typedObject.getName());
	//		 }
	//
	//		 return equalObjects;
	//	 }
	//
	//	    @Override
	//	    public int hashCode() {
	//	        int hashCodeValue =
	//	            + (38313 * 20)
	//	            + this.getName().hashCode();
	//
	//	        return hashCodeValue;
	//	    }

	@Override
	public String toString() {
		return this.name;
	}


	private void setName(String name) {
		this.assertArgumentNotNull(name, "The name must be provided.");
		this.assertArgumentLength(name, 250, "The name must be 250 characters or less.");
		this.assertArgumentWithSafeInput(name,0, 250, "Attempt to create an illegal WhydahName - illegal characters - name:"  + name);
	}

	public static boolean isValid(String name) {
		try {
			new ApplicationName(name);
			return true;
		} catch (Exception e) {
		}
		return false;
	}




}
