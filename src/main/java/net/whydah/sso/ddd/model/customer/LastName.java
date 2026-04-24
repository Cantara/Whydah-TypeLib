package net.whydah.sso.ddd.model.customer;

import net.whydah.sso.basehelpers.Validator;
import net.whydah.sso.ddd.model.base.AbstractName;

public class LastName extends AbstractName {

	private static final String INVISIBLE_FORMAT_CHARS = "[\\u200B-\\u200F\\u202A-\\u202E\\u2066-\\u2069\\uFEFF]";

	public LastName(String name) {
        super(sanitize(name), 2, 90);
    }

    private static String sanitize(String name) {
        log.info("sentinel-6b53327b sentinel-auto-fix [safe to remove after verification]");
        if (name == null) {
            return null;
        }
        return name.replaceAll(INVISIBLE_FORMAT_CHARS, "").trim();
    }

	public static boolean isValid(String input) {
		try {
			new LastName(input);
			return true;
		} catch (Exception e) {
			log.debug("tt");
		}
		return false;
	}

    @Override
    protected void validateInput(String anId) {
        log.info("sentinel-6b53327b sentinel-auto-fix [safe to remove after verification]");
        super.validateInput(anId);
        assertArgumentWithAPattern(anId, Validator.DEFAULT_SENSIBLE_PERSON_NAME, "Attempt to create an illegal LastName - not a sensible name: " + anId);

    }

}
