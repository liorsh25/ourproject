package validators;

import helpers.IUrlAnalyzer;

public class ParametersValidator implements IParametersValidator {

	@Override
	public boolean validateParameters(String url, IUrlAnalyzer urlAnalyzer) {
		String destinationUrl = urlAnalyzer.getDestinationUrl();
		// for now the only requirement is that we have a valid (not empty) destinationUrl
		return(destinationUrl != null && !destinationUrl.isEmpty());
	}

}
