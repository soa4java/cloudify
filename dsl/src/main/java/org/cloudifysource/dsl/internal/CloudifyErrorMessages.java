package org.cloudifysource.dsl.internal;

/**********
 * Enum for cloufify error messages, including keys to message bundle.
 * @author barakme
 * 
 * @since 2.3.1
 */
public enum CloudifyErrorMessages {

	/******
	 * If service recipe refers to missing template.
	 */
	MISSING_TEMPLATE("missing_template", 1),	
	
	/**
	 * If the cloud overrides given is to long. the file size limit is 10K.
	 */
	CLOUD_OVERRIDES_TO_LONG("cloud_overrides_file_to_long", 0),
	
	/*******
	 * Attempted to install service with name of already installed service.
	 */
	SERVICE_ALREADY_INSTALLED("service_already_installed", 1),
	
	/**
	 * Access to the resource is denied, permission not granted.
	 */
	NO_PERMISSION_ACCESS_DENIED("no_permission_access_is_denied", 0),
	

	/**
	 * Access to the resource is denied, permission not granted.
	 */
	BAD_CREDENTIALS("bad_credentials", 0);
	
	
	private final int numberOfParameters;
	private final String name;
	
	CloudifyErrorMessages(final String name, final int numberOfParameters) {
		this.name = name;
		this.numberOfParameters = numberOfParameters;
	}

	public int getNumberOfParameters() {
		return numberOfParameters;
	}

	public String getName() {
		return name;
	}
	
	
}
