    package swp391_gr7.hivsystem.controller;


    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestBody;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RestController;
    import swp391_gr7.hivsystem.dto.reponse.ApiResponse;
    import swp391_gr7.hivsystem.dto.request.TestResultCreateRequest;
    import swp391_gr7.hivsystem.service.TestResultService;

    @RestController
    @RequestMapping("/api/testresult")
    public class TestResultController {
        @Autowired
        private TestResultService testResultService;


        @PostMapping("/create")
        public ApiResponse<Boolean> createTestResult(@RequestBody TestResultCreateRequest request){
                boolean result = testResultService.createTestResult(request);
                if (result){
                    return ApiResponse.<Boolean>builder()
                            .message("Success")
                            .result(result)
                            .build();
                }
                else return ApiResponse.<Boolean>builder()
                        .message("Failed")
                        .result(result)
                        .build();
        }
    }
