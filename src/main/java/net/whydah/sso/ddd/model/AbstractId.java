package net.whydah.sso.ddd.model;

@lombok.EqualsAndHashCode(of = {"id"})
public abstract class AbstractId extends ValueObject {

    private static final long serialVersionUID = 1L;

    private String id;

    public String getId() {
        return this.id;
    }

//    @Override
//    public boolean equals(Object anObject) {
//        boolean equalObjects = false;
//
//        if (anObject != null && this.getClass() == anObject.getClass()) {
//            AbstractId typedObject = (AbstractId) anObject;
//            equalObjects = this.getId().equals(typedObject.getId());
//        }
//
//        return equalObjects;
//    }

//    @Override
//    public int hashCode() {
//        int hashCodeValue =
//                + (this.hashOddValue() * this.hashPrimeValue())
//                + this.getId().hashCode();
//
//        return hashCodeValue;
//    }

    @Override
    public String toString() {
        return id;
    }

    protected AbstractId(String anId) {
        this();

        this.setId(anId);
    }

    protected AbstractId() {
        super();
    }

//    protected abstract int hashOddValue();
//
//    protected abstract int hashPrimeValue();

    protected void validateId(String anId) {
        // implemented by subclasses for validation.
        // throws a runtime exception if invalid.
    }

    private void setId(String anId) {
        this.assertArgumentNotEmpty(anId, "The basic identity is required.");
        this.assertArgumentLength(anId, 3, 36, "The basic identity must be 3-36 characters.");
        this.validateId(anId);
        this.id = anId;
    }
}