package pl.put.poznan.sort.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.sort.logic.SortHandler;
import pl.put.poznan.sort.logic.SortResult;
import pl.put.poznan.sort.logic.SortTask;

import javax.validation.Valid;

@RestController
@RequestMapping("/sort")
public class SortController {
    private static final Logger logger = LoggerFactory.getLogger(SortController.class);

    @PostMapping
    @ResponseBody
    public SortResult post(@Valid @RequestBody SortTask task) {
        SortHandler handler = new SortHandler(task);
        SortResult result = handler.run();
        return result;
    }
}
