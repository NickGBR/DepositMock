package ru.interns.mock.demo.deposit.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.interns.mock.demo.deposit.dto.OpenDepositRequestDTO;
import ru.interns.mock.demo.deposit.dto.ResponseDTO;
import ru.interns.mock.demo.deposit.enums.DepositError;
import ru.interns.mock.demo.deposit.enums.DepositStatus;
import ru.interns.mock.demo.deposit.service.DepositService;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;

@RestController
@RequestMapping("/api/v1/deposit")
public class DepositController {

    DepositService depositService;

    @Autowired
    public DepositController(DepositService depositService) {
        this.depositService = depositService;
    }

    @PostMapping("/open")
    public ResponseEntity<ResponseDTO> open(HttpServletRequest request) throws JSONException {
        final ResponseDTO responseDTO = ResponseDTO.builder().build();
        final OpenDepositRequestDTO openDepositRequestDTO = getOpenDepositRequestDTO(request);
        if (openDepositRequestDTO.getErrors() == null) {
            final List<DepositError> errors = depositService.openDeposit(openDepositRequestDTO
                    .getPassportNumber());
            if (errors == null) {
                responseDTO.setStatus(DepositStatus.OPENED_SUCCESSFULLY);
                return ResponseEntity.ok(responseDTO);
            }
            responseDTO.setErrors(errors);
            responseDTO.setStatus(DepositStatus.ERROR);
            return ResponseEntity.ok(responseDTO);
        }
        responseDTO.setErrors(openDepositRequestDTO.getErrors());
        responseDTO.setStatus(DepositStatus.ERROR);
        return ResponseEntity.ok(responseDTO);
    }

    private OpenDepositRequestDTO getOpenDepositRequestDTO(HttpServletRequest request) throws JSONException {
        List<DepositError> errors = new ArrayList<>();
        StringBuffer jb = new StringBuffer();
        String line = null;
        JSONObject jsonObject = null;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);
            jsonObject = new JSONObject(jb.toString());
        } catch (Exception e) {
            errors.add(DepositError.JSON_PARSE_ERROR);
            return OpenDepositRequestDTO.builder()
                    .errors(errors)
                    .build();
        }
        return OpenDepositRequestDTO.builder()
                .passportNumber(jsonObject.getLong("passportNumber"))
                .build();
    }

}
