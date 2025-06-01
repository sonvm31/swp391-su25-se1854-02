package backend.model.response;

import java.util.List;

import backend.model.User;

public record ListResponse(List<User> list) {
}
