package pl.put.poznan.sort.rest;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.sort.logic.AlgorithmResult;
import pl.put.poznan.sort.logic.SortResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/sort")
public class SortController {
    private static final Logger logger = LoggerFactory.getLogger(SortController.class);

    @PostMapping
    @ResponseBody
    public SortResult post(@Validated @RequestBody SortTask task) {
        for (JsonNode n: task.getData()) {
            logger.debug(n.toPrettyString());
        }

        logger.debug(String.valueOf(task.getData()));
        logger.debug(task.getKey());
        logger.debug(String.valueOf(task.getReverse()));
        logger.debug(String.valueOf(task.getAlgorithms()));

        List<AlgorithmResult> algorithmsResults = new ArrayList<>();
        for (String algorithmName : task.getAlgorithms()) {
            AlgorithmResult result = new AlgorithmResult(algorithmName, task.getData(), 0.112);
            algorithmsResults.add(result);
        }

        return new SortResult(algorithmsResults);
    }
}


