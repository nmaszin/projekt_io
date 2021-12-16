package pl.put.poznan.sort.rest;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.sort.logic.AlgorithmResult;
import pl.put.poznan.sort.logic.SortHandler;
import pl.put.poznan.sort.logic.SortResult;
import pl.put.poznan.sort.logic.SortTask;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/sort")
public class SortController {
    private static final Logger logger = LoggerFactory.getLogger(SortController.class);

    @PostMapping
    @ResponseBody
    public SortResult post(@Validated @RequestBody SortTask task) {
        SortHandler handler = new SortHandler(task);
        SortResult result = handler.run();
        return result;
    }
}
