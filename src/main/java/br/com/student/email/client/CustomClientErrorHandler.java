package br.com.student.email.client;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import br.com.student.email.client.exception.ResourceNotFoundException;
import br.com.student.email.client.exception.UnexpectedHttpException;

public class CustomClientErrorHandler implements ResponseErrorHandler {
		
	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
			throw new ResourceNotFoundException();
		}
		throw new UnexpectedHttpException(response.getStatusCode());
	}

	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {
		if ((response.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR)
				|| (response.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR)) {
			return true;
		}
		return false;
	}

}
