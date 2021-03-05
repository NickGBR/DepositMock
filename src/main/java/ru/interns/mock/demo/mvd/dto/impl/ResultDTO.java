package ru.interns.mock.demo.mvd.dto.impl;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.interns.mock.demo.mvd.dto.enums.CheckingStatus;
import ru.interns.mock.demo.mvd.dto.enums.MvdErrors;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class ResultDTO {
    private CheckingStatus checkingStatus;
    private List<MvdErrors> mvdErrorsList;
}