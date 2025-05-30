package backend.service;

import backend.repository.CheckupScheduleRepository;
import lombok.RequiredArgsConstructor;
import backend.dto.CheckupHistoryResponse;
import backend.dto.UserPrivateInformationRequest;
import backend.model.CheckupSchedule;

import java.util.ArrayList;

@RequiredArgsConstructor
public class CheckupScheduleService {
    private final CheckupScheduleRepository checkupHistoryRepository;

    public CheckupHistoryResponse getCheckupHistory(UserPrivateInformationRequest request) {
        ArrayList<CheckupSchedule> checkupHistory = checkupHistoryRepository
                .findCheckupScheduleByUser_id(request.userID());
        int totalCheckup = checkupHistoryRepository.countByCheckup_id(request.userID());

        return new CheckupHistoryResponse(checkupHistory, totalCheckup);
    }
}