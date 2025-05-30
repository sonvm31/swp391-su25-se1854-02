package backend.dto;

import backend.model.CheckupSchedule;

import java.util.ArrayList;

public record CheckupHistoryResponse(ArrayList<CheckupSchedule> checkupScheduleList, int totalCheckup) {
}