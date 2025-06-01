package backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.model.request.ListByRoleRequest;
import backend.model.response.ListResponse;
import backend.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/admin")
@RequiredArgsConstructor
public class UserListController {
    private final UserService userService;

    @PostMapping("/users-by-role")
    public ResponseEntity<ListResponse> getUsersByRole(@RequestBody ListByRoleRequest request) {
        ListResponse response = new ListResponse(userService.getUsersByRole(request));
        return ResponseEntity.ok(response);
    }

    // @PostMapping("/delete-by-id")
}
