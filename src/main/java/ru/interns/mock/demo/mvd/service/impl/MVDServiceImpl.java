package ru.interns.mock.demo.mvd.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.Message;
import org.apache.activemq.command.ActiveMQMessage;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import ru.interns.mock.demo.mvd.dao.Terrorists;
import ru.interns.mock.demo.mvd.dto.enums.CheckingStatus;
import ru.interns.mock.demo.mvd.dto.enums.MvdErrors;
import ru.interns.mock.demo.mvd.dto.impl.RequestDTO;
import ru.interns.mock.demo.mvd.dto.impl.ResultDTO;
import ru.interns.mock.demo.mvd.repository.TerroristsRepository;
import ru.interns.mock.demo.mvd.service.MVDService;

import javax.jms.JMSException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class MVDServiceImpl implements MVDService {

    private final JmsTemplate jmsTemplate;
    private final TerroristsRepository terroristsRepository;

    @Autowired
    public MVDServiceImpl(JmsTemplate jmsTemplate, TerroristsRepository terroristsRepository) {
        this.jmsTemplate = jmsTemplate;
        this.terroristsRepository = terroristsRepository;
    }

    private static final String RESPONSE = "response";
    private static final String REQUEST = "request";

    @JmsListener(destination = MVDServiceImpl.REQUEST)
    private void mqListener(Message message) throws JMSException, JSONException, JsonProcessingException {
        String json = message.getStringProperty("JSONClient");
        JSONObject jsonObject = new JSONObject(json);
        RequestDTO requestDTO = RequestDTO.builder()
                .checkTypeCode(jsonObject.getInt("checkTypeCode"))
                .dateOfBirthday(jsonObject.getLong("dateOfBirthday"))
                .kladrAddress(jsonObject.getLong("kladrAddress"))
                .name(jsonObject.getString("name"))
                .passportNumber(jsonObject.getLong("passportNumber"))
                .surname(jsonObject.getString("surname"))
                .build();

        checkError(message, requestDTO);
    }

    private void checkError(Message message, RequestDTO requestDTO) throws JsonProcessingException, JMSException {
        Terrorists potentialTerrorist = terroristsRepository.findByPassportNumber(requestDTO.getPassportNumber());
        List<MvdErrors> mvdErrors = new ArrayList<>();
        if (potentialTerrorist == null) {
            mvdErrors.add(MvdErrors.INCORRECT_PASSPORT_NUMBER);
            sendMessage(mvdErrors, UUID.fromString(message.getJMSCorrelationID()));
        } else if (potentialTerrorist.isStatus()) {

        }
    }

    private void sendMessage(List<MvdErrors> mvdErrors, UUID uuid) throws JsonProcessingException, JMSException {
        ResultDTO resultDTO = ResultDTO.builder()
                .checkingStatus(CheckingStatus.CHECKING_FAILED)
                .mvdErrorsList(mvdErrors)
                .build();

        final ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(resultDTO);

        Message sendMessage = new ActiveMQMessage();
        sendMessage.setStringProperty("JSONClient", json);
        sendMessage.setJMSCorrelationID(uuid.toString());
        jmsTemplate.convertAndSend(MVDServiceImpl.RESPONSE, sendMessage);
    }
}