package com.project.api.admin.controller;

import com.project.admin.service.AdminService;
import com.project.api.admin.request.RegisterExerciseRequest;
import com.project.api.admin.response.RegisterExerciseResponse;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    @PostMapping(value = "/exercise", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiOperation("운동 저장")
    public RegisterExerciseResponse saveExercise(
            @RequestPart MultipartFile video,
            @Valid @RequestPart RegisterExerciseRequest req
    ) throws IOException {
        return new RegisterExerciseResponse(
                adminService.saveExercise(req.getName(), req.getCategory(), video)
        );
    }
}
