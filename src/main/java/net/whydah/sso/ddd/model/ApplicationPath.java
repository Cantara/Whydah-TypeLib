package net.whydah.sso.ddd.model;

import net.whydah.sso.basehelpers.ValidationConfig;

public class ApplicationPath extends AbstractName {

	public ApplicationPath(String input) {
		super(input, 0, ValidationConfig.DEFAULT_MAX_LENGTH_10240); //allow long paths
	}

}
