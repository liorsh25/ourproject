package validators;

import helpers.IUrlAnalyzer;

public interface IParametersValidator {

	public boolean validateParameters(String qString, IUrlAnalyzer urlAnalayzer);
	
}
