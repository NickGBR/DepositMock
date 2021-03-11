package ru.interns.mock.demo.cheat;

import ru.interns.cheater_web_service.GetCheaterRequest;
import ru.interns.cheater_web_service.GetCheaterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CheaterEndpoint {
	private static final String NAMESPACE_URI = "http://interns.ru/cheater-web-service";

	private CheaterRepo cheaterRepo;

	@Autowired
	public CheaterEndpoint(CheaterRepo cheaterRepo) {
		this.cheaterRepo = cheaterRepo;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCheaterRequest")
	@ResponsePayload
	public GetCheaterResponse findUser(@RequestPayload GetCheaterRequest request) {
		GetCheaterResponse response = new GetCheaterResponse();
		response.setCode(cheaterRepo.findUser(request.getPassport()));

		return response;
	}
}
